package org.example.commerceplatform.member.application;

import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.member.domain.Member;
import org.example.commerceplatform.member.domain.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(String email, String rawPassword, String name) {
        if (memberRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Member member = new Member(email, encodedPassword, name);
        return memberRepository.save(member);
    }
}
