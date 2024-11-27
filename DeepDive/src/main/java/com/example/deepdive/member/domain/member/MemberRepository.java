package com.example.deepdive.member.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByMemberNickName(String memberNickName);

    boolean existsByMemberEmail(String memberEmail);

    Optional<Member> findMemberByMemberEmail(String memberEmail);

    Optional<Member> findById(Long memberId);
}
