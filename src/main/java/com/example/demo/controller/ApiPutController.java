package com.example.demo.controller;

import com.example.demo.dto.PutRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiPutController {

    /** [1] 단순 Put **/
    @PutMapping("/put")
    public PutRequest put(@RequestBody PutRequest putRequest){
        System.out.println(putRequest);
        return putRequest;
    }

    /** [2] PathVariable 을 갖는 Put **/
    @PutMapping("/put2/{userId}")
    public PutRequest put2(@RequestBody PutRequest putRequest, @PathVariable String userId){

        System.out.println(userId + " : " + putRequest);
        return putRequest;
    }
}
