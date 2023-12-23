package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

//    필드 주입
//    @Autowired private MemberService memberService;

//    세터 주입
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.MemberRepository = MemberRepository;
//    }

//    생성자 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
