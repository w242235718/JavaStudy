package com.shopmanager.main;

import com.shopmanager.bean.Role;
import java.util.Scanner;

public class ShopManagerSystem {
    private Scanner s;
    public ShopManagerSystem(){
        init();
    }

    private void init() {
        s=new Scanner(System.in);
    }

    public void run() {
        System.out.println("欢迎使用XX超市管理系统");
        System.out.println("请先登陆");
        do {
            System.out.println("请输入用户id:");
            String userName = s.next();
            System.out.println("请输入密码");
            String passwd = s.next();
            if (isAdmin(userName,passwd)){
                //管理员
                new ShopAdminModel().run();
            }else if(isCashier(userName,passwd)){
                //收银员
                new ShopCashierModel().run();
            }
        } while (isContinue());
    }

    private boolean isCashier(String userName, String passwd) {
        if (userName==null||userName.length()==0){
            System.out.println("您输入的账号不存在！");
            return false;
        }
        if (!Role.ADMIN.equalsIgnoreCase(userName) && !Role.CASHIER.equalsIgnoreCase(userName)){
            System.out.println("您输入的账号不存在！");
            return false;
        }

        if (Role.CASHIER.equalsIgnoreCase(userName)){
            if (Role.CASHIER_PASSWD.equalsIgnoreCase(passwd)){
                return true;
            }else{
                System.out.println("您输入的密码不正确!");
                return false;
            }
        }
        return false;
    }

    private boolean isAdmin(String userName, String passwd) {

        if (Role.ADMIN.equalsIgnoreCase(userName)&&passwd.length()!=0){
            if (passwd.equalsIgnoreCase(Role.ADMIN_PASSWD)){
                return true;
            }else{
                System.out.println("您输入的密码不正确!");
            }
        }
        return false;
    }

    private boolean isContinue() {
        System.out.println("是否要继续登陆 y/n");
        String next = s.next();
        return "y".equalsIgnoreCase(next);
    }

}
