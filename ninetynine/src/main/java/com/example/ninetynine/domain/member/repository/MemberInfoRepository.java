package com.example.ninetynine.domain.member.repository;

import com.example.ninetynine.domain.member.entity.Member;
import com.example.ninetynine.domain.member.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {
}
