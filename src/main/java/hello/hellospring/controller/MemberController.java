package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        
        // Proxy가 주입되는지 console 확인
        System.out.println("memberService = " + memberService.getClass());
        // memberService = class hello.hellospring.service.MemberService$$EnhancerBySpringCGLIB$$4c64f2ee
        // CGLIB은 memberService를 복제해서 코드를 조작하는 기술
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
