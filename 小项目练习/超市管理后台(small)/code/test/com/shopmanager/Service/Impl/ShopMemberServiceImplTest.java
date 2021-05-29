package com.shopmanager.Service.Impl;

import com.shopmanager.Service.ShopMemberService;
import com.shopmanager.bean.ShopMember;
import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ShopMemberServiceImplTest {
    private ShopMemberService shopMemberService=new ShopMemberServiceImpl();
    @Test
    public void addMember() {
        ShopMember m = new ShopMember("huangdalao","huangdalao","131234567911",new Timestamp(new Date().getTime()),null);
        shopMemberService.addMember(m);
    }

    @Test
    public void queryAll() {
        List<ShopMember> members = shopMemberService.queryAll();
        members.forEach((item)->{
            System.out.println(item);
        });
    }

}