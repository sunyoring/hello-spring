package com.sunyo.hellospring.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String HelloController(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";

    }

    //mvc방식으로 데이터 전달하여 화면 띄우기
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {  //@RequestParam : 웹에서 인자를 받는다.
        model.addAttribute("name",name); //키와 값의 형태로 파라미터로 넘어온 name을 넣어준다.
        return "hello-template";
    }

    //API방식으로 데이터 전달하여 화면 띄우기기
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
