package com.shopmanager.Controller;


import com.shopmanager.Service.Impl.OrderPurchaseServiceImpl;
import com.shopmanager.Service.OrderPurchaseService;

import com.shopmanager.bean.Goods;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.common.GoodsNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OrderPurchaseController {
    private Map<String, OrderInfo> shoppingCart;
    private Scanner sc;
    private OrderPurchaseService orderPurchaseService;

    public OrderPurchaseController(){
        init();
    }
    public void init(){
        shoppingCart=new HashMap<>();
        sc=new Scanner(System.in);
        orderPurchaseService=new OrderPurchaseServiceImpl();
    }

    public void run(){
        do {
            printMenu();
            int choose = sc.nextInt();
            switch (choose){
                case 1:
                    addProductToShoppingCart();
                    break;
                case 2:
                    editProductStore();
                    break;
                case 3:
                    deleteProductFromShoppingCart();
                    break;
                case 4:
                    showShoppingCart();
                    break;
                case 5:
                    checkOutShoppingCart();
                    break;
                default:
                    System.out.println("出错啦！");
            }
        } while (returnMenu());
    }

    private void checkOutShoppingCart() {
        int result=orderPurchaseService.checkOut(shoppingCart,sc);
        if (result<0){
            System.out.println("结算失败");
            return;
        }
        System.out.println("结算成功");
        //结算成功清空购物车
        shoppingCart.clear();
    }

    private void showShoppingCart() {

        shoppingCart.forEach((key,item)->{
            StringBuilder pb = new StringBuilder();
            Goods g=orderPurchaseService.querySingleItem(item);
            System.out.println("购物车：");
            pb.append("商品名称:"+g.getGname());
            pb.append("\t商品价格:"+g.getPrice());
            pb.append("\t商品编号:"+g.getGid());
            pb.append("\t购买数量:"+item.getOrdernum());
            pb.append("\t总额:"+(item.getOrdernum()*g.getPrice().doubleValue()));
            System.out.println(pb);
        });
    }

    private void editProductStore() {
        try {
            shoppingCart=orderPurchaseService.editProductStoreInShoppingCart(shoppingCart,sc);
            System.out.println("修改成功！");
        } catch (GoodsNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProductFromShoppingCart() {
        try {
            shoppingCart=orderPurchaseService.deleteProductInShoppringCart(shoppingCart,sc);
            System.out.println("删除成功！");
        } catch (GoodsNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProductToShoppingCart() {

        shoppingCart=orderPurchaseService.addProductToShoppingCart(shoppingCart,sc);
        System.out.println("是否确认购买? y/n");
        boolean isPurchase="y".equalsIgnoreCase(sc.next());
        if (!isPurchase){
            return;
        }
        int result=orderPurchaseService.checkOut(shoppingCart,sc);
        if (result<=0){
            System.out.println("结算失败");
            return;
        }
        System.out.println("结算成功");
        //结算成功清空购物车
        shoppingCart.clear();
    }

    private boolean returnMenu() {
        System.out.println("是否继续 y/n");
        if ("y".equalsIgnoreCase(sc.next())){
            return true;
        }
        return false;
    }

    private void printMenu() {
        System.out.println("***购物车***");
        System.out.println("1.添加商品");
        System.out.println("2.修改商品数量");
        System.out.println("3.删除商品");
        System.out.println("4.展示购物车");
        System.out.println("5.结算购物车");
        System.out.println("请选择 ");
    }


}
