package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 홈 페이지
     * @GetMapping("/") : localhost:8080/으로 접속했을 때 home.html로 이동
     * 이 컨트롤러를 제거하면 기본적으로 resources/static/index.html로 이동
     * @return
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
