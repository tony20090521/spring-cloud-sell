package com.example.product.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class ProductCategory {

//    `category_id` int(11) NOT NULL AUTO_INCREMENT,
//  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
//            `category_type` int(11) NOT NULL COMMENT '类目编号',
//            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    @Id
    @GeneratedValue
    private Integer categoryId;

    /**类目名字*/
    private String categoryName;

    /**类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}
