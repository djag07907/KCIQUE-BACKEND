package com.example.demo.dao

import com.example.demo.model.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrderStatusRepository:JpaRepository<OrderStatus,Long> {
    fun findByOrderStatusName(orderStatusName:String): Optional<OrderStatus>
}