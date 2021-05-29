package com.shopmanager.main;

import com.shopmanager.Controller.OrderPurchaseController;
import com.shopmanager.Controller.OrderQueryController;
import com.shopmanager.Controller.RankController;

import java.util.Scanner;

public class ShopCashierModel {
    private Scanner s;
    private OrderPurchaseController orderPurchaseController;
    public ShopCashierModel() {
        init();
    }

    private void init() {
        s = new Scanner(System.in);
        orderPurchaseController=new OrderPurchaseController();
    }

    public void run() {
        do {
            printMenu();
            int choose = s.nextInt();
            switch (choose) {
                case 1:
                    orderPurchaseController.run();
                    break;
                case 2:
                    new OrderQueryController().run();
                    break;
                case 3:
                    new RankController().run();
                    break;
                default:
                    System.out.println("出错了");
            }
        } while (returnMenu());
    }

    private boolean returnMenu() {
        System.out.println("是否返回菜单 y/n");
        return "y".equalsIgnoreCase(s.next());
    }

    private void printMenu() {
        System.out.println("****欢迎进入收银员模块****");
        System.out.println("1.购买管理");
        System.out.println("2.订单查询");
        System.out.println("3.排行统计");
    }
}


