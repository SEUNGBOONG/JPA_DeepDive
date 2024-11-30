package com.example.deepdive.member.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<User, Long> {

    boolean existsByMemberNickName(String memberNickName);

    boolean existsByMemberEmail(String memberEmail);

    Optional<User> findMemberByMemberEmail(String memberEmail);

    Optional<User> findById(Long memberId);
}
