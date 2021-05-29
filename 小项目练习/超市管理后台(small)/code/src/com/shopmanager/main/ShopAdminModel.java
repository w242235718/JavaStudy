package com.shopmanager.main;

import com.shopmanager.Controller.GoodsController;
import com.shopmanager.Controller.GoodsTypeController;
import com.shopmanager.Controller.ShopMemberController;

import java.util.Scanner;

public class ShopAdminModel {
    private Scanner s;
    public ShopAdminModel(){
        init();
    }

    private void init() {
        s=new Scanner(System.in);
    }

    public void run(){
        do {
            printMenu();
            int choose = s.nextInt();
            switch (choose){
                case 1:
                    new GoodsTypeController().run();
                    break;
                case 2:
                    new GoodsController().run();
                    break;
                case 3:
                    new ShopMemberController().run();
                    break;
                default:
                    System.out.println("出错了");
            }
        } while (returnMenu());
    }

    private boolean returnMenu(){
        System.out.println("是否返回菜单 y/n");
        return "y".equalsIgnoreCase(s.next());
    }
    private void printMenu() {
        System.out.println("****欢迎进入管理员模块****");
        System.out.println("1.商品类型管理");
        System.out.println("2.商品管理");
        System.out.println("3.会员管理");
    }
}
