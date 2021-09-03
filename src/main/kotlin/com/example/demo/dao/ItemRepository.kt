package com.example.demo.dao

import com.example.demo.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ItemRepository: JpaRepository<Item,Long> {
    fun findByNombre(nombre:String): Optional<Item>
}