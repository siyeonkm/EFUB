package com.example.efubSeminar6th;

import com.example.efubSeminar6th.member.Grade;
import com.example.efubSeminar6th.member.Member;
import com.example.efubSeminar6th.member.MemberService;
import com.example.efubSeminar6th.member.MemberServiceImpl;
import com.example.efubSeminar6th.order.Order;
import com.example.efubSeminar6th.order.OrderService;
import com.example.efubSeminar6th.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        long memberId = 1L;

        Member member = new Member(memberId, "memberA", Grade.VIP);

        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
