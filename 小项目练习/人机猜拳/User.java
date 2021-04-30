package com.demo.homework;

import java.util.Scanner;

public class User {
    private int winCount;
    private String userName;

    public void registryUserName(Scanner input){
        System.out.print("请输入您的用户名:");
        userName=input.nextLine();
    }

    public int showFist(Scanner input){
        System.out.println("请出拳:(1、剪刀 2、石头 3、布)");
        System.out.print("===>");
        int userInput = input.nextInt();
        System.out.println("您出拳:"+Util.Fist(userInput));
        return userInput;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public void addWinCount() {
        winCount++;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
