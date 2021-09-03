package com.example.demo.bussines

import com.example.demo.model.PaymentMethod


interface iPaymentMethodBusiness {
    fun getPaymentMethods():List<PaymentMethod>
    fun getPaymentMethodById(paymentMethodId: Long): PaymentMethod
    fun savePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod
    fun savePaymentMethods(paymentMethod: List<PaymentMethod>):List<PaymentMethod>
    fun removePaymentMethod(paymentMethodId: Long)
    fun getPaymentMethodByPaymentName(paymentName: String): PaymentMethod
    fun getPaymentMethodByCustomerDni(customerDni: String): PaymentMethod
    fun updatePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod
}