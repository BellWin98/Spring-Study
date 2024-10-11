package com.basic.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration

// @Component 어노테이션이 붙은 클래스를 찾아서 자동으로 스프링 빈으로 등록해줌
@ComponentScan(
        // 패키지 위치를 지정하지 않으면, @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨
        basePackages = "com.basic.core.member",
//        basePackageClasses = AutoAppConfig.class, // AutoAppConfig.class 가 있는 패키지의 하위 패키지를 모두 탐색
        // @Configuration 클래스는 컴포넌트 스캔 대상에서 제외 (기존 예제 코드 유지하기 위해 충돌 방지)
        excludeFilters = @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
