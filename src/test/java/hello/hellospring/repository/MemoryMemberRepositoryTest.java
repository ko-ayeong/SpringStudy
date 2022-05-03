package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// Assertion.asserThat 줄임을 위함
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    // 틀을 먼저 만드는거 : 테스트 주도 개발 tdd?
    // 근데 지금은 구현클래스를 만들고 테스트를 한거라 tdd를 한게 아님.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 실행되고 끝날때마다 저장소를 다 지운다
    @AfterEach
    public void  afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // get으로 꺼내는건 좋은 방법은 아니다
        Member result = repository.findById(member.getId()).get();

        // 검증
        // System.out.println("result = " + (result == member));
        // 출력되는건 없지만 초록체크모냥이 뜸, 실패하면 오류 뜬다! (jupyter)
        // Assertions.assertEquals(result, member);
        // Assertions(assertj) : 멤버가 result랑 똑같애?
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // shift f6 하면 동시이름변경
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
