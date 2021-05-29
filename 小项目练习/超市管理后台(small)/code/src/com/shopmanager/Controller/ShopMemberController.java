package com.shopmanager.Controller;

import com.shopmanager.Service.Impl.ShopMemberServiceImpl;
import com.shopmanager.Service.ShopMemberService;
import com.shopmanager.bean.ShopMember;
import com.shopmanager.utils.EncodingUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ShopMemberController {
    private Scanner sc = null;
    private ShopMemberService shopMemberService;

    public ShopMemberController() {
        init();
    }

    private void init() {
        sc = new Scanner(System.in);
        shopMemberService = new ShopMemberServiceImpl();
    }

    public void run() {
        do {
            printMenu();
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    addShopMember();
                    break;
                case 2:
                    editShopMember();
                    break;
                case 3:
                    deleteShopMember();
                    break;
                case 4:
                    queryShopMember();
                    break;
                case 5:
                    reChangeShopMember();
                    break;
                default:
                    System.out.println("出错啦！");
            }
        } while (returnMenu());
    }

    private void reChangeShopMember() {
        ShopMember m=null;
        System.out.println("请输入要充值的会员编号");
        int uid = sc.nextInt();
        if ((m=shopMemberService.queryMemberById(uid))==null){
            System.out.println("您输入的会员编号不存在！");
            return;
        }
        System.out.println("请输入要充值的金额");
        BigDecimal charge = sc.nextBigDecimal();
        int result=shopMemberService.chargeShopMember(m,charge);
        if (result<0){
            System.out.println("充值失败!");
            return;
        }
        System.out.println("充值成功!");
    }

    private void queryShopMember() {
        System.out.println("1.根据id查询指定会员信息");
        System.out.println("2.查询所有会员信息");
        System.out.println("请选择");
        int choose = sc.nextInt();
        switch (choose) {
            case 1:
                queryShopMemberByID();
                break;
            case 2:
                queryAll();
                break;
            default:
                System.out.println("出错了！");
        }
    }


    private void deleteShopMember() {
        System.out.println("请输入要删除的会员编号");
        int id=sc.nextInt();
        int result=shopMemberService.deleteMember(id);
        if (result<1){
            System.out.println("删除失败");
            return;
        }
        System.out.println("删除成功");
    }

    private void editShopMember() {
        ShopMember m=null;

        System.out.println("请输入要修改的会员编号");
        int uid=sc.nextInt();

        if ((m=shopMemberService.queryMemberById(uid))==null){
            System.out.println("您输入的会员编号不存在！");
            return;
        }
        System.out.println("请输入要修改的会员名");
        String username = sc.next();

        System.out.println("请输入密码");
        String passwd = sc.next();

        System.out.println("请再次输入密码");
        if (!passwd.equals(sc.next())) {
            System.out.println("确认密码输入错误");
            return;
        }
        System.out.println("请输入联系方式");
        String phone = String.valueOf(sc.nextLong());

        System.out.println("请输入要修改的积分");
        float score = sc.nextFloat();

        System.out.println("请输入要修改的余额");
        double balance = sc.nextDouble();

        m.setUsername(username);
        m.setPasswd(EncodingUtils.Encoding(passwd));
        m.setContactPhone(phone);
        m.setMemberPoints(score);
        m.setBalance(new BigDecimal(balance));
        m.setUpdatetime(new Timestamp(new Date().getTime()));

        int result=shopMemberService.editMember(m);

        if (result<1){
            System.out.println("修改失败！");
            return;
        }
        System.out.println("修改成功");
    }

    private void addShopMember() {
        System.out.println("请输入会员名");
        String username = sc.next();
        System.out.println("请输入密码");
        String passwd = sc.next();
        if (passwd.length() <= 0) {
            System.out.println("密码不能为空");
        }
        System.out.println("请输入联系方式");
        String phoneNum = String.valueOf(sc.nextLong());

        passwd = EncodingUtils.Encoding(passwd);
        ShopMember m = new ShopMember(username, passwd, phoneNum, new Timestamp(new Date().getTime()), null);
        int result = shopMemberService.addMember(m);

        if (result < 1) {
            System.out.println("添加失败！");
            return;
        }
        System.out.println("添加成功");
    }

    private boolean returnMenu() {
        System.out.println("是否继续 y/n");
        if ("y".equalsIgnoreCase(sc.next())) {
            return true;
        }
        return false;
    }


    private void printMenu() {
        System.out.println("***会员管理模块***");
        System.out.println("1.添加会员");
        System.out.println("2.修改会员信息");
        System.out.println("3.删除会员");
        System.out.println("4.查询会员");
        System.out.println("5.会员充值");
        System.out.println("请选择 ");
    }

    private void queryAll() {
        List<ShopMember> shopMemberList = shopMemberService.queryAll();
        shopMemberList.forEach((item) -> {
            System.out.println(item);
        });
    }


    private void queryShopMemberByID() {
        System.out.println("请输入会员ID");
        int id = sc.nextInt();
        ShopMember member = shopMemberService.queryMemberById(id);
        if (member==null){
            System.out.println("您输入的会员编号不存在！");
            return;
        }
        System.out.println(shopMemberService.queryMemberById(id));
    }



}
