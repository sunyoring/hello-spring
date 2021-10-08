package com.sunyo.hellospring.repository;

import com.sunyo.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {  //DB 환경을 아직 선택하지 않았다는 가정하에 테스트를 위해 생성하는 클래스

    Member save(Member mem);

    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findALL();


//    Optional : null 값 처리 방법

}

