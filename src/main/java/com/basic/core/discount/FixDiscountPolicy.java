package com.basic.core.discount;

import com.basic.core.member.Grade;
import com.basic.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override
    public int discount(final Member member, final int price) {
        if (member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }
        return 0;
    }
}
