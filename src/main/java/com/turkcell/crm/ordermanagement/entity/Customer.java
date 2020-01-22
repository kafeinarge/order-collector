package com.turkcell.crm.ordermanagement.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(of = "identityNumber")
@Data
public class Customer {

    private String name;

    private String identityNumber;

}
