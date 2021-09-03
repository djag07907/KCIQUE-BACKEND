package com.example.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name= "orders" +
        "")
    data class Order(val orderId:Long=0,
                     val itemId:Int,
                     val itemAmount:Int,
                     val shopId:Int,
                     val customerDni:String="",
                     val orderStatusName:String="",
                     val orderName:String="",
                     val orderTotalTopay:Int,
                     val isvType:Double,
                     val isvAmount:Double,
                     val orderDate:String="",
                     val deliveryPrice:Double,
                     val customerAddress:String="")
    {
        @Id
        @GeneratedValue
        var orderRegId:Long=0
    }