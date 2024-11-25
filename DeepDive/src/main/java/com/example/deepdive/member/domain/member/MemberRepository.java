package com.example.deepdive.member.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;

public interface MemberRepository extends JpaRepository<Long, Member> {
}
