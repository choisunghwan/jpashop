package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //fetchtype은 왠만하면 lazy로 둔다!!
    @ManyToOne(fetch = FetchType.LAZY) // 주문 입장에서 회원과의 관계는 (다 대 일) 관계이다.
    @JoinColumn(name = "member_id") // join할때 외래키는 member_id가 된다고 보면 된다.
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>(); //주문 아이템


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id") //관계에서 주인! 외래키를 여기 두었기 떄문!
    private Delivery delivery; // 배송


    private LocalDateTime orderDate; // 주문 시간

    @Enumerated(EnumType.STRING) // enum 형 생성시 어노테이션 작성 해야 하며, String 타입으로!
    private OrderStatus status; // 주문 상태 [ORDER , CANCEL]

    //==연관관계 메서드 ==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);

    }

    //== 생성 메서드 ==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//

    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");

        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem:orderItems){
            orderItem.cancel();
        }
    }

    //== 조회 로직 ==//

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderItem orderItem: orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
