package com.woxsen.leagueapi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
public class PayUController {
    private final UserRepository userRepository;

    public PayUController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/generate-hash")
    public String generateHash(@RequestParam String key,
                               @RequestParam Double amount,
                               @RequestParam UUID userId,
                               @RequestParam(required = false) String surl,
                               @RequestParam(required = false) String furl) {
        User user = userRepository.findByIdAndActiveIndex(userId, true);
        String txnId= UUID.randomUUID().toString();
        String firstName= user.getFirstName();
        String email= user.getEmail();
        String phone= user.getPhone();
        String salt = "YOUR_SALT_HERE";

        StringBuilder hashString = new StringBuilder();
        hashString.append(key).append("|")
                .append(txnId).append("|")
                .append(amount).append("|")
                .append(firstName).append("|")
                .append(email).append("|")
                .append("|||||")
                .append(salt);

        String hash = hashCal("SHA-512", hashString.toString());
        
        // Create response object with hash and other required parameters
        Map<String, String> response = new HashMap<>();
        response.put("hash", hash);
        response.put("key", key);
        response.put("txnid", txnId);
        response.put("amount", String.valueOf(amount));
        response.put("firstname", firstName);
        response.put("email", email);
        response.put("phone", phone);
        response.put("surl", surl);
        response.put("furl", furl);
        
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