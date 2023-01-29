package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 추상클래스로 만든 이유? => 구현체를 가지게 할 것이기 때문에 abstract 를 붙였음.
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //  상속 전략 ( SINGLE_TABLE : 상속 받은 모든것을 한 테이블에 다 떄려박는다!)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name; // 아이템 명
    private int price; // 아이템 가격
    private int stockQuantity; // 재고량


}
