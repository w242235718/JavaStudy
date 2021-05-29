package com.shopmanager.Service.Impl;

import com.shopmanager.Service.OrderPurchaseService;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.common.GoodsNotFoundException;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

public class OrderPurchaseServiceImplTest {
    private static  OrderPurchaseService orderPurchaseService=new OrderPurchaseServiceImpl();

    public static void main(String[] args) {
        Map<String, OrderInfo> shoppingCart=new HashMap<>();
        int shoppingCartCount= shoppingCart.size();
        Scanner sc = new Scanner(System.in);

        shoppingCart = orderPurchaseService.addProductToShoppingCart(shoppingCart,sc);
        shoppingCartCount=shoppingCart.size();
        System.out.println(shoppingCart);

    }

    @Test
    public void addProductToShoppingCart() {
        Scanner sc = new Scanner(System.in);
        HashMap<String, OrderInfo> shoppingCart=new HashMap<>();

        Map<String, OrderInfo> cart = orderPurchaseService.addProductToShoppingCart(shoppingCart,sc);
        System.out.println(cart);


    }

}