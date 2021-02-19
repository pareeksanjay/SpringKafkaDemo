package com.example.library.demo.dao;

import com.example.library.demo.entity.PurchaseOrderRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrderRequest,Long> {
}
