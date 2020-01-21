package com.turkcell.crm.ordermanagement.entity;

import com.turkcell.crm.ordermanagement.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "orders")
public class Order extends BaseEntity {

    @Field("customer")
    private Customer customer;

    @Field("catalog")
    private Catalog catalog;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String staticIP;




}
