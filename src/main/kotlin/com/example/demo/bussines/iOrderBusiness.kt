package com.example.demo.bussines

import com.example.demo.model.Order


interface iOrderBusiness {
    fun getOrders():List<Order>
    fun getOrderByOrderId(orderId: Long): Order
    fun getOrderByCustomerDni(customerDniOrder:String): Order
    fun getOrderByOrderName(orderNameOrder: String): Order
    fun getOrderByShopId(shopIdOrder:Int): Order
    fun saveOrder(order: Order): Order
    fun saveOrders(order: List<Order>):List<Order>
    fun removeOrder(orderId: Long)
    fun updateOrder(order: Order): Order
}