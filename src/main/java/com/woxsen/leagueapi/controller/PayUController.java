package com.woxsen.leagueapi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woxsen.leagueapi.entity.User;
import com.woxsen.leagueapi.repository.UserRepository;

@RestController
@RequestMapping("/payu")
@Slf4j
public class PayUController {
    private final UserRepository userRepository;

    public PayUController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/generate-hash")
    public String generateHash(@RequestParam String key,
                               @RequestParam Double amountq,
                               @RequestParam UUID userId,
                               @RequestParam(required = false) String surl,
                               @RequestParam(required = false) String furl) {
        User user = userRepository.findByIdAndActiveIndex(userId, true);
        String txnId= UUID.randomUUID().toString();
        String firstName= "Ashish";
        String email= "test@gmail.com";
        String phone= "9988776655";
        String salt = "UkojH5TS";
        String productinfo = "iPhone";
        String amount = String.valueOf(1);
        Map<String,String> responseFinal =new HashMap<>();
        log.info(txnId);


        StringBuilder hashString = new StringBuilder();
        hashString.append(key).append("|")
                .append(txnId).append("|")
                .append(amount).append("|")
                .append(productinfo).append("|")
                .append(firstName).append("|")
                .append(email).append("|")
                .append("||||||||||")
                .append(salt);

        String hash = hashCal("SHA-512", hashString.toString());
        
        // Create response object with hash and other required parameters
        Map<String, String> response = new HashMap<>();
        response.put("hash", hash);
        response.put("productinfo", "iPhone");//
        response.put("key", "oZ7oo9");//
        response.put("txnid", txnId);//
        response.put("amount", String.valueOf(1));//
        response.put("firstname", "Ashish");//
        response.put("email", "test@gmail.com");//

        response.put("phone", "9988776655");
        response.put("surl", "http://localhost:8080/api/v1/util/success");
        response.put("furl", "http://localhost:3000/");
        
        // Convert response object to JSON and return
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = "";
        try {
            jsonResponse = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    private String hashCal(String type, String str) {
        byte[] hashSeq = str.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance(type);
            algorithm.reset();
            algorithm.update(hashSeq);
            byte messageDigest[] = algorithm.digest();

            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1)
                    hexString.append("0");
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hexString.toString();
    }
}





//In this example, the endpoint is /payu/generate-hash and it accepts the following parameters:
//
//        key: Your PayU Merchant Key
//        txnId: A unique transaction ID for the payment
//        amount: The amount of the payment
//        productInfo: A description of the product being purchased
//        firstName: The first name of the customer making the payment
//        email: The email address of the customer making the payment
//        phone: The phone number of the customer making the payment
//        surl (optional): The URL to redirect the customer to after a successful payment
//        furl (optional): The URL to redirect the customer to after a failed payment
//        The salt is a secret key that is used to create the hash. You should replace "YOUR_SALT_HERE" with your actual salt value.
//
//        The hashCal method generates the hash using the SHA-512 algorithm. You can modify this method to use a different algorithm if necessary.
//
//        The endpoint returns a JSON response containing the hash and the other parameters required by PayU Money