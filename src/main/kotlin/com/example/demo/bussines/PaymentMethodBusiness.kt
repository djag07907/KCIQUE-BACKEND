package com.example.demo.bussines

import com.example.demo.dao.PaymentMethodRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.model.PaymentMethod
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class PaymentMethodBusiness: iPaymentMethodBusiness {

    @Autowired
    val paymentMethodRepository: PaymentMethodRepository?= null

    @Throws(BusinessException::class)
    override fun getPaymentMethods(): List<PaymentMethod> {
        try {
            return paymentMethodRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    override fun getPaymentMethodById(paymentMethodId: Long): PaymentMethod {
        TODO("Not yet implemented")
    }

    override fun savePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod {
        TODO("Not yet implemented")
    }

    override fun savePaymentMethods(paymentMethod: List<PaymentMethod>): List<PaymentMethod> {
        TODO("Not yet implemented")
    }

    override fun removePaymentMethod(paymentMethodId: Long) {
        TODO("Not yet implemented")
    }

    override fun getPaymentMethodByPaymentName(paymentName: String): PaymentMethod {
        TODO("Not yet implemented")
    }

    override fun getPaymentMethodByCustomerDni(customerDni: String): PaymentMethod {
        TODO("Not yet implemented")
    }

    override fun updatePaymentMethod(paymentMethod: PaymentMethod): PaymentMethod {
        TODO("Not yet implemented")
    }


}