package com.shopmanager.Controller;


import com.shopmanager.Service.Impl.OrderQueryServiceImpl;
import com.shopmanager.Service.OrderQueryService;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;


import java.util.List;
import java.util.Scanner;

public class OrderQueryController {
    private Scanner sc=null;
    private OrderQueryService orderQueryService;
    public OrderQueryController() {
        init();
    }

    private void init() {
        sc=new Scanner(System.in);
        orderQueryService=new OrderQueryServiceImpl();
    }

    public void run(){
        do {
            System.out.println("***订单查询***");
            System.out.println("请输入会员编号");
            int uid = sc.nextInt();
            List<Order> orders = queryAndGetOrders(uid);

            if (orders.size()<=0){
                System.out.println("会员编号不存在!");
                continue;
            }
            System.out.println("1.展示所有订单");
            System.out.println("2.根据商品id查询订单信息");
            System.out.println("请输入");
            int choose = sc.nextInt();
            switch (choose){
                case 1:
                    queryAllOrder(orders);
                    break;
                case 2:
                    queryOrderById(orders);
                    break;
                default:
                    System.out.println("出错啦！");
            }
        } while (returnMenu());
    }

    private List<Order> queryAndGetOrders(int uid) {
        List<Order> orderList=orderQueryService.queryAndGetOrders(uid);
        return orderList;
    }

    private void queryOrderById(List<Order> orders) {

        System.out.println("请输入商品id");
        int gid = sc.nextInt();
        int nullCounter=0;

        for (Order order : orders) {
            OrderInfo orderInfo = orderQueryService.querySpecificOrder(order, gid);
            if(orderInfo==null){
                nullCounter++;
                continue;
            }
            System.out.println(order);
            System.out.println(orderInfo);
        }
        System.out.println(nullCounter==orders.size()?"没有订单记录":"");

    }

    private void queryAllOrder(List<Order> orders) {

        orders.stream().forEach((orderItem)->{
            List<OrderInfo> orderInfoList=orderQueryService.queryAllOrder(orderItem);
            System.out.print("订单编号:"+orderItem.getOid());
            System.out.print("\t用户id:"+orderItem.getUid());
            System.out.print("\t\t订单总额:￥"+orderItem.getOrdertotal());
            System.out.print("\t下单时间:"+orderItem.getPaytime());
            System.out.print("\t付款方式:"+(orderItem.getPaymethod()==0?"现金":"会员卡支付"));
            System.out.println();
            orderInfoList.forEach((item)->{
                Goods g=orderQueryService.queryGoods(item.getGid());
                System.out.print("\t商品编号:"+g.getGid());
                System.out.print("\t商品名称:"+g.getGname());
                System.out.print("\t商品单价:￥"+g.getPrice());
                System.out.print("\t\t购买数量:"+item.getOrdernum());
                System.out.println();
            });
        });
    }

    private boolean returnMenu() {
        System.out.println("是否继续 y/n");
        if ("y".equalsIgnoreCase(sc.next())){
            return true;
        }
        return false;
    }




}
