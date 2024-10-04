package com.basic.core.singleton;

import com.basic.core.AppConfig;
import com.basic.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 호출할 때마다 객체 생성 (JVM 메모리에 객체가 계속 생성됨) -> 메모리 낭비
        // 해결방안: 객체가 딱 1개만 생성되고, 공유하도록 설계 (싱글톤 패턴)
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonService() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        /**
         * isSameAs: 인스턴스 비교
         * isEqualTo
         */
        assertThat(singletonService1).isSameAs(singletonService2);
    }
}
