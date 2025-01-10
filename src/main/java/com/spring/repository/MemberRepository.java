package com.spring.repository;

import com.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // 만약 NULL이 반환하게 되는 경우 Optional을 사용 (Java 8)
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
