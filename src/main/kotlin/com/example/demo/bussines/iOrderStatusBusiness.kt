package com.example.demo.bussines

import com.example.demo.model.OrderStatus


interface iOrderStatusBusiness {
    fun getOrderStatuses():List<OrderStatus>
    fun getOrderStatusById(orderId: Long): OrderStatus
    fun saveOrderStatus(orderStatus: OrderStatus): OrderStatus
    fun saveOrderStatuses(orderStatuses: List<OrderStatus>):List<OrderStatus>
    fun removeOrderStatus(orderId: Long)
    fun getOrderStatusByNombre(orderStatusName:String): OrderStatus
    fun updateOrderStatus(orderStatus: OrderStatus): OrderStatus
}