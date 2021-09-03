package com.example.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "seller")
    data class Seller(val sellerId:Long,
                      val userId:Int,
                      val orderId:Int,
                      val shopId:Int,
                      val sellerName:String="")
    {
        @Id
        @GeneratedValue
        var sellerRegId: Long = 0
    }