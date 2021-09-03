package com.example.demo.dao

import com.example.demo.model.Seller
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SellerRepository: JpaRepository<Seller,Long> {
    fun findBySellerName(sellerName:String): Optional<Seller>
    fun findByOrderId(orderId: Int): Optional<Seller>
    fun findByShopId(shopId: Int): Optional<Seller>

}