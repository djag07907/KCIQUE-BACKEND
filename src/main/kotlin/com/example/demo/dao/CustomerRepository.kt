package com.example.demo.dao

import com.example.demo.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository: JpaRepository<Customer,Long> {
    fun findByNombre(nombre:String): Optional<Customer>
    fun findByEmail(email:String): Customer?
}