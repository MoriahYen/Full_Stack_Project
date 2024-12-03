package com.luv2code.fullstackweb.dto;

import com.luv2code.fullstackweb.entity.Address;
import com.luv2code.fullstackweb.entity.Customer;
import com.luv2code.fullstackweb.entity.Order;
import com.luv2code.fullstackweb.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
