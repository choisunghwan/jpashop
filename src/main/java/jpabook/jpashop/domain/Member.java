package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 아이디

    private String name; // 회원 이름(명)

    @Embedded //내장 타입이 포함되었다!라는 어노테이션으로 매핑!~ (Address 내장 타입)
    private Address address; // 주소


    // mappedby : mappedby 가 적힌곳은 연관관계 주인이 아니다!X 그렇다면 여기서 의미는 order 클래스에서 member의 거울 일 뿐! 그래서 여기에 값을 넣는다고 order의 member값(외래키)이 변경되지 않음.
    @OneToMany(mappedBy ="member") // 회원 입장에서는 주문과의 관계에서 (일 대 다) 관계가 된다.
    private List<Order> orders = new ArrayList<>(); // 주문 리스트


}
