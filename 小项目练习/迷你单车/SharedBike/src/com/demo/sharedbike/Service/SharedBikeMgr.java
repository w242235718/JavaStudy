package com.demo.sharedbike.Service;

import com.demo.sharedbike.Const.SharedBikeConst;
import com.demo.sharedbike.DAO.SharedBikeDao;
import com.demo.sharedbike.DAO.SharedBikeImpl;
import com.demo.sharedbike.bean.*;

import java.util.Arrays;
import java.util.Scanner;

public class SharedBikeMgr {
    private Scanner input;
    private SharedBikeCompany[] sharedBikeCompanies;
    private SharedBikeDao sharedBikeDao;

    public void run(){
        while (true) {
            startMenu();
            switch (chooseMenu(input)){
                case 1:
                    sharedBikeDao.putBikes();
                    break;
                case 2:
                    sharedBikeDao.selectAllBike();
                    break;
                case 3:
                    sharedBikeDao.deleteBike();
                    break;
                case 4:
                    sharedBikeDao.borrowBike();
                    break;
                case 5:
                    sharedBikeDao.returnBike();
                    break;
                case 6:
                    sharedBikeDao.sortCounts();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("您输入的数字不合法");
                    return;
            }
        }

    }


    private int chooseMenu(Scanner input) {
        System.out.print("请选择:");
        Integer userInput = input.nextInt();
        if (!(userInput<0)&&(userInput>7)){
            System.out.println("请输入1-7之间的数字");
            userInput =7;
        }
        return userInput;
    }

    public void startMenu(){

        System.out.println("********************************************");
        System.out.println("1、投放单车");
        System.out.println("2、查看单车");
        System.out.println("3、删除Bike");
        System.out.println("4、借出Bike");
        System.out.println("5、归还Bike");
        System.out.println("6、Bike排行榜");
        System.out.println("7、退    出");
        System.out.println("**********************************************");

    }

    public void initial(){
        SharedBike[] ofoBikes=generateBikes(new OfoBike[5], SharedBikeConst.OFO_BIKE_NAME,5);
        SharedBike[] moBikes=generateBikes(new MoBike[5],SharedBikeConst.MOBIKE_BIKE_NAME,5);
        SharedBike[] helloBikes=generateBikes(new HelloBike[5],SharedBikeConst.HELLO_BIKE_NAME,5);

        SharedBikeCompany ofo = new SharedBikeCompany(1, SharedBikeConst.OFO_NAME,
                ofoBikes , 5, 100);
        SharedBikeCompany moBike=new SharedBikeCompany(2,SharedBikeConst.MOBIKE_NAME,
                moBikes,5,200);
        SharedBikeCompany helloBike=new SharedBikeCompany(3,SharedBikeConst.HELLO_NAME,
                helloBikes,5,1000);

        sharedBikeCompanies=new SharedBikeCompany[3];
        sharedBikeCompanies[0]=ofo;
        sharedBikeCompanies[1]=moBike;
        sharedBikeCompanies[2]=helloBike;

        input=new Scanner(System.in);
        sharedBikeDao=new SharedBikeImpl(sharedBikeCompanies,input);

        System.out.println("欢迎使用迷你共享单车管理系统");
//        System.out.println(Arrays.toString(sharedBikeCompanies));
    }

    public SharedBike[] generateBikes(SharedBike[] sharedBikes,String name,Integer num){
        for (Integer i = 0; i < num; i++) {
            if (sharedBikes instanceof OfoBike[]){
                sharedBikes[i]=new OfoBike((i+1),name+(i+1),SharedBikeConst.BIKE_AVAILABLE,null);
            }else if (sharedBikes instanceof HelloBike[]){
                sharedBikes[i]=new HelloBike((i+1),name+(i+1),SharedBikeConst.BIKE_AVAILABLE,null);
            }else if (sharedBikes instanceof MoBike[]){
                sharedBikes[i]=new MoBike((i+1),name+(i+1),SharedBikeConst.BIKE_AVAILABLE,null);
            }else{
                return null;
            }
        }
        return sharedBikes;
    }


    public SharedBikeMgr(){
        initial();
    }
}
