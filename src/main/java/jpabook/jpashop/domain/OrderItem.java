package jpabook.jpashop.domain;


import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    // 이 곳에 외래키가 있기 때문에 orders와 orderitem 중 연관관계 주인은 이 곳 (order item) 이 된다.
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id") // 외래키(orders 테이블의 order_id가 외래키가 됨)
    private Order order;


    private int orderPrice; // 주문 가격(주문 당시 가격)

    private int count; // 주문 수량


}
