package com.shopmanager.Controller;

import com.shopmanager.Service.GoodsTypeService;
import com.shopmanager.Service.Impl.GoodsTypeServiceImpl;
import com.shopmanager.bean.GoodsType;
import com.shopmanager.utils.GoodsConst;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

public class GoodsTypeController {
    private Scanner sc=null;
    private GoodsTypeService goodsTypeService;
    public GoodsTypeController() {
        init();
    }

    private void init() {
        sc=new Scanner(System.in);
        goodsTypeService=new GoodsTypeServiceImpl();
    }

    public void run(){
        do {
            printMenu();
            int choose = sc.nextInt();
            switch (choose){
                case 1:
                    addProductType();
                    break;
                case 2:
                    editProductType();
                    break;
                case 3:
                    deleteProductType();
                    break;
                case 4:
                    queryProductTypeById();
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
        if ("y".equalsIgnoreCase(sc.next())){
            return true;
        }
        return false;
    }


    private void printMenu() {
        System.out.println("***商品类型管理***");
        System.out.println("1.添加商品类型");
        System.out.println("2.修改商品类型");
        System.out.println("3.删除商品类型");
        System.out.println("4.根据id查询类型");
        System.out.println("5.查询所有商品类型");
        System.out.println("请选择 ");
    }

    private void queryAll() {
        List<GoodsType> products=goodsTypeService.queryAll();
        printProductAndSubProducts(products);

    }

    private void printProductAndSubProducts(List<GoodsType> products) {
        products.stream().forEach(new Consumer<GoodsType>() {
            @Override
            public void accept(GoodsType goodsType) {
                print(products,goodsType.getIsParentType()==true?goodsType:null ,"|");
            }
        });
    }
    private void print(List<GoodsType> products, GoodsType g, String sep) {
        if (g==null){
            return;
        }
        if (g.getPid()==0){
            System.out.println(g.getTypeName());
        }
        for (GoodsType product : products) {
            if (g.getId()==product.getPid()){
                System.out.println(" "+product.getId()+sep+product.getTypeName());
                print(products,product,sep+"|");
            }
        }

    }

    private void queryProductTypeById() {
        System.out.println("请输入要查询的商品类型ID");
        int id=sc.nextInt();
        GoodsType goodsType = goodsTypeService.queryProductTypeById(id);
        if (goodsType==null){
            System.out.println("商品类型不存在！");
            return;
        }
        System.out.println(goodsType);
    }

    private void deleteProductType() {
        System.out.println("请输入要删除的商品类型ID");
        int id=sc.nextInt();
        int result=goodsTypeService.deleteProductType(id);
        if (result<1){
            System.out.println("删除失败");
            return;
        }
        System.out.println("删除成功");
    }

    private void editProductType() {
        GoodsType g=null;

        System.out.println("请输入要修改商品类型的ID");
        int id=sc.nextInt();

        if ((g=goodsTypeService.queryProductTypeById(id))==null){
            System.out.println("您输入的商品类型不存在！");
            return;
        }
        System.out.println("请输入商品类型的父ID");
        int pid = sc.nextInt();
        System.out.println("请输入商品类型名称");
        String typeName = sc.next();
        System.out.println("是否设置该商品类型为父类型 y/n");
        boolean isParentType="y".equalsIgnoreCase(sc.next())? GoodsConst.IS_PARENT_TYPE :GoodsConst.IS_NOT_PARENT_TYPE;

        g.setPid(pid);
        g.setTypeName(typeName);
        g.setIsParentType(isParentType);

        int result=goodsTypeService.editGoodType(g);

        if (result<1){
            System.out.println("修改失败！");
            return;
        }
        System.out.println("修改成功");
    }

    private void addProductType() {
        System.out.println("请输入商品类型的父ID");
        int pid = sc.nextInt();
        if (pid<=0){
            System.out.println("父id不能为负数或零");
            return;
        }
        System.out.println("请输入商品类型名称");
        String typeName = sc.next();
        System.out.println("是否设置该商品类型为父类型 y/n");
        boolean isParentType="y".equalsIgnoreCase(sc.next())? GoodsConst.IS_PARENT_TYPE :GoodsConst.IS_NOT_PARENT_TYPE;

        GoodsType goodsType = new GoodsType(pid, typeName, isParentType, null, null);
        int result=goodsTypeService.addProductType(goodsType);
        if (result<1){
            System.out.println("添加失败！");
            return;
        }
        System.out.println("添加成功");
    }

}
