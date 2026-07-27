package org.example.commerceplatform.member.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.member.api.dto.MemberSignupRequest;
import org.example.commerceplatform.member.api.dto.MemberSignupResponse;
import org.example.commerceplatform.member.application.MemberService;
import org.example.commerceplatform.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberSignupResponse> signUp(@Valid @RequestBody MemberSignupRequest request) {
        Member member = memberService.signUp(request.email(), request.password(), request.name());
        MemberSignupResponse response = new MemberSignupResponse(member.getId(), member.getEmail(), member.getName());
        return ResponseEntity.created(URI.create("/members/" + member.getId())).body(response);
    }
}
