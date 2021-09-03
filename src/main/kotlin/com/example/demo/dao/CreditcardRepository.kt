package com.example.demo.dao

import com.example.demo.model.Creditcard
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CreditcardRepository: JpaRepository<Creditcard, Long> {
    fun findByNametag(nametag:String): Optional<Creditcard>
//    fun findByCustomerDni(customerDni: Int): Optional<Creditcard>

}