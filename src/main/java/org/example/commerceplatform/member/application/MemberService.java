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

    public Member login(String email, String rawPassword) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

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
