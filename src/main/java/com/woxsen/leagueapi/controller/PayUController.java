package com.woxsen.leagueapi.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.woxsen.leagueapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payu")
@Slf4j
public class PayUController {
	private final UserRepository userRepository;
	@Value("${payu.salt}")
	private String salt;
	@Value("${payu.key}")
	private String key;
	@Value("${payu.REDIRECT_BASE_URL}")
	private String REDIRECT_BASE_URL;
	@Value("${app.DOMAIN}")
	private String DOMAIN;

	public PayUController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static String hashCal(String type, String str) {
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append("0");
				}
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException nsae) {
		}
		return hexString.toString();
	}

	@GetMapping("/users/{userId}/arenas/{arenaId}/slot/{slotId}/day/{day}/redirect")
    public ModelAndView getPdfView(@PathVariable UUID userId,@PathVariable UUID arenaId,@PathVariable UUID slotId,@PathVariable String day) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payu");
		User user=userRepository.findByIdAndActiveIndex(userId,true);
//        RestTemplate temp=new RestTemplate();
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.add("Content-Type", "application/json");
//        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
//
//        ResponseEntity<Map> entity = temp.exchange("http://localhost:8080/payu/generate-hash?amount=1&userId=57f03cbb-0181-4e29-aad0-ab7040f63e98", HttpMethod.POST, httpEntity, Map.class);
		Double amount=1.0;
        Map map = generateHash(amount,userId);

        modelAndView.addObject("key",key );
        modelAndView.addObject("txnId",map.get("txnId") );
        modelAndView.addObject("amount",amount );
        modelAndView.addObject("pInfo","slot-booking" );
        modelAndView.addObject("fName", user.getFirstName());
        modelAndView.addObject("email",user.getEmail());
        modelAndView.addObject("phone",user.getPhone());
        modelAndView.addObject("lName", user.getLastName());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("arenaId", arenaId);
        modelAndView.addObject("slotId", slotId);
        modelAndView.addObject("surl", DOMAIN+AppConstants.BASE_URL+"/payments/users/"+userId+"/arenas/"+arenaId+"/slots/"+slotId+"/redirect?day="+day );
        modelAndView.addObject("furl", DOMAIN+AppConstants.BASE_URL+"/payments/users/"+userId+"/arenas/"+arenaId+"/slots/"+slotId+"/redirect?day="+day );
        modelAndView.addObject("hash",map.get("hash") );
        return modelAndView;
    }

	// @PostMapping("/generate-hash")
	public Map generateHash(Double amount, UUID userId) {
		User user = userRepository.findByIdAndActiveIndex(userId, true);
		Map<String, String> paymentDetail = new HashMap<>();
		String hashString = "";

		String txnId = UUID.randomUUID().toString();
		paymentDetail.put("txnId", txnId);
		String hash = "";
		// String otherPostParamSeq =
		// "phone|surl|furl|lastname|curl|address1|address2|city|state|country|zipcode|pg";
		String hashSequence = "key|txnid|amount|productinfo|firstname|email|||||||||||";
		hashString = hashSequence.concat(salt);
		hashString = hashString.replace("key", key);
		hashString = hashString.replace("txnid", txnId);
		hashString = hashString.replace("amount", String.valueOf(amount));
		hashString = hashString.replace("productinfo", "slot-booking");
		hashString = hashString.replace("firstname", user.getFirstName());
		hashString = hashString.replace("email", user.getEmail());

		log.info(hashString);

		hash = hashCal("SHA-512", hashString);
		paymentDetail.put("hash", hash);

		return paymentDetail;
	}

}

// In this example, the endpoint is /payu/generate-hash and it accepts the
// following parameters:
//
// key: Your PayU Merchant Key
// txnId: A unique transaction ID for the payment
// amount: The amount of the payment
// productInfo: A description of the product being purchased
// firstName: The first name of the customer making the payment
// email: The email address of the customer making the payment
// phone: The phone number of the customer making the payment
// surl (optional): The URL to redirect the customer to after a successful
// payment
// furl (optional): The URL to redirect the customer to after a failed payment
// The salt is a secret key that is used to create the hash. You should replace
// "YOUR_SALT_HERE" with your actual salt value.
//
// The hashCal method generates the hash using the SHA-512 algorithm. You can
// modify this method to use a different algorithm if necessary.
//
// The endpoint returns a JSON response containing the hash and the other
// parameters required by PayU Money