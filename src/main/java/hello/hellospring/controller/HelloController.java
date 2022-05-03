package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }

    // http://localhost:9090/hello-mvc?name=spring (get방식)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // @ResponseBody : body 부에 이 데이터를 내가 직접 넣어주겠다(return "hello" + name)
    // 실행하고 소스보면 저 hello + name만 똘랑 나옴.. 태그라는게 안보임
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // name spring이라면 "hello spring"
    }

    // 진짜는 지금부터.. 데이터를 내놓으라하면?
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        // 기존엔 viewResolver가 동작함. 하지만 ResponseBody 어노테이션 때문에 viewResolver 대신 HttpMessageConverter가 동작
        // json으로 돌아온다(jsonConverter / Hello 라는 객체라서! 기본 문자라면 그냥 문자뽑아냄 StringConverter)
        // jackson이라는 것이 json으로 반환해줌 (gson도 있음)
        return hello;
    }

    static class Hello{
        private String name;

        // alt + insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
