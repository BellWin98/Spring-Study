package com.basic.core;

import com.basic.core.member.Grade;
import com.basic.core.member.Member;
import com.basic.core.member.MemberService;
import com.basic.core.order.Order;
import com.basic.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        /**
         * ApplicationContext를 스프링 컨테이너라고 함
         * 스프링 빈은 @Bean 이 붙은 메서드의 이름을 스프링 빈의 이름으로 사용
         * AnnotationConfigApplicationContext는 ApplicationContext의 구현체
         * 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보(AppConfig.class)를 사용해서 스프링 빈을 등록함
         * 빈의 이름은 메서드 이름으로 사용하고, 직접 부여할 수도 있음 (@Bean(name="memberService2"))
         * 단, 빈 이름은 항상 다른 이름을 부여하기, 중복되면 다른 빈이 무시되거나, 기존 빈을 덮어버려 오류 발생
         * 스프링 컨테이너는 빈을 등록한 후에, 설정 정보(AppConfig.class)를 참고해서 빈 간의 의존관계를 주입(DI)한다.
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;

        memberService.join(new Member(memberId, "memberA", Grade.VIP));

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
