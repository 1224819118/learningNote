package com.book.bookshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: jzhang
 * @Date: 2019/9/29 15:02
 * @Description: 地址实体类
 */
@Data
@TableName(value = "bs_address")
@EqualsAndHashCode(callSuper = false)
public class Address extends Model<Address> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String province;
    private String city;
    private String area;
    private String detailAddress;
    private String emailCode;
    private String receiver;
    private String tel;
    private String isDefault; //是否是默认收货地址

}
