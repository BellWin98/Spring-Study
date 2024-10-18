package com.basic.core.order;

import com.basic.core.discount.DiscountPolicy;
import com.basic.core.member.Member;
import com.basic.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 자동으로 만들어 줌
public class OrderServiceImpl implements OrderService {

    /**
     * 주문 서비스 클라이언트인 OrderServiceImpl은 DiscountPolicy(추상) 뿐만 아니라 구현 클래스(Fix, Rate)에도 의존
     * DIP 위반
     */
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    /**
     * 클라이언트가 인터페이스에만 의존하도록 코드를 변경했으나 (DIP 준수), NPE 발생
     * 누군가 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야 함
     * final 키워드를 사용하면, 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아줌
     * 오직 생성자 주입 방식만 final 키워드 사용 가능
     */
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    // 수정자 주입: 필수 값이 아닐 경우, 수정자 주입 방식으로 옵션 부여
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

    /**
     * 생성자가 1개일 때는, @Autowired 생략 가능. 2개 있으면, 반드시 명시해주어야 함
     * 생성자 주입을 사용하면, 파라미터에 주입 데이터를 누락하면 컴파일 오류가 발생 -> 좋은 오류
     * 생성자 주입 방식 선택 이유: 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살린 방법
     * 가끔, 옵션이 필요할 때, 수정자 주입 선택
     * @Autowired 는 타입으로 조회하기 때문에 ac.getBean("DiscountPolicy.class")와 유사하게 동작함
     *
     */
    @Autowired
    public OrderServiceImpl(final MemberRepository memberRepository,
                            final @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
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
