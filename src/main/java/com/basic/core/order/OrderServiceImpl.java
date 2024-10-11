package com.basic.core.order;

import com.basic.core.discount.DiscountPolicy;
import com.basic.core.member.Member;
import com.basic.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    // 주문 서비스 클라이언트인 OrderServiceImpl은 DiscountPolicy(추상) 뿐만 아니라 구현 클래스(Fix, Rate)에도 의존
    // DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 클라이언트가 인터페이스에만 의존하도록 코드를 변경했으나 (DIP 준수), NPE 발생
    // 누군가 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야 함
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    // 수정자 주입
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(@Qualifier("rateDiscountPolicy") DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자가 1개일 때는, @Autowired 생략 가능. 2개 있으면, 반드시 명시해주어야 함
    @Autowired
    public OrderServiceImpl(final MemberRepository memberRepository,
                            @Qualifier("rateDiscountPolicy") final DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(final Long memberId, final String itemName, final int itemPrice) {
        Member findMember = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(findMember, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
