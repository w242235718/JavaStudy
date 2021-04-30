package com.demo.sharedbike.DAO;

import com.demo.sharedbike.Const.SharedBikeConst;
import com.demo.sharedbike.bean.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class SharedBikeImpl implements SharedBikeDao{
    private SharedBikeCompany[] sharedBikeCompanies;
    private Scanner input;
    private DateFormat dataFormat;


    @Override
    public void putBikes() {
        SharedBikeCompany company = getShareBikeCompany(sharedBikeCompanies, input);
        System.out.println("请输入要投放的数量:");
        int num = input.nextInt();
//        company.setSharedBikes(AddBike(company.getSharedBikes(),num));
        doAddBike(company,num);
    }

    @Override
    public void deleteBike() {
        SharedBikeCompany company = getShareBikeCompany(sharedBikeCompanies, input);
        System.out.println("请输入要删除单车的编号");
        int no=input.nextInt();
        doDeleteBike(no,company);

    }

    @Override
    public void borrowBike() {
        SharedBikeCompany company = getShareBikeCompany(sharedBikeCompanies, input);
        printCompanyBikes(company.getSharedBikes());
        System.out.println("请输入要借出的单车编号");
        int no = input.nextInt();
        doBorrowBike(company,no);
    }

    @Override
    public void returnBike() {
        SharedBikeCompany company = getShareBikeCompany(sharedBikeCompanies, input);
        System.out.println("请输入要归还的单车编号");
        int no = input.nextInt();
        doReturnBike(company,no);
    }

    @Override
    public void sortCounts() {
        SharedBikeCompany[] companies = this.sharedBikeCompanies;
        SharedBikeCompany temp;
        for (int i = 0; i < companies.length-1; i++) {
            for (int j = i; j < companies.length-i-1; j++) {
                if (companies[j].getCount() > companies[j + 1].getCount()){
                    temp = companies[j];
                    companies[j]=companies[j+1];
                    companies[j+1]=temp;
                }
            }
        }

        System.out.println("公司名称\t\t借车次数");
        for (SharedBikeCompany company : companies) {
            System.out.print(company.getBikeCompanyName()+"\t\t");
            System.out.print(company.getCount());
            System.out.println();
        }

    }

    private void doReturnBike(SharedBikeCompany company, int no) {
        SharedBike bike = findBikeByNo(company, no);
        if (bike==null){
            System.out.println("该单车不存在！");
            return;
        }
        if (bike.getStatus()==SharedBikeConst.BIKE_AVAILABLE){
            System.out.println("该单车未借出!");
            return;
        }

        Date returnTime = new Date();
        Date borrowTime=null;
        try {
             borrowTime = dataFormat.parse(bike.getBorrowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long stayHours=(returnTime.getTime()-borrowTime.getTime())/(60*60*1000);
        long fee=stayHours*1;
        System.out.println("用车时间为"+(stayHours/24)+"天"+(stayHours%24)+"小时，需要支付【"+fee+"】元");

        bike.setStatus(SharedBikeConst.BIKE_AVAILABLE);
        bike.setBorrowTime(null);
        company.setCount(company.getCount()+1);
    }


    private void doBorrowBike(SharedBikeCompany company, int no) {

        SharedBike bike = findBikeByNo(company, no);
        if (bike==null){
            System.out.println("该单车不存在！");
            return;
        }
        if (bike.getStatus()==SharedBikeConst.BIKE_NOT_AVAILABLE){
            System.out.println("该单车已借出!");
            return;
        }
        System.out.println("请输入借出日期(2021-4-29 12:30:22)");
        input.nextLine();
        String borrowTime=input.nextLine();
        bike.setBorrowTime(borrowTime);
        bike.setStatus(SharedBikeConst.BIKE_NOT_AVAILABLE);
        System.out.println("借出"+company.getBikeCompanyName()+"单车公司下的["+bike.getBikeName()+"]单车成功");
    }

    private SharedBike findBikeByNo(SharedBikeCompany company, int no) {
        SharedBike[] bikes = company.getSharedBikes();
        Integer bikeSum = company.getSum();
        for (int i = 0; i < bikeSum; i++) {
            if (bikes[i].getBikeId() == no) {
                return bikes[i];
            }
        }
        return null;
    }


    private void doDeleteBike(int no, SharedBikeCompany company) {
        Integer bikeTotal = company.getSum();
        SharedBike[] bikes = company.getSharedBikes();
        int bikeIndex=0;
        for (int i = 0; i < bikeTotal; i++) {
            if (bikes[i].getBikeId()==no){
                 bikeIndex=i;
            }
        }
        if (bikes[bikeIndex]==null){
            System.out.println("共享单车已清空，不能删除！");
            return;
        }
        if (bikes[bikeIndex].getStatus()==SharedBikeConst.BIKE_NOT_AVAILABLE){
            System.out.println("该单车目前已借出，无法删除!请归还后再进行相应操作");
            return;
        }

        for (int i =  bikeIndex; i < bikeTotal-1; i++) {
            bikes[i]=bikes[i+1];
        }
        bikes[bikeTotal-1]=null;
        company.setSum(bikeTotal-1);
        company.setSharedBikes(bikes);
    }


    private void doAddBike(SharedBikeCompany company, int num) {
        SharedBike[] bikes = company.getSharedBikes();
        Integer bikeId = bikes.length;

        if (isIncreaseCapacity(bikes,num)){
            System.out.println("自动扩容");
            bikes= Arrays.copyOf(bikes, bikes.length+num);
        }
        SharedBike[] sharedBikes = generateBikes(bikeId, bikes);
        company.setSharedBikes(sharedBikes);
        company.setSum(sharedBikes.length);
    }

    private boolean isIncreaseCapacity(SharedBike[] bikes, int num) {
        int leftCount=0;
        for (int i = 0; i < bikes.length; i++) {
            if (bikes[i]==null){
                leftCount++;
            }
        }
        return leftCount<num;
    }




    @Override
    public void selectAllBike() {

        for (int i = 0; i < sharedBikeCompanies.length; i++) {
            SharedBikeCompany company = sharedBikeCompanies[i];
            System.out.println("公司序号\t|\t公司名称\t|\t公司单车数量\t|\t公司单车借出数量");
            System.out.print("\t"+company.getBikeCompanyId()+"\t");
            System.out.print("\t"+company.getBikeCompanyName()+"\t");
            System.out.print("\t\t"+company.getSum()+"\t");
            System.out.print("\t\t"+company.getCount()+"\t");
            printCompanyBikes(company.getSharedBikes());
            System.out.println();



        }
    }


    private SharedBike[] generateBikes(Integer bikeId,SharedBike[] sharedBikes){
        for (Integer i = bikeId; i < sharedBikes.length; i++) {
            if (sharedBikes instanceof OfoBike[]){
                sharedBikes[i]=new OfoBike((i+1),SharedBikeConst.OFO_BIKE_NAME+(i+1),SharedBikeConst.BIKE_AVAILABLE,null);
            }else if (sharedBikes instanceof HelloBike[]){
                sharedBikes[i]=new HelloBike((i+1),SharedBikeConst.HELLO_BIKE_NAME+(i+1),SharedBikeConst.BIKE_AVAILABLE,null);
            }else if (sharedBikes instanceof MoBike[]){
                sharedBikes[i]=new MoBike((i+1),SharedBikeConst.MOBIKE_BIKE_NAME+(i+1),SharedBikeConst.BIKE_AVAILABLE,null);
            }else{
                return null;
            }
        }
        return sharedBikes;
    }

    private SharedBikeCompany getShareBikeCompany(SharedBikeCompany[] sharedBikeCompanies, Scanner input) {
        SharedBikeCompany sharedBikeCompany=null;

        for (int i = 0; i < sharedBikeCompanies.length; i++) {
            SharedBikeCompany company = sharedBikeCompanies[i];
            System.out.println(company.getBikeCompanyId()+": "+company.getBikeCompanyName());
        }
        System.out.println("请选择要投放的单车品牌:");
        int brandId = input.nextInt();


        for (int i = 0; i < sharedBikeCompanies.length; i++) {
            if (sharedBikeCompanies[i].getBikeCompanyId()==brandId){
                sharedBikeCompany = sharedBikeCompanies[i];
            }
        }

        return sharedBikeCompany;
    }

    private void printCompanyBikes(SharedBike[] sharedBikes) {
        System.out.println();
        System.out.println("单车序号\t|\t单车品牌\t|\t单车状态\t|\t借出时间");
        for (int i = 0; i < sharedBikes.length; i++) {
            SharedBike bike = sharedBikes[i];
            if (bike == null) {
                return;
            }
            System.out.print("\t"+bike.getBikeId());
            System.out.print("\t\t"+bike.getBikeName());
            System.out.print("\t\t"+(bike.getStatus()== SharedBikeConst.BIKE_AVAILABLE?"可用":"不可用"));
            System.out.print("\t\t"+(bike.getBorrowTime()==null?"":bike.getBorrowTime()));
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public SharedBikeImpl(SharedBikeCompany[] sharedBikeCompanies, Scanner input) {
        this.sharedBikeCompanies = sharedBikeCompanies;
        this.input = input;
        this.dataFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public SharedBikeImpl(){

    }
}
