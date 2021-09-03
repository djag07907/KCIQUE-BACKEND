package com.example.demo.services

import com.example.demo.dao.CustomerRepository
import com.example.demo.model.Customer
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) {

    fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)
    }

    fun findByEmail(email: String): Customer? {
        return this.customerRepository.findByEmail(email)
    }

    fun getById(id: Int): Customer {
        return this.customerRepository.getById(id.toLong())
    }
}