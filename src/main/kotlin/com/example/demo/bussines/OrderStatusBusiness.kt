package com.example.demo.bussines

import com.example.demo.dao.OrderStatusRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.OrderStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class OrderStatusBusiness:iOrderStatusBusiness {

    @Autowired
    val orderStatusRepository:OrderStatusRepository?=null

    @Throws(BusinessException::class)
    override fun getOrderStatuses(): List<OrderStatus> {
        try {
            return orderStatusRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getOrderStatusById(orderId: Long): OrderStatus {
        val opt: Optional<OrderStatus>
        try {
            opt = orderStatusRepository!!.findById(orderId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro al usuario $orderId")
        }
        return opt.get()    }


    @Throws(BusinessException::class)
    override fun saveOrderStatus(orderStatus: OrderStatus): OrderStatus {
        try {
            if (orderStatus.orderStatusName.length<5)
                throw BusinessException("Ingrese mas de 5 caracteres")
            return orderStatusRepository!!.save(orderStatus)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveOrderStatuses(orderStatuses: List<OrderStatus>): List<OrderStatus> {
        try {
            return orderStatusRepository!!.saveAll(orderStatuses)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeOrderStatus(orderId: Long) {
        val opt:Optional<OrderStatus>
        try {
            opt = orderStatusRepository!!.findById(orderId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $orderId")
        }
        else{
            try {
                orderStatusRepository!!.deleteById(orderId)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getOrderStatusByNombre(orderStatusName: String): OrderStatus {
        val opt:Optional<OrderStatus>
        try {
            opt = orderStatusRepository!!.findByOrderStatusName(orderStatusName)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $orderStatusName")
        }
        return opt.get()    }


    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateOrderStatus(orderStatus: OrderStatus): OrderStatus {
        val opt:Optional<OrderStatus>
        try {
            opt = orderStatusRepository!!.findById(orderStatus.orderId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${orderStatus.orderId}")
        }
        else{
            try {
                return orderStatusRepository!!.save(orderStatus)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }


}