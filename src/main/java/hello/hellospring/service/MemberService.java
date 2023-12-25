package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional // JPA를 사용할 때 항상 필요
public class MemberService {

    private final MemberRepository memberRepository;

    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        /*
        // 시간 측정 로직과 핵심 비즈니스의 로직이 섞여 유지보수 어려움 -> 문제
        // AOP(Aspect Oriented Programming)를 적용해서 해결
        
        // 시간 측정 로직 = 공통 관심 사항 (cross-cutting concern)
        long start = System.currentTimeMillis();

        try {
            // 핵심 관심 사항 (core concern)
            // 같은 이름이 있는 중복 회원X
            validateDuplicatedMember(member); // 중복 회원 검증

            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
        */

        // 같은 이름이 있는 중복 회원X
        validateDuplicatedMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        /*
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
        */

        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
