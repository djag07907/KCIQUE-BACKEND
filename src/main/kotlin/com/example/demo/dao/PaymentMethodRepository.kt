package com.example.demo.dao

import com.example.demo.model.PaymentMethod
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PaymentMethodRepository: JpaRepository<PaymentMethod, Long> {
    fun findByPaymentName(paymentName: String): Optional<PaymentMethod>
    fun findByCustomerDni(customerDni: String): Optional<PaymentMethod>
}