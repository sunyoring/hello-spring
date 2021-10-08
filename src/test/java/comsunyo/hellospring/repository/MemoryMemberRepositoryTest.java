package comsunyo.hellospring.repository;

import com.sunyo.hellospring.domain.Member;
import com.sunyo.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repo = new MemoryMemberRepository();


    /*
    메소드 단위테스트에서는 문제가 없었는데 클래스 단위테스트를 하니 문제가 발생했다.
    테스트의 순서는 무작위로 결정되는데
    findAll()과 findByName()에서 같은 이름의 객체를 사용하고 있어 문제가 발생한 듯 하다.
    의존관계 없이 설계되어야 하기 때문에
    이것을 해결하기 위해 사용하는 @AfterEach 을 사용해서 MemoryMemberRepository를 한 메소드가
    끝날 때마다 비워준다.
    * */
    @AfterEach    //한 메소드가 끝날 때마다 동작하는 콜백 메소드
    public void afterEach(){
        repo.clearStore(); //
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repo.save(member);

       Member result = repo.findById(member.getId()).get();
       //테스트 방법 1 : System.out.println을 이용해 출력된 결과를 확인
        System.out.println("result = " + (result == member));
        //테스트 방법 2 :
//        Assertions.assertEquals(member,result);
//        import org.junit.jupiter.api.Assertions;
        //테스트 방법 3 :
        assertThat(member).isEqualTo(result);
//        import org.assertj.core.api.Assertions;
//        System.out.println 처럼 출력되는 결과는 없으나 실행시 테스트 결과에 빨간 불이 들어오며 에러가 뜨는 것을 확인할 수 있다.
    }


    @Test
    public void findByName() {
        Member member1= new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repo.save(member2);

        Member result = repo.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1= new Member();
        member1.setName("spring1");
        repo.save(member1);

        Member member2= new Member();
        member2.setName("spring2");
        repo.save(member2);

        List<Member> result = repo.findALL();

        assertThat(result.size()).isEqualTo(2);

    }


}
