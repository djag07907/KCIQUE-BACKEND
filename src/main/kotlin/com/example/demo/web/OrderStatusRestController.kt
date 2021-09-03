package com.example.demo.web

import com.example.demo.bussines.iOrderStatusBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.OrderStatus
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_ORDERSTATUSES)
class OrderStatusRestController {

    @Autowired
    val orderStatusBusiness: iOrderStatusBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<OrderStatus>> {
        return try {
            ResponseEntity(orderStatusBusiness!!.getOrderStatuses(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") orderId:Long): ResponseEntity<OrderStatus> {
        return try {
            ResponseEntity(orderStatusBusiness!!.getOrderStatusById(orderId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/orderStatusName/{orderStatusName}")
    fun loadByNombre(@PathVariable("orderStatusName") orderStatusName:String): ResponseEntity<OrderStatus> {
        return try {
            ResponseEntity(orderStatusBusiness!!.getOrderStatusByNombre(orderStatusName), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addOrderStatus")
    fun insert(@RequestBody orderStatus: OrderStatus): ResponseEntity<Any> {
        return try {
            orderStatusBusiness!!.saveOrderStatus(orderStatus)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_CUSTOMERS+"/"+orderStatus.orderStatusRegId)
            ResponseEntity(orderStatus, responseHeader, HttpStatus.CREATED)
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
    @PostMapping("/addOrderStatuses")
    fun insert(@RequestBody customers:List<OrderStatus>): ResponseEntity<Any> {
        return try {
            ResponseEntity(orderStatusBusiness!!.saveOrderStatuses(customers), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody orderStatus: OrderStatus): ResponseEntity<Any> {
        return try {
            orderStatusBusiness!!.updateOrderStatus(orderStatus)
            ResponseEntity(orderStatus, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idOrderStatus: Long): ResponseEntity<Any> {
        return try {
            orderStatusBusiness!!.removeOrderStatus(idOrderStatus)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}