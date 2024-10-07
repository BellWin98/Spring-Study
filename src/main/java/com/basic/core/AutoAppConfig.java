package com.basic.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration

// @Component 어노테이션이 붙은 클래스를 찾아서 자동으로 스프링 빈으로 등록해줌
@ComponentScan(
        // @Configuration 클래스는 컴포넌트 스캔 대상에서 제외 (기존 예제 코드 유지하기 위해 충돌 방지)
        excludeFilters = @ComponentScan.Filter(type =  FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
