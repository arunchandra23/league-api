//package com.woxsen.leagueapi;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class tata {
//    @Autowired
//    private TestRestTemplate template;
//
//    @Test
//    void ad(){
//
//        List<String> arenas =new ArrayList<>();
//        arenas.addAll(Arrays.asList("013aad7b-8ec4-4711-bd39-66bda5be6442",
//                "0ab260e0-54be-4243-bd7c-913fdfa3f234",
//                "1082201a-c10d-4e91-8497-32f5b4ca3810",
//                "288d880a-dd8b-4942-aba1-9af17f7c4bde",
//                "3677c5fc-1313-4851-ab0a-1c2799562146",
//                "4ae5f7e5-b58c-46ac-b65e-52fc2dd81799",
//                "55011bb2-6437-4376-aa52-74dd430a57af",
//                "58acac5c-3206-4f9c-bea8-04978a27d0a1",
//                "66a42abb-d20c-4c3c-b8ff-e4dbd396172e",
//                "bd5717a2-2860-426d-ac95-b0321a066685",
//                "d1e03375-774c-4880-a303-2763b6d0b8a2",
//                "ff2b7bb4-3775-4bb7-b7f6-327db99d1f49"
//        ));
//        List<String> slots =new ArrayList<>();
//        slots.addAll(Arrays.asList("33a34c24-8198-4b7e-8229-f4d2a0605bec",
//                "35333815-01a5-4980-8c99-85f56bad992b",
//                "3b57ec48-7e13-45ae-aa8a-f5d42d1f71c2",
//                "3e8981fe-a1c8-498d-8e37-3911e66f840a",
//                "4595b03b-ebe7-42d9-bb8c-d396b6bef40c",
//                "759c015d-9288-46b4-8cbe-3d3df602df75",
//                "7bc1baa6-d893-4893-a43e-1ba8403a2647",
//                "85794056-109e-4bae-895f-e038ec6d21ba",
//                "a04fe1a1-0f51-4a5f-a189-4d5291f42478",
//                "a184cca5-3b97-43d5-8524-a396a58c542b",
//                "a4e8740d-d24d-4e28-bf1f-ebd9cfec6db1",
//                "aaaa4ea2-5abe-453f-b812-98640ef950c4",
//                "b44237af-6e79-4f16-b493-f5d474c0e2b3",
//                "c9f0be21-3d15-4def-983f-3ece99bc851e",
//                "d1791fbe-047d-4823-9f09-3d259b24ca4e",
//                "ee2af062-34d7-4092-bcdc-2b5706a20100",
//                "f44f24da-ecda-47c6-a5e4-fa4f20e1423c"
//        ));
//        for(int i=0; i<arenas.size();i++){
//            System.out.println("Arena"+arenas.get(i));
//            for(int j=0; j<slots.size();j++){
//
//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.add("Content-Type", "application/json");
//                HttpEntity<String> httpEntity = new HttpEntity<>( httpHeaders);
//                ResponseEntity<String> responseEntity = template.exchange("/api/v1/arenas/"+arenas.get(i)+"/slots/"+slots.get(j), HttpMethod.POST, httpEntity, String.class);
//
//
//            }
//        }
//        System.out.println(arenas);
//    }
//}
