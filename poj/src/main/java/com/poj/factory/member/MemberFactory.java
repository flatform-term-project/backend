package com.poj.factory.member;

import com.poj.dto.auth.SignupRequest;
import com.poj.entity.member.EAuthority;
import com.poj.entity.member.Member;
import com.poj.repository.AuthorityRepository;
import com.poj.repository.MemberRepository;
import com.poj.validation.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class MemberFactory {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthValidator authValidator;

    public Member createUnverified(SignupRequest request) {
        authValidator.signupValidate(request);
        Member member = Member.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(Set.of(
                        authorityRepository.findByName(EAuthority.ROLE_UNVERIFIED)))
                .build();
        memberRepository.save(member);
        return member;

    }


}