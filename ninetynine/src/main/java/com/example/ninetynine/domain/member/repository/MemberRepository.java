package com.example.ninetynine.domain.member.repository;

import com.example.ninetynine.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmailAndPassword(String email, String password);

    Optional<Member> findByEmail(String email);
    Optional<Member> findByKakaoId(Long id);

    Optional<?> findMemberByEmail(String email);

}
