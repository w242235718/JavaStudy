package com.shopmanager.Service;

import com.shopmanager.bean.ShopMember;

import java.math.BigDecimal;
import java.util.List;

public interface ShopMemberService {

    int addMember(ShopMember m);

    List<ShopMember> queryAll();

    ShopMember queryMemberById(int id);

    int editMember(ShopMember m);

    int deleteMember(int id);

    int chargeShopMember(ShopMember m, BigDecimal balance);
}
