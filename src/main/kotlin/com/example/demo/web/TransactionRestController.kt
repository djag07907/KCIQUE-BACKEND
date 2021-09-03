package com.example.demo.web

import com.example.demo.bussines.iTransactionBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Transaction
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigInteger

@RestController
@RequestMapping(Constants.URL_BASE_TRANSACTIONS)
class TransactionRestController {

    @Autowired
    val transactionBusiness: iTransactionBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Transaction>> {
        return try {
            ResponseEntity(transactionBusiness!!.getTransactions(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idTransaction:Long): ResponseEntity<Transaction> {
        return try {
            ResponseEntity(transactionBusiness!!.getTransactionById(idTransaction), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/customerDni/{customerDni}")
    fun loadByCustomerDni(@PathVariable("customerDni") customerDni:String): ResponseEntity<Transaction> {
        return try {
            ResponseEntity(transactionBusiness!!.getTransactionByCustomerDni(customerDni), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/customerMobile/{customerMobile}")
    fun loadByCustomerMobile(@PathVariable("customerMobile") customerMobile:Int): ResponseEntity<Transaction> {
        return try {
            ResponseEntity(transactionBusiness!!.getTransactionByCustomerMobile(customerMobile), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/shopname/{shopname}")
    fun loadByShopName(@PathVariable("shopname") shopName:String): ResponseEntity<Transaction> {
        return try {
            ResponseEntity(transactionBusiness!!.getTransactionByShopName(shopName), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/shopid/{shopid}")
    fun loadByShopId(@PathVariable("shopid") shopId:Int): ResponseEntity<Transaction> {
        return try {
            ResponseEntity(transactionBusiness!!.getTransactionByShopId(shopId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/orderid/{orderid}")
    fun loadByOrderId(@PathVariable("orderid") orderId:Int): ResponseEntity<Transaction> {
        return try {
            ResponseEntity(transactionBusiness!!.getTransactionByOrderId(orderId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addTransaction")
    fun insert(@RequestBody transaction: Transaction): ResponseEntity<Any> {
        return try {
            transactionBusiness!!.saveTransaction(transaction)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_TRANSACTIONS +"/"+transaction.transactionRegId)
            ResponseEntity(transaction, responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            //return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion Enviada no es Valida",
                e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Para agregar varios TEST
    @PostMapping("/addTransactions")
    fun insert(@RequestBody transactions:List<Transaction>): ResponseEntity<Any> {
        return try {
            ResponseEntity(transactionBusiness!!.saveTransactions(transactions), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody transaction: Transaction): ResponseEntity<Any> {
        return try {
            transactionBusiness!!.updateTransaction(transaction)
            ResponseEntity(transaction, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idTransaction: Long): ResponseEntity<Any> {
        return try {
            transactionBusiness!!.removeTransaction(idTransaction)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}