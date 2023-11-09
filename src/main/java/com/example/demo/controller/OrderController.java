package com.example.demo.controller;

import com.example.demo.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "choose one of status : DONE, CANCELED, PAYMENT_CONFIRM, PENDING")
    @GetMapping("/admin/order/get-all")
    public ResponseEntity<?> getAllOrder(@RequestParam(required = false) String status,
                                         @RequestParam(required = false) String page,
                                         @RequestParam(required = false) String limit) {
        return ResponseEntity.ok(orderService.getAllOrder(status, page, limit));
    }
}