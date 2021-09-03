package com.example.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="paymentMethod")
        data class PaymentMethod(val paymentMethodId:Long,
                         val customerDni:String="",
                         val transactionId:Int,
                         val paymentName:String="")
    {
        @Id
        @GeneratedValue
        val paymentRegId:Long = 0

    }