package com.example.demo.dao

import com.example.demo.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AddressRepository: JpaRepository<Address, Long> {
    fun findByStreetName(streetName:String): Optional<Address>
    fun findByCity(streetName:String): Optional<Address>
}