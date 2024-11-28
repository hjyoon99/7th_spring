package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.spring.service.memberService.MemberCommandService;
import umc.spring.web.dto.memberDto.MemberRequestDTO;

@Controller
@RequiredArgsConstructor
public class MemberViewController {
    private final MemberCommandService memberCommandService;

    // thymeleaf 사용을 위해 일부가 변경되었습니다.
    // 실제로는 8주차에서 작성한 컨트롤러와 동일하게 작성하시면 됩니다!!
    @PostMapping("/members/signup")
    public String joinMember(@ModelAttribute("memberJoinDto") MemberRequestDTO.JoinDto request, // 협업시에는 기존 RequestBody 어노테이션을 붙여주시면 됩니다!(그럼 dto에 setter 넣어줘야함)
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            // 뷰에 데이터 바인딩이 실패할 경우 signup 페이지를 유지합니다.
            System.out.println("유효성 검증 실패: " + bindingResult.getAllErrors());
            model.addAttribute("error", "비밀번호를 입력해주세요.");
            return "signup";
        }

        try {
            // 로그 추가
            System.out.println("컨트롤러에서 회원가입 요청 처리 시작");
            System.out.println("전달된 비밀번호: " + request.getPassword());
            memberCommandService.joinMember(request);
            System.out.println("컨트롤러에서 회원가입 요청 처리 완료");
            return "redirect:/login";
        } catch (Exception e) {
            // 회원가입 과정에서 에러가 발생할 경우 에러 메시지를 보내고, signup 페이디를 유지합니다.
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("memberJoinDto", new MemberRequestDTO.JoinDto());
        return "signup";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}