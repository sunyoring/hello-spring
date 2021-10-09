package com.sunyo.hellospring.conroller;

import com.sunyo.hellospring.domain.Member;
import com.sunyo.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MemberController {

    private final MemberService memberService;

    //@Autowired 가 MemberService를 가지고 와서 연결해준다.
    //하지만 스프링 컨테이너는 MemberService를 가지고있지 않다. 이때 MemberService에 @Service 어노테이션을 써주면 등록된다.
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }  //의존성 주입 방법 중 생성자를 통해 주입하는 방법 , 필드 주입법도 있는데 권장하지 않는다고 한다.
    //setter 주입 방법 : setter메소드로 구현하여 주입하는 방법/ 단점 : public으로 되어 개방되어있기 때문에 문제가 발생할 가능성이 있다.


    //get 방식의 url 매핑
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    //post 방식 url 매핑
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        member.setAge(form.getAge());
        System.out.println("member: " + member);
        memberService.join(member);

        return "redirect:/";
    }
}
