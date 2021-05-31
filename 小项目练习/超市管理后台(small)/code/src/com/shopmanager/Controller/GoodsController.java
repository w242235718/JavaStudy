package com.shopmanager.Controller;

import com.shopmanager.Service.GoodsService;
import com.shopmanager.Service.Impl.GoodsServiceImpl;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.GoodsType;


import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GoodsController {
    private Scanner sc = null;
    private GoodsService goodsService;

    public GoodsController() {
        init();
    }

    private void init() {
        sc = new Scanner(System.in);
        goodsService = new GoodsServiceImpl();
    }

    public void run() {
        do {
            printMenu();
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    queryProductById();
                    break;
                case 5:
                    queryAll();
                    break;
                default:
                    System.out.println("出错啦！");
            }
        } while (returnMenu());
    }

    private boolean returnMenu() {
        System.out.println("是否继续 y/n");
        if ("y".equalsIgnoreCase(sc.next())) {
            return true;
        }
        return false;
    }


    private void printMenu() {
        System.out.println("***商品管理***");
        System.out.println("1.添加商品");
        System.out.println("2.修改商品");
        System.out.println("3.删除商品");
        System.out.println("4.根据id查询商品");
        System.out.println("5.查询所有商品");
        System.out.println("请选择 ");
    }

    private void queryAll() {
        List<Goods> goodsList = goodsService.queryAll();
        goodsList.forEach((item) -> {
            System.out.println(item);
        });
    }

    private void queryProductById() {
        System.out.println("请输入要查询的商品ID");
        int id = sc.nextInt();
        Goods goods = goodsService.queryProductById(id);
        if (goods == null) {
            System.out.println("id为【" + id + "】号的商品不存在");
            return;
        }
        System.out.println(goodsService.queryProductById(id));
    }

    private void deleteProduct() {
        System.out.println("请输入要删除的商品ID");
        int id = sc.nextInt();
        int result = goodsService.deleteProduct(id);
        if (result < 1) {
            System.out.println("删除失败");
            return;
        }
        System.out.println("删除成功");
    }

    private void editProduct() {
        Goods g = null;

        System.out.println("请输入要修改的商品ID");
        int id = sc.nextInt();

        if ((g = goodsService.queryProductById(id)) == null) {
            System.out.println("商品不存在！");
            return;
        }

        System.out.println("请输入商品名称");
        String gName = sc.next();
        System.out.println("请输入商品所属类型(ID)");
        int gtype = sc.nextInt();
        System.out.println("请输入商品库存数量");
        int gstore = sc.nextInt();
        System.out.println("请输入商品单价");
        double price = sc.nextDouble();
        System.out.println("请输入商品状态(1可用/2下架/3删除)");
        int gstatus = sc.nextInt();
        System.out.println("请输入商品折扣");
        float gdiscount = sc.nextFloat();

        g.setGname(gName);
        g.setGtype(gtype);
        g.setGstore(gstore);
        g.setGstatus(gstatus);
        g.setPrice(new BigDecimal(price));
        g.setGdiscount(gdiscount);
        g.setUpdatetime(new Timestamp(new Date().getTime()));

        int result = goodsService.editProduct(g);

        if (result < 1) {
            System.out.println("修改失败！");
            return;
        }
        System.out.println("修改成功");
    }

    private void addProduct() {
        System.out.println("请输入商品名称");
        String gName = sc.next();
        System.out.println("请输入商品所属类型(ID)");
        int gtype = sc.nextInt();
        if (isNegative(new BigDecimal(gtype), "商品所属类型(ID)")) {
            return;
        }
        System.out.println("请输入商品库存数量");
        int gstore = sc.nextInt();
        if (isNegative(new BigDecimal(gstore), "商品库存数量")) {
            return;
        }
        System.out.println("请输入商品单价");
        BigDecimal price = sc.nextBigDecimal();
        if (isNegative(price, "商品单价")) {
            return;
        }
        System.out.println("请输入商品状态(1可用/2下架/3删除)");
        int gstatus = sc.nextInt();
        if (gstatus < 0 || gstatus > 3) {
            System.out.println("请输入【1-3】整数");
            return;
        }
        System.out.println("请输入商品折扣");
        float gdiscount = sc.nextFloat();
        if (gdiscount > 1.1 || gdiscount<0) {
            System.out.println("折扣必须小于或等于1.0");
            return;
        }
        Goods goods = new Goods(gName, gtype, gstore, price, gstatus, gdiscount, new Timestamp(new Date().getTime()), null);
        int result = goodsService.addProduct(goods);
        if (result < 1) {
            System.out.println("添加失败！");
            return;
        }
        System.out.println("添加成功");
    }

    private boolean isNegative(BigDecimal num, String tipMsg) {
        if (num.doubleValue() <= 0) {
            System.out.println(tipMsg + "不能为负数或零");
            return true;
        }
        return false;
    }
}
