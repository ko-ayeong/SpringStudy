package hello.hellospring.domain;

// jpa는 객체랑 orm이라는 기술 orm(object relational mapping)
// 엔터티 매핑 - 어노테이션으로!
import javax.persistence.*;

@Entity
public class Member {

    // db가 알아서 생성해주는거 = GenerationType.IDENTITY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
