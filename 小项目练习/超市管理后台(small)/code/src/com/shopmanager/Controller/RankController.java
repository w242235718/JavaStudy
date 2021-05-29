package com.shopmanager.Controller;

import com.shopmanager.Service.Impl.RankServiceImpl;
import com.shopmanager.Service.RankService;
import com.shopmanager.bean.Rank;


import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class RankController {
    private Scanner sc=null;
    private RankService rankService;
    public RankController() {
        init();
    }

    private void init() {
        sc=new Scanner(System.in);
        rankService=new RankServiceImpl();
    }

    public void run(){
        do {
            System.out.println("***排行统计***");
            System.out.println("1.按月份统计 ");
            System.out.println("2.按商品类别统计 ");
            int choose = sc.nextInt();
            switch (choose){
                case 1:
                    countProductByMonth();
                    break;
                case 2:
                    countProductByType();
                    break;
                default:
                    System.out.println("出错啦！");
            }
        } while (returnMenu());
    }

    private void countProductByType() {
        List<Rank> productList=rankService.countProductByType();
        if (productList==null){
            System.out.println("出错啦！");
            return;
        }
        printProductMap(productList);
    }

    private void countProductByMonth() {
        System.out.println("请输入月份");
        int month = sc.nextInt();
        if (month<1&&month>12){
            System.out.println("请输入数字1-12");
            return;
        }
        List<Rank> productList=rankService.countProductByMonth(month);
        printProductMap(productList);
    }


    private void printProductMap(List<Rank> productList){
        if (productList==null){
            System.out.println("出错啦！");
            return;
        }
        for (int i = 0; i < productList.size(); i++) {
            Rank rank = productList.get(i);
            System.out.print((i+1)+":");
            System.out.print(" 商品id="+ rank.getGname());
            System.out.print("\t销售量="+ rank.getSales());
            System.out.println();

        }
    }

    private boolean returnMenu() {
        System.out.println("是否继续 y/n");
        if ("y".equalsIgnoreCase(sc.next())){
            return true;
        }
        return false;
    }



}
