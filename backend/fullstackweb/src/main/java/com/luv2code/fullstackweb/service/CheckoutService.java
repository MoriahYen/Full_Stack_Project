package com.luv2code.fullstackweb.service;

import com.luv2code.fullstackweb.dto.Purchase;
import com.luv2code.fullstackweb.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
