package jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // hellocontroller에 오면  hello 라는 url 호출!
    public String hello(Model model){
        model.addAttribute("data", "hello!!!");
        return "hello"; //resources폴더/template폴더/hello.html 파일로~

    }
}
