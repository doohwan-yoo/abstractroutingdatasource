package com.corp.coooldh.example.controller;

import com.corp.coooldh.example.model.Member;
import com.corp.coooldh.example.repository.KafkaSender;
import com.corp.coooldh.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExampleController {

    private MemberService memberService;
    private KafkaSender kafkaSender;

    @Autowired
    public ExampleController(MemberService memberService, KafkaSender kafkaSender) {
        this.memberService = memberService;
        this.kafkaSender = kafkaSender;
    }

    @RequestMapping(value="/member/{id}", method= RequestMethod.GET)
    public @ResponseBody
    Member member(@PathVariable Long id) {
        return memberService.getMemberInfo(id);
    }

    @RequestMapping(value="/member", method=RequestMethod.POST)
    public @ResponseBody
    boolean member() {
        try {
            return memberService.saveMember();
        } catch(Exception e) {
            return false;
        }

    }

    @RequestMapping(value="/kafka/send", method= RequestMethod.GET)
    public @ResponseBody
    String kafkaSend() {

        kafkaSender.sendToTestTopic("KAFKA TEST");
        return "success";
    }


}
