package com.example.demo.bussines

import com.example.demo.dao.TransactionRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.math.BigInteger
import java.util.*

@Service
class TransactionBusiness: iTransactionBusiness {

    @Autowired
    val transactionRepository: TransactionRepository?=null

    @Throws(BusinessException::class)
    override fun getTransactions(): List<Transaction> {
        try {
            return transactionRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTransactionById(transactionId: Long): Transaction {
        val opt: Optional<Transaction>
        try {
            opt = transactionRepository!!.findById(transactionId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $transactionId")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTransactionByCustomerDni(customerDni: String): Transaction {
        val opt: Optional<Transaction>
        try {
            opt = transactionRepository!!.findByCustomerDni(customerDni)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $customerDni")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTransactionByCustomerMobile(customerMobile: Int): Transaction {
        val opt: Optional<Transaction>
        try {
            opt = transactionRepository!!.findByCustomerMobile(customerMobile)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $customerMobile")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTransactionByShopId(shopId: Int): Transaction {
        val opt: Optional<Transaction>
        try {
            opt = transactionRepository!!.findByShopId(shopId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $shopId")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTransactionByShopName(shopName: String): Transaction {
        val opt: Optional<Transaction>
        try {
            opt = transactionRepository!!.findByShopName(shopName)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $shopName")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTransactionByOrderId(orderId: Int): Transaction {
        val opt: Optional<Transaction>
        try {
            opt = transactionRepository!!.findByOrderId(orderId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $orderId")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun saveTransaction(transaction: Transaction): Transaction {
        try {
            if (transaction.transactionDate.length<3)
                throw BusinessException("Ingrese mas de 3 caracteres")
            return transactionRepository!!.save(transaction)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveTransactions(transaction: List<Transaction>): List<Transaction> {
        try {
            return transactionRepository!!.saveAll(transaction)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeTransaction(transactionId: Long) {
        val opt:Optional<Transaction>
        try {
            opt = transactionRepository!!.findById(transactionId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $transactionId")
        }
        else{
            try {
                transactionRepository!!.deleteById(transactionId)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun updateTransaction(transaction: Transaction): Transaction {
        val opt:Optional<Transaction>
        try {
            opt = transactionRepository!!.findById(transaction.transactionRegId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${transaction.transactionRegId}")
        }
        else{
            try {
                return transactionRepository!!.save(transaction)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

}