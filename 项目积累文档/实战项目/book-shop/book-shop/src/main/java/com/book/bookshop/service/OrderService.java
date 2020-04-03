package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.*;
import com.book.bookshop.mapper.OrderMapper;
import com.book.bookshop.utils.OrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: jzhang
 * @Date: 2019/9/29 16:36
 * @Description:
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper,Order> {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CartService cartService;

    /**
     * 购买
     */
    public String buy(List<CartVo> cartVos,Integer addrId, HttpSession session){
        //1.生成订单表记录
        Order order = new Order();
        order.setAddressId(addrId);
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        order.setCreateDate(new Date());
//        order.setOrderNum(UUID.randomUUID().toString());
        order.setOrderNum(OrderUtils.createOrderNum());//自定义生成订单编号
        order.setOrderStatus("1");
        orderMapper.insert(order);

        //2.生成订单明细表记录
        List<OrderItem> orderItems = new ArrayList<>();
        List<Integer> cartIds = new ArrayList<>();
        for (CartVo cart: cartVos) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(cart.getBookId());
            orderItem.setCount(cart.getCount());
            orderItem.setOrderId(order.getId());
            orderItems.add(orderItem);
            cartIds.add(cart.getId());
        }
        orderItemService.saveBatch(orderItems);

        //3.删除购物车表中记录
        cartService.removeByIds(cartIds);
        return "success";
    }

    /**
     * 查询用户订单
     */
    public List<Order> findUserOrder(Integer userId,OrderQueryVo orderQueryVo){
        Integer begin = (orderQueryVo.getPage() - 1) * orderQueryVo.getPageSize();
        Integer end = orderQueryVo.getPage() * orderQueryVo.getPageSize();
        orderQueryVo.setBegin(begin);
        orderQueryVo.setEnd(end);
        orderQueryVo.setUserId(userId);
        List<Order> list = orderMapper.findOrderAndOrderDetailListByUser(orderQueryVo);
        for (Order order: list) {
            List<OrderItem> items = order.getOrderItems();
            double price = 0.0;
            for (OrderItem item:items) {
                price += item.getCount() * item.getBook().getNewPrice();
            }
            order.setTotalPrice(price);//计算订单总金额
        }
        return list;
    }

    /**
     * 查询总页数
     */
    public Integer findUserOrderPages(Integer userId,OrderQueryVo orderQueryVo){
        orderQueryVo.setUserId(userId);
        int count = orderMapper.findOrderCountByUser(orderQueryVo);
        return (count - 1) / orderQueryVo.getPageSize() + 1;
    }
}
