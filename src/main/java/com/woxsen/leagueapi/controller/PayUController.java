package com.woxsen.leagueapi.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.woxsen.leagueapi.entity.Bookings;
import com.woxsen.leagueapi.exceptions.ResourceNotFoundException;
import com.woxsen.leagueapi.repository.BookingsRepository;
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
@RequestMapping(AppConstants.BASE_URL+"/payu")
@Slf4j
public class PayUController {
	private final BookingsRepository bookingsRepository;
	private final UserRepository userRepository;
	@Value("${payu.salt}")
	private String salt;
	@Value("${payu.key}")
	private String key;
	@Value("${payu.FORM_URL}")
	private String FORM_URL;
	@Value("${app.DOMAIN}")
	private String DOMAIN;

	public PayUController(UserRepository userRepository,
						  BookingsRepository bookingsRepository) {
		this.userRepository = userRepository;
		this.bookingsRepository = bookingsRepository;
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

	@GetMapping("/users/{userId}/arenas/{arenaId}/slots/{slotId}/day/{day}/get-payu-button")
    public ModelAndView getPaymentView(@PathVariable UUID userId,@PathVariable UUID arenaId,@PathVariable UUID slotId,@PathVariable String day) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payu");
		User user=userRepository.findByIdAndActiveIndex(userId,true);
		Double amount=1.0;
        Map map = generateHash(amount,userId);

        modelAndView.addObject("key",key );
        modelAndView.addObject("formUrl",FORM_URL );
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
	@GetMapping("/bookings/{bookingId}/extension/get-payu-button")
    public ModelAndView getextensionView(@PathVariable UUID bookingId) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payu");
		Bookings booking = bookingsRepository.findByIdAndActiveIndex(bookingId, true);
		if(booking==null){
			throw new ResourceNotFoundException("Booking not found with id:"+bookingId);
		}
		if(booking.getExtension()!=null){
			throw new ResourceNotFoundException("Booking with id: "+bookingId+" has a slot already been extended");
		}
		User user=booking.getUser();
		Double amount=3500.0;
        Map map = generateHash(amount,user.getId());

        modelAndView.addObject("key",key );
        modelAndView.addObject("formUrl",FORM_URL );
        modelAndView.addObject("txnId",map.get("txnId") );
        modelAndView.addObject("amount",amount );
        modelAndView.addObject("pInfo","slot-booking" );
        modelAndView.addObject("fName", user.getFirstName());
        modelAndView.addObject("email",user.getEmail());
        modelAndView.addObject("phone",user.getPhone());
        modelAndView.addObject("lName", user.getLastName());
		//add addPayment method where store payment details only and redirect to success page
        modelAndView.addObject("surl", DOMAIN+AppConstants.BASE_URL+"/payments/bookings/"+bookingId+"/extension/redirect");
        modelAndView.addObject("furl", DOMAIN+AppConstants.BASE_URL+"/payments/bookings/"+bookingId+"/extension/redirect");
        modelAndView.addObject("hash",map.get("hash") );
        return modelAndView;
    }


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
