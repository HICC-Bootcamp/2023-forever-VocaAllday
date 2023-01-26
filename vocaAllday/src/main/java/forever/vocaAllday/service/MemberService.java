package forever.vocaAllday.service;

import forever.vocaAllday.dto.MemberFormDto;
import forever.vocaAllday.entity.Member;
import forever.vocaAllday.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor


public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);

    }

    public Member create(MemberFormDto memberFormDto){
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        return member;
    }


    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

}