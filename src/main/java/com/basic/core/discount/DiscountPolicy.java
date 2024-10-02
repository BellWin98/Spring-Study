package com.basic.core.discount;

import com.basic.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
