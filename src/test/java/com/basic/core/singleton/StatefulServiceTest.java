package com.basic.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // ThreadA: A사용자 10,000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20,000원 주문
        statefulService2.order("userB", 20000);

        int price = statefulService1.getPrice(); // A사용자 주문 금액 10,000원을 기대했으나, 20,000원 출력

        System.out.println("price = " + price);

        assertThat(price).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}