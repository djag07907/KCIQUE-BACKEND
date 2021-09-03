package com.example.demo.bussines

import com.example.demo.model.Transaction
import java.math.BigInteger


interface iTransactionBusiness {
    fun getTransactions():List<Transaction>
    fun getTransactionById(transactionId: Long): Transaction
    fun getTransactionByCustomerDni(customerDni:String): Transaction
    fun getTransactionByCustomerMobile(customerMobile:Int):Transaction
    fun getTransactionByShopId(shopId:Int):Transaction
    fun getTransactionByShopName(shopName:String):Transaction
    fun getTransactionByOrderId(orderId:Int):Transaction
    fun saveTransaction(transaction: Transaction): Transaction
    fun saveTransactions(transaction: List<Transaction>):List<Transaction>
    fun removeTransaction(transactionId: Long)
    fun updateTransaction(transaction: Transaction): Transaction
}