package com.example.efubSeminar6th.order;

import com.example.efubSeminar6th.discount.DiscountPolicy;
import com.example.efubSeminar6th.discount.FixDiscountPolicy;
import com.example.efubSeminar6th.member.Member;
import com.example.efubSeminar6th.member.MemberRepository;
import com.example.efubSeminar6th.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}