package com.basic.core;

import com.basic.core.discount.DiscountPolicy;
import com.basic.core.discount.FixDiscountPolicy;
import com.basic.core.discount.RateDiscountPolicy;
import com.basic.core.member.MemberRepository;
import com.basic.core.member.MemberService;
import com.basic.core.member.MemberServiceImpl;
import com.basic.core.member.MemoryMemberRepository;
import com.basic.core.order.OrderService;
import com.basic.core.order.OrderServiceImpl;

/**
 * 애플리케이션의 실제 동작에 필요한 구현 객체 생성
 * 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입해준다.
 * 즉, 객체의 생성과 연결을 담당하는 클래스 (관심사가 명확히 분리됨)
 */
public class AppConfig {

    public MemberService memberService() {
        // ctrl + alt + m => 메서드 추출
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), getDiscountPolicy());
    }

    private DiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
