package com.example.demo.bussines

import com.example.demo.model.Customer

interface ICustomerBusiness {
    fun getCustomers():List<Customer>
    fun getCustomerById(idCustomer: Long): Customer
    fun saveCustomer(customer: Customer): Customer
    fun saveCustomers(customers: List<Customer>):List<Customer>
    fun removeCustomer(idCustomer: Long)
    fun getCustomerByNombre(nombreCustomer: String): Customer
    fun updateCustomer(customer: Customer): Customer
}