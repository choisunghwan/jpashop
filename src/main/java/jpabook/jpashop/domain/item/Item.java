package jpabook.jpashop.domain.item;


import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0 ){
            throw new NotEnoughStockException("need more stock");

        }
        this.stockQuantity = restStock;
    }


}
