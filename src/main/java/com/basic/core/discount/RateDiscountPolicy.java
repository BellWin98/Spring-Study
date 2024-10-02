package com.basic.core.discount;

import com.basic.core.member.Grade;
import com.basic.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(final Member member, final int price) {

        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        return 0;
    }
}
