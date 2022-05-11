package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource ,EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    // 이렇게 하면 인터페이스를 확장시킬 수 있음
    // 스프링 컨테이너가 이걸 지원해줌(다형성), di까지
    // 어셈블리.. 조립하는 코드만 딱 손대면 나머지 애플리케이션 코드를 손 댈 필요가 없음. 이것이 스프링 장점!
    // 개방-폐쇄 원칙(OCP, Open-Closed Principle)
    // - 확장에는 열려있고, 수정/변경에는 닫혀있다 > 객체 지향에서 말하는 다형성을 잘 활용하면 이렇게 기능을 완전히
    // 변경해도 애플리케이션 전체를 수정할 필요 없다. 물론 조립해야 하는 변경사항이 있지만
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
