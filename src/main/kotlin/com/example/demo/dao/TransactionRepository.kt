package com.example.demo.dao

import com.example.demo.model.Order
import com.example.demo.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import java.math.BigInteger
import java.util.*

interface TransactionRepository: JpaRepository<Transaction, Long> {
    fun findByCustomerMobile(customerMobile:Int): Optional<Transaction>
    fun findByShopName(shopName:String): Optional<Transaction>
    fun findByCustomerDni(customerDni: String): Optional<Transaction>
    fun findByShopId(shopId: Int): Optional<Transaction>
    fun findByOrderId(orderId: Int): Optional<Transaction>
}