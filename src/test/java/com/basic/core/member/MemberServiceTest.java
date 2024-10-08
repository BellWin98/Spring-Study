package com.basic.core.member;

import com.basic.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {

        // given
        Member member = new Member(1L, "bellwin", Grade.BASIC);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}
