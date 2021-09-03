package com.example.demo.web

import com.example.demo.bussines.iOrderBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Order
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_ORDERS)
class OrderRestController {

    @Autowired
    val orderBusiness:iOrderBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Order>> {
        return try {
            ResponseEntity(orderBusiness!!.getOrders(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idOrder: Long): ResponseEntity<Order> {
        return try {
            ResponseEntity(orderBusiness!!.getOrderByOrderId(idOrder), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/shopId/{shopId}")
    fun loadByShopId(@PathVariable("shopId") shopId:Int): ResponseEntity<Order> {
        return try {
            ResponseEntity(orderBusiness!!.getOrderByShopId(shopId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/customerDni/{customerDni}")
    fun loadByCustomerDni(@PathVariable("customerDni") customerDni:String): ResponseEntity<Order> {
        return try {
            ResponseEntity(orderBusiness!!.getOrderByCustomerDni(customerDni), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/orderName/{orderName}")
    fun loadByOrderName(@PathVariable("orderName") orderName:String): ResponseEntity<Order> {
        return try {
            ResponseEntity(orderBusiness!!.getOrderByOrderName(orderName), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addOrder")
    fun insert(@RequestBody order: Order): ResponseEntity<Any> {
        return try {
            orderBusiness!!.saveOrder(order)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_ORDERS +"/"+order.orderRegId)
            ResponseEntity(order, responseHeader, HttpStatus.CREATED)
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
    @PostMapping("/addOrders")
    fun insert(@RequestBody orders:List<Order>): ResponseEntity<Any> {
        return try {
            ResponseEntity(orderBusiness!!.saveOrders(orders), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody order: Order): ResponseEntity<Any> {
        return try {
            orderBusiness!!.updateOrder(order)
            ResponseEntity(order, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") orderId: Long): ResponseEntity<Any> {
        return try {
            orderBusiness!!.removeOrder(orderId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}