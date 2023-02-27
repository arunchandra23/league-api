package com.woxsen.leagueapi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${payu.salt}")
    private String salt;
    @Value("${payu.key}")
    private String key;
    private final UserRepository userRepository;

    public PayUController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/generate-hash")
    public Map generateHash(@RequestParam Double amount,
                               @RequestParam UUID userId,
                               @RequestParam(required = false) String surl,
                               @RequestParam(required = false) String furl) {
        User user = userRepository.findByIdAndActiveIndex(userId, true);
        Map<String,String> paymentDetail=new HashMap<>();
        String hashString = "";

        String txnId = UUID.randomUUID().toString();
        paymentDetail.put("txnId",txnId);
        String hash = "";
        //String otherPostParamSeq = "phone|surl|furl|lastname|curl|address1|address2|city|state|country|zipcode|pg";
        String hashSequence = "key|txnid|amount|productinfo|firstname|email|||||||||||";
        hashString = hashSequence.concat(salt);
        hashString = hashString.replace("key", key);
        hashString = hashString.replace("txnid", txnId);
        hashString = hashString.replace("amount", String.valueOf(amount));
        hashString = hashString.replace("productinfo", "iPhone");
        hashString = hashString.replace("firstname", "Ashish");
        hashString = hashString.replace("email", "test@gmail.com");

        log.info(hashString);

        hash = hashCal("SHA-512", hashString);
        paymentDetail.put("hash",hash);

        return paymentDetail;
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