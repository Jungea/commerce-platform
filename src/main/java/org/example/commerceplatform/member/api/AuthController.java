package org.example.commerceplatform.member.api;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.member.api.dto.LoginRequest;
import org.example.commerceplatform.member.api.dto.LoginResponse;
import org.example.commerceplatform.member.application.MemberService;
import org.example.commerceplatform.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        Member member = memberService.login(request.email(), request.password());
        session.setAttribute("memberId", member.getId());
        return ResponseEntity.ok(new LoginResponse(member.getId(), member.getEmail(), member.getName()));
    }
}
