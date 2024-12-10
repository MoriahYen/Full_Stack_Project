package com.luv2code.fullstackweb.controller;

import com.luv2code.fullstackweb.dto.Purchase;
import com.luv2code.fullstackweb.dto.PurchaseResponse;
import com.luv2code.fullstackweb.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin("http://localhost:4200")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }
}
