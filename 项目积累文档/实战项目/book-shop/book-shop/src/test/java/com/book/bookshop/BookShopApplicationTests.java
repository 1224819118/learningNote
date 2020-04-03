package com.book.bookshop;

import com.book.bookshop.entity.Book;
import com.book.bookshop.entity.OrderQueryVo;
import com.book.bookshop.mapper.CartMapper;
import com.book.bookshop.mapper.OrderMapper;
import com.book.bookshop.service.BookService;
import com.book.bookshop.utils.OrderUtils;
import javafx.scene.input.PickResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookShopApplicationTests {
    @Autowired
    private BookService bookService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;


    @Test
    public void findBookList() {
        bookService.list().forEach(System.out::println);
    }

    @Test
    public void findCartList(){
        cartMapper.findCartListByUserId(1).forEach(System.out::println);
    }

    @Test
    public void findOrderList(){
        OrderQueryVo orderQueryVo = new OrderQueryVo();
        orderQueryVo.setUserId(1);
        orderQueryVo.setBegin(0);
        orderQueryVo.setEnd(2);
        orderQueryVo.setOrderBy(" bo.create_date desc");
        orderMapper.findOrderAndOrderDetailListByUser(orderQueryVo);
    }

    @Test
    public void testCreateOrderNo() {
        System.out.println(OrderUtils.createOrderNum());
    }

    @Test
    public void test() {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();//从控制台读入一个整数，也可以在这直接给a赋给初值
        System.out.print(a + "的所有因子是：");
        for (int i = 1; i <= a; i++) {
            if (a % i == 0) { //a对i取余，能除尽i就是a的因子
                System.out.print(i + " ");//打印这个i值
            }

        }
    }
}
