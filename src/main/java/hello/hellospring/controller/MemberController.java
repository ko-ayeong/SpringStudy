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

    // 생성자 주입(권장)
    @Autowired
    public MemberController(MemberService memberService) {
        // 인젝션
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // post 방식으로 받음 > postmapping 사용
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 회원가입 후 홈화면으로 보냄
        return "redirect:/";
    }

    // 목록
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        // 모델 안에 키가 멤버스.. 모든 회원을 담아 보낸거임!
        // 서버를 껐다가 다시키면 데이터가 모두 날아감, 메모리 안에 있기 때문에.. 실무에서 이렇게 하면 큰일!
        // 그래서 데이터를 데이터베이스나 서버에 저장해야한다
        model.addAttribute("members", members); // 리스트 자체를 담아서 넘길거임
        return "members/memberList";
    }
}
