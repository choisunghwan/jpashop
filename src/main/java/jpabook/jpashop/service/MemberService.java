package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //컴포넌트 스캔의 대상이 되어서 자동적으로 스프링빈에 등록됨.
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 이 있는 것의 생성자 생성해줌.
public class MemberService {

//    @Autowired //autowired: 스프링 빈에 등록된 memberrepository를 injection(주입) 해준다.
    private final MemberRepository memberRepository;


    /**
     * 회원 가입 (등록)
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 한 건 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
