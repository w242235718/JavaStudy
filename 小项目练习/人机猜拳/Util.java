package com.demo.homework;

public class Util {

    public static String Fist(int i){
        String str=null;
        if (!(i>0) && (i<=3)){
            return "输入有误";
        }
        switch (i){
            case 1:
                str= "剪刀";
                break;
            case 2:
                str="石头";
                break;
            case 3:
                str="布";
                break;
        }
        return str;
    }
}
