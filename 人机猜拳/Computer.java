package com.demo.homework;

import java.util.Scanner;

public class Computer {
    private String character;
    private int winCount;

    public void chooseRole(Scanner input){
        System.out.println("请选择对方角色:(1、刘备 2、孙权 3、曹操)");
        System.out.print("===>");
        int role = input.nextInt();
        switch (role){
            case 1:
                character="刘备";
                break;
            case 2:
                character="孙权";
                break;
            case 3:
                character="曹操";
                break;
            default:
                System.out.println("您输入的数字有误！请输入1-3以内的数字");
                return;
        }
        System.out.println("您选择"+character+"对战");
    }

    public int showFist(Scanner input){
        int random= (int) (Math.random()*2+ 1);
        System.out.println(character+"出拳:"+Util.Fist(random));

        return random;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
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
}
