package com.spring.repository;

import com.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 동시성 문제가 고려되어 있지 않다, 실무에서는 ConcureentHashMap 사용 고려
    private static Map<Long, Member> store = new HashMap<>();

    // 실무에선 AtomicLong 사용 고려
    private static long sequence = 0L;

    // Select Methods to Implement (⌥ + ⮐)
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
