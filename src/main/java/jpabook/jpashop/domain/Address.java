package jpabook.jpashop.domain;

// JPA의 내장타입 _ Address

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
