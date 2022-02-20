package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //해당 Class는 REST API 를 처리하는 컨트롤러임을 지정
@RequestMapping("/api") //Request Mapping URI 를 지정해주는 어노테이션
public class ApiGetController {

    @GetMapping(path = "/test1") //http://localhost:9090/api/test
    public String test1(){
        return "Get Test";
    }
    @RequestMapping(path = "/test2", method = RequestMethod.GET) //http://localhost:9090/api/test2
    public String test2(){
        return "Get Test2";
    }

    @GetMapping(path = "/path-variable/{name}")
    public String pathVariable(@PathVariable String name){   //http://localhost:9090/api/pathvariable/{name}
        System.out.println(name);
        return name;
    }
    //아래 방식은 PathVariable 을 통해 변수명이 변경되었을 때에도 잘 찾아올 수 있도록 함
    @GetMapping(path = "/path-variable2/{name}")
    public String pathVariable2(@PathVariable(name="name") String pathName, String name){   //http://localhost:9090/api/pathvariable/{name}
        System.out.println(name + "," + pathName);
        return pathName;
    }

    /** 쿼리 패러미터의 사용 **/
    //구글의 예시
    // https://google.com/
    // search?q=test
    // &sxsrf=APq-WBuJj5l0o1BvoNRvcu2lQF6rwLr0rA%3A1645258541876
    // &source=hp
    // &ei=LacQYp2yM86D1e8Pl9Cn6A4
    // &iflsig=AHkkrS4AAAAAYhC1PRBVEd0MoRmgwLzydsC3MzEiSc-p
    // &ved=0ahUKEwjd_MzXqYv2AhXOQfUHHRfoCe0Q4dUDCAk
    // &uact=5
    // &oq=test
    // &gs_lcp=Cgdnd3Mtd2l6EAMyCwgAEIAEELEDEIMBMg4ILhCABBCxAxDHARCjAjIICAAQgAQQsQMyCwgAEIAEELEDEIMBMggIABCABBCxAzILCAAQgAQQsQMQgwEyCwgAEIAEELEDEIMBMgsIABCABBCxAxCDATILCAAQgAQQsQMQgwEyCAgAEIAEELEDOgcIIxAnEJ0COgQIIxAnOg4ILhCABBCxAxDHARDRAzoNCC4QgAQQxwEQ0QMQCjoFCAAQgAQ6BwgjEOoCECc6EQguEIAEELEDEIMBEMcBENEDOgsILhCABBCxAxCDAToOCC4QgAQQxwEQ0QMQ1AI6CwguEIAEELEDENQCOggILhCABBDUAkoFCDwSATFQAFjYDWCvDmgDcAB4AIAB6QGIAZYEkgEFMS4yLjGYAQCgAQGwAQo
    // &sclient=gws-wiz
    //위와 같이 시작은 ?로 가고, &을 통해 key=value 형태로 URI 작성

    /** [1] **/
    //아래를 구현하기 위한 함수 작성
    //http://localhost:9090/api/query-param?user=cksgnl&age=32
    //RequestParam 어노테이션을 통해 Map 을 가져오는 방식
    @GetMapping(path="/query-param")
    public String queryParam (@RequestParam Map<String, String> queryParam){
        StringBuilder stringBuilder = new StringBuilder();
        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());

            stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "\n");
        });

        return stringBuilder.toString();
    }

    /** [2] **/
    //아래 방식은 RequestParam 에 대해 지정된 Key와 일치하여야만 함.
    @GetMapping(path="/query-param2")
    public String queryParam2 (
            @RequestParam String name,
            @RequestParam int age){
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(name);
        System.out.println(age);

        stringBuilder.append( "name=" + name + "age=" + age + "\n");

        return stringBuilder.toString();
    }
    /** [3] **/
    //아래 방식은 RequestParam 을 별도로 사용하지 않아도, 객체 자체에서 변수와 이름 매칭을 자동으로 해줌
    //그래서 없어도 dto에 해당하는 클래스만 변경하게 되도 자동으로 작동하게 됨
    //쿼리 패러미터가 아무리 늘어나도 해당하지 않는 정보는 무시하기 때문에 실무에서 가장 많이 GET Method 를 활용하는 방법이다.
    @GetMapping(path="/query-param3")
    public String queryParam3 (
            UserRequest userRequest){
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        stringBuilder.append( "name=" + userRequest.getName() + "age=" + userRequest.getAge() + "\n");

        return stringBuilder.toString();
    }
}
