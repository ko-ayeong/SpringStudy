package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스는 다중상속이 된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 구현할 것이 .. 이거 뿐이다..
    // JPQL select m from Member m where n.name = ?
    // findByNameAndId(String name, Long id) 이런 식으로도 가능하다
    @Override
    Optional<Member> findByName(String name);
}

/* 정리
    순수 jdbc : 쿼리 작성 어마어마함
    jdbcTemplate : 반복 코드 줄지만 sql 직접 작성해야함
    jpa : 기본적인 crud, 내가 쿼리 작성할 필요 x, 물론 select jpql 작성해야함
    스프링 데이터 jpa : 구현 클래스를 작성할 필요 없이 인터페이스만 작성하면 됨, findBy... 제공
 */