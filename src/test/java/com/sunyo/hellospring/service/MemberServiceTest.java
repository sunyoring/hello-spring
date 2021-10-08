package com.sunyo.hellospring.service;

import com.sunyo.hellospring.domain.Member;
import com.sunyo.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    /*하나의 레퍼지토리로 테스트하기위해 메소드를 실행하기전에
    * memberService에게 외부에서 레파지토리를 넣어 준다.
    * 현재 상황에선 MemoryMemberRepository 가 static이라 상관없긴 하지만 알아두자.
    * */
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    /* 회원가입과 중복회원_예외 메소드를 같이 돌리자 충돌이 일어난다.
    * 메모리 비워주기 */
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 상황이 주어짐
        Member member = new Member();
        member.setName("spring");

        //when  : 실행
        Long saveId = memberService.join(member);

        //then  : 기대 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_예외(){

        Member m1 = new Member();
        m1.setName("spring");

        Member m2 = new Member();
        m2.setName("spring");
        memberService.join(m1);

        /*예외처리 방법 2  assertThrows(기대하는 예외.class, ()-> 예외를 기대하는 로직 ) 를 이용*/
        assertThrows(IllegalStateException.class,() -> memberService.join(m2));  /*테스트 성공*/
//        assertThrows(NullPointerException.class,() -> memberService.join(m2));   /*테스트 실패*/
        /*에러메세지를 받는 법 :   assertThrows가 반환해줌. */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");/*테스트 성공*/



/*       예외처리 방법 1
        try{
            memberService.join(m2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/

    }

    @Test
    void 회원전체조회() {
    }

    @Test
    void 아이디로회원찾기() {
    }
}