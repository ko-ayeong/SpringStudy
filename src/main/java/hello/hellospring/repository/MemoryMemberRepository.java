package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 예제니까 단순하게 해쉬맵 사용
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // 실무에서는 어텀 롱? 이런걸 써주는 게 좋음

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member); // map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null 이 반환될 가능성이 있어서 옵셔널 옵널 어쩌고을 감싸서 사용한다 !!
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 루프를 돌리는거임
        // findAny는 하나라도 찾는거임
        // 맵에서 돌면서 어 그냥 하나 찾아서 반환 , 없으면 옵셔널에...
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store.values > member
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
