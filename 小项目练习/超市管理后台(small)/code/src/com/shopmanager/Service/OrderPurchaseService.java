package com.shopmanager.Service;


import com.shopmanager.bean.Goods;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.common.GoodsNotFoundException;


import java.util.Map;
import java.util.Scanner;

public interface OrderPurchaseService {
    Map<String, OrderInfo> addProductToShoppingCart(Map<String, OrderInfo> shoppingCart, Scanner sc)  ;

    int checkOut(Map<String, OrderInfo> shoppingCart,Scanner scanner);


    Map<String, OrderInfo> editProductStoreInShoppingCart(Map<String, OrderInfo> shoppingCart, Scanner sc) throws GoodsNotFoundException;

    Map<String, OrderInfo> deleteProductInShoppringCart(Map<String, OrderInfo> shoppingCart, Scanner sc) throws GoodsNotFoundException;

    Goods querySingleItem(OrderInfo item);
}
