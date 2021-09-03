package com.example.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name= "orders_status")
data class OrderStatus(val orderId:Long,
                       val orderStatusId:Int,
                       val orderStatusName:String="",
                       val orderStatusMsg:String="")
{
    @Id
    @GeneratedValue
    var orderStatusRegId:Long=0
}