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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 실제 동작에 필요한 구현 객체 생성
 * 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입해준다.
 * 즉, 객체의 생성과 연결을 담당하는 클래스 (관심사가 명확히 분리됨)
 * 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달하면,
 * 클라이언트와 서버의 실제 의존관계가 연결됨 => 의존관계 주입
 * IoC 컨테이너 또는 DI 컨테이너라 불림 (주로 DI 컨테이너로 불림): 객체를 생성하고 관리하면서 의존관계를 연결해주는 것
 * 스프링 컨테이너가 @Configuration이 붙은 Config 클래스를 설정(구성)정보로 사용
 * 스프링 컨테이너가 @Bean 메서드를 모두 호출 후, 반환된 객체를 스프링 컨테이너에 등록
 * 이렇게 등록된 객체를 스프링 빈이라 함
 */
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        // ctrl + alt + m => 메서드 추출
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), getDiscountPolicy());
    }

    @Bean
    public DiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }
}
