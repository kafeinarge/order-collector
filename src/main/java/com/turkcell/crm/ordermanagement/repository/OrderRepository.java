package com.turkcell.crm.ordermanagement.repository;

import com.turkcell.crm.ordermanagement.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, Long> {

}
