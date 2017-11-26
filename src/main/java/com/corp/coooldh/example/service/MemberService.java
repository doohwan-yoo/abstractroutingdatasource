package com.corp.coooldh.example.service;

import com.corp.coooldh.example.config.ConnectToDB;
import com.corp.coooldh.example.model.Member;
import com.corp.coooldh.example.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    @ConnectToDB(value="SLAVE")
    public Member getMemberInfo(Long id) {
        return memberRepository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @ConnectToDB(value="MASTER")
    public boolean saveMember() throws Exception {

        Member member = new Member();
//        member.setMemberId();
        member.setMemberId("test");
        member.setMemberPwd("12345");

        memberRepository.save(member);

        return true;
    }

}
