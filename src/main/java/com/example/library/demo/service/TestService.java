package com.example.library.demo.service;

import com.example.library.demo.dao.PurchaseOrderRepository;
import com.example.library.demo.entity.PurchaseOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {
@Autowired
    PurchaseOrderRepository purchaseOrderRepository;


public Optional<PurchaseOrderRequest> getOrderById (Long Id)
{
   return purchaseOrderRepository.findById(Id);
}
}
