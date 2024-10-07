package com.basic.core.member;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryMemberRepository implements MemberRepository{

    private static final Map<Long, Member> STORE = new ConcurrentHashMap<>();

    @Override
    public void save(final Member member) {
        STORE.put(member.getId(), member);
    }

    @Override
    public Member findById(final Long memberId) {
        return STORE.get(memberId);
    }
}
