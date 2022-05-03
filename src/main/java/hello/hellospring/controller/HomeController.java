package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 컨트롤러가 정적 파일(static)보다 우선순위가 높다
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
