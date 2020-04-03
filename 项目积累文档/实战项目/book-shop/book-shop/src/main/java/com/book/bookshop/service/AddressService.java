package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.Address;
import com.book.bookshop.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
 * @Auther: jzhang
 * @Date: 2019/9/29 15:04
 * @Description:
 */
@Service
public class AddressService extends ServiceImpl<AddressMapper,Address> {
}
