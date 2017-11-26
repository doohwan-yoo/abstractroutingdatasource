package com.corp.coooldh.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_member")
public class Member implements Serializable {

    private static final long serialVersionUID = -5601386028913882753L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="memberno", updatable = false, nullable = false)
    private Long memberNo;

    @Column(name="memberid")
    private String memberId;

    @Column(name="memberpwd", nullable = false)
    private String memberPwd;

    public Long getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Long memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }
}
