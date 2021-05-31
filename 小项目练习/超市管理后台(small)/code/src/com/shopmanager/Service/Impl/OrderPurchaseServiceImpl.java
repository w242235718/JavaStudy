package com.shopmanager.Service.Impl;

import com.shopmanager.DAO.Impl.OrderPurchaseDaoImpl;
import com.shopmanager.DAO.OrderPurchaseDao;
import com.shopmanager.Service.OrderPurchaseService;
import com.shopmanager.bean.Goods;
import com.shopmanager.bean.Order;
import com.shopmanager.bean.OrderInfo;
import com.shopmanager.bean.ShopMember;
import com.shopmanager.common.GoodsNotFoundException;
import com.shopmanager.common.PurchaseException;
import com.shopmanager.utils.OrderPurchaseUtil;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class OrderPurchaseServiceImpl implements OrderPurchaseService {
    private OrderPurchaseDao orderPurchaseDao = new OrderPurchaseDaoImpl();


    @Override
    public Map<String, OrderInfo> addProductToShoppingCart(Map<String, OrderInfo> shoppingCart, Scanner sc) {
        //添加购物车
        do {
            System.out.println("请输入商品id");
            int id = sc.nextInt();
            Goods g = null;
            try {
                g = orderPurchaseDao.selectGoodsById(id);
                if (g == null) {
                    try {
                        throw new GoodsNotFoundException("商品【" + id + "】不存在!");
                    } catch (GoodsNotFoundException e) {
                        System.out.println(e.getMessage());
                        shoppingCart = addProductToShoppingCart(shoppingCart, sc);
                        return shoppingCart;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            System.out.print("\t商品编号:" + g.getGid());
            System.out.print("\t商品名称:" + g.getGname());
            System.out.print("\t商品价格:" + g.getPrice());
            System.out.println();
            System.out.println("请输入购买数量");
            int purchaseNum = sc.nextInt();

            //生成订单详情表
            OrderInfo orderInfo = new OrderInfo(null, g.getGid(), purchaseNum);
            //判断购物车中是否已经存在
            if (shoppingCart.containsKey(String.valueOf(g.getGid()))) {
                //累加库存
                OrderInfo od = shoppingCart.get(String.valueOf(g.getGid()));
                od.setOrdernum(od.getOrdernum() + purchaseNum);
                continue;
            }
            //不存在
            shoppingCart.put(String.valueOf(g.getGid()), orderInfo);

        } while (isAddMore(sc));
        return shoppingCart;
    }

    @Override
    public int checkOut(Map<String, OrderInfo> shoppingCart, Scanner sc) {
        int oid = 0;
        int row = 0;
        ShopMember sm = null;
        double orderTotal = 0.0;
        System.out.println("是否使用会员卡支付? y/n");
        try {
            //获取订单总额
            orderTotal = getOrderTotal(shoppingCart);
            //生成订单信息表
            if (!"y".equalsIgnoreCase(sc.next())) {
                //现金支付
                oid = payByCash(orderTotal, null);
                //减去库存
                System.out.println(subStore(shoppingCart));
            } else {
                //会员卡支付
                System.out.println("请输入会员卡(编)号");
                int uid = sc.nextInt();
                if ((sm = orderPurchaseDao.selectUserById(uid)) == null) {
                    System.out.println("会员卡号不存在");
                    return -1;
                }
                //会员卡余额是否充足
                double balance = sm.getBalance().doubleValue();
                if (balance > orderTotal) {
                    //更新会员余额
                    sm.setBalance(new BigDecimal((balance - orderTotal)));
                    row += orderPurchaseDao.updateMember(sm);
                    //生成订单
                    Order vipOrder = new Order(uid, new BigDecimal(orderTotal), new Timestamp(new Date().getTime()), 1);
                    oid = orderPurchaseDao.insertOrder(vipOrder);
                    //减去库存
                    System.out.println(subStore(shoppingCart));
                } else {
                    //不充足,现金支付
                    System.out.println("会员卡余额不足,改为现金支付");
                    oid = payByCash(orderTotal, uid);
                    System.out.println(subStore(shoppingCart));
                }
            }
            //生成订单详情表
            Set<Map.Entry<String, OrderInfo>> cart = shoppingCart.entrySet();
            for (Map.Entry<String, OrderInfo> item : cart) {
                OrderInfo o = item.getValue();
                o.setOid(oid);
                shoppingCart.put(item.getKey(), o);
            }
            Set<Map.Entry<String, OrderInfo>> orderCart = shoppingCart.entrySet();
            for (Map.Entry<String, OrderInfo> item : orderCart) {
                OrderInfo orderInfo = item.getValue();
                row = orderPurchaseDao.insertOrderInfo(orderInfo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (PurchaseException e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return row;
    }

    private boolean subStore(Map<String, OrderInfo> shoppingCart) throws SQLException, PurchaseException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int row = 0;
        Set<Map.Entry<String, OrderInfo>> cartSet = shoppingCart.entrySet();
        for (Map.Entry<String, OrderInfo> item : cartSet) {
            OrderInfo o = item.getValue();
            row += orderPurchaseDao.updateGoodsStore(o.getGid(), o.getOrdernum());
        }
        return row == cartSet.size() ? true : false;
    }

    private double getOrderTotal(Map<String, OrderInfo> shoppingCart) throws SQLException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Double total = 0.0;
        Set<String> keySet = shoppingCart.keySet();
        for (String key : keySet) {
            OrderInfo orderInfo = shoppingCart.get(key);
            Goods g = orderPurchaseDao.selectGoodsById(orderInfo.getGid());
            total += g.getPrice().doubleValue() * orderInfo.getOrdernum();
        }
        return total;
    }

    /**
     * 修改购物车中的商品数量
     *
     * @param shoppingCart
     * @param sc
     * @return
     * @throws GoodsNotFoundException
     */
    @Override
    public Map<String, OrderInfo> editProductStoreInShoppingCart(Map<String, OrderInfo> shoppingCart, Scanner sc) throws GoodsNotFoundException {
        System.out.println("请输入购物车中要修改的商品id");
        int gid = sc.nextInt();

        if (!shoppingCart.containsKey(String.valueOf(gid))) {
            throw new GoodsNotFoundException("商品【" + gid + "】不存在");
        }

        System.out.println("请输入要修改的数量");
        int orderNum = sc.nextInt();

        OrderInfo orderInfo = shoppingCart.get(String.valueOf(gid));
        orderInfo.setOrdernum(orderNum);
        shoppingCart.put(String.valueOf(gid), orderInfo);

        return shoppingCart;
    }

    @Override
    public Map<String, OrderInfo> deleteProductInShoppringCart(Map<String, OrderInfo> shoppingCart, Scanner sc) throws GoodsNotFoundException {
        System.out.println("请输入要删除购物车中的商品id");
        int gid = sc.nextInt();
        if (!shoppingCart.containsKey(String.valueOf(gid))) {
            throw new GoodsNotFoundException("商品【" + gid + "】不存在");
        }
        shoppingCart.remove(String.valueOf(gid));
        return shoppingCart;
    }

    @Override
    public Goods querySingleItem(OrderInfo item) {
        Goods g = new Goods();
        try {
            g = orderPurchaseDao.selectGoodsById(item.getGid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return g;
    }


    private int payByCash(double orderTotal, Integer uid) throws SQLException, IllegalAccessException {
        System.out.println("订单总金额为:" + orderTotal);
        Order cashOrder = new Order((uid == null ? OrderPurchaseUtil.getNonVipID() : uid), new BigDecimal(orderTotal), new Timestamp(new Date().getTime()), 0);
        return orderPurchaseDao.insertOrder(cashOrder);
    }

    private boolean isAddMore(Scanner sc) {
        sc.nextLine();
        System.out.println("是否继续添加商品 y/n");
        String flag = sc.next();
        if (!"y".equalsIgnoreCase(flag)) {
            return false;
        }
        return true;
    }


}
