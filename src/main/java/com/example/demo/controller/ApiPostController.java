package com.example.demo.controller;

import com.example.demo.dto.PostRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiPostController {

    /** [1] 단순 맵 오브젝트 통해 가져오는 방식 **/
    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){ //인자로 RequestBody 어노테이션 추가
//        requestData.entrySet().forEach(stringObjectEntry -> {
//
//        }); //이렇게 쓸 수도 있고, 아래처럼 간략하게 쓸 수도 있다.
        requestData.forEach((key, value) -> {   //보통
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });
    }
    /** [2] 객체 생성 후 해당하는 정보에 대해 가져오는 방식 (대부분 선호) **/
    @PostMapping("/post2")
    public void post2(@RequestBody PostRequest requestData){
        System.out.println(requestData.toString());
    }
}
