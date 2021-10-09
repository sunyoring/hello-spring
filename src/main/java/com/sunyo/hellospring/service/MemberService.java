package com.sunyo.hellospring.service;

import com.sunyo.hellospring.domain.Member;
import com.sunyo.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

/*
@Service
*/
public class MemberService {
/*ctrl + shift + t : 테스트 자동 생성 */
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*회원가입*/

    public Long join(Member member){

        //중복 체크
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    /* ctrl + alt + m : 메소드 추출하기 */
    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");


/*        Optional<Member> result = memberRepository.findByName(member.getName());

//        Member member1 = result.get(); 값을 꺼낼 때 사용하나 직접적으로 꺼내는 것은 권장하지 않는다. 값이 있는지 확인하고 꺼내자
                result.ifPresent(m ->{ //ifPresent : 값이 존재한다면 로직이 동작 Optional에 객체를 담았을 때 사용할 수 있음.
                    throw new IllegalAccessException("이미 존재하는 회원입니다.");*/

        });
    }


    /*전체 회원 조회*/
    public List<Member> findMembers() {
        return memberRepository.findALL();
    }
    /*아이디로 회원 조회*/
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
