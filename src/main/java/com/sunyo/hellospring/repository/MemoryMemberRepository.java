package com.sunyo.hellospring.repository;

import com.sunyo.hellospring.domain.Member;

import java.util.*;

/*
@Repository
*/
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence =0L;  //id


    @Override
    public Member save(Member mem) {  //id 넣어주고 Member객체 생성
        mem.setId(++sequence);
        store.put(mem.getId(), mem);   //store에 저장
        return mem;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return   store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //찾으면 찾은거 반환 없으면 Optional로 반환
    }   //람다식 : store를 돌리면서 member중 이름이 파라미터 이름과 일치하면 반환

    @Override
    public List<Member> findALL() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}

