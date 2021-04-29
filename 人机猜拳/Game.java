package com.demo.homework;

import java.util.Scanner;

public class Game {
    private Scanner input;
    private Computer c;
    private User u;
    private int count;

    public Game(){
        initial();
    }

    public void startGame(){
        String isGoOn;
        int userFist;
        int systemFist;

        System.out.println("要开始吗? (y/n)");
        if (!"y".equalsIgnoreCase(input.next())){
            return;
        }

        do {
            userFist=u.showFist(input);
            systemFist=c.showFist(input);
            compareFist(userFist,systemFist);
            count++;
            System.out.println("是否开始下一轮(y/n)");
            isGoOn=input.next();
        } while ("y".equalsIgnoreCase(isGoOn));

        printResult();

    }

    private void printResult() {
        System.out.println("------------------------------");
        System.out.println("您("+u.getUserName()+")\t Vs \t"+c.getCharacter());
        System.out.println("对战次数"+count);
        System.out.println("姓名\t\t\t\t得分");
        System.out.println("您("+u.getUserName()+")\t\t\t"+u.getWinCount());
        System.out.println(c.getCharacter()+"\t\t\t\t"+c.getWinCount());
        System.out.println("平局"+(count-(c.getWinCount()+u.getWinCount()))+"次");
        System.out.println((c.getWinCount()>u.getWinCount()?c.getCharacter()+"获胜":"您获胜"));
        System.out.println("------------------------------");
    }

    private void compareFist(int userFist, int systemFist) {
        if ((userFist==1&&systemFist==3)||(userFist==2&&systemFist==1)||(userFist==3&&systemFist==2)){
            u.addWinCount();
            System.out.println("恭喜,您赢了！");
        }else if(userFist==systemFist){
            System.out.println("平局");
        }else{
            c.addWinCount();
            System.out.println("很遗憾,您输了！");
        }
    }


    public void initial(){
        input=new Scanner(System.in);
        c=new Computer();
        u=new User();

        printWelcome();
        u.registryUserName(input);
        c.chooseRole(input);

    }

    private void printWelcome() {
        System.out.println("*******************欢迎进入游戏世界*******************");
        System.out.println("**********\t\t\t猜拳 Start！\t\t\t************");
        System.out.println("***********************************");
        System.out.println("出拳规则: 1、剪刀 2、石头 3、布");
        System.out.println();
    }


}
