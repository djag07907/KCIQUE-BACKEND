package com.example.demo.bussines

import com.example.demo.model.Item

interface iItemBusiness {
    fun getItems():List<Item>
    fun getItemById(idItem: Long): Item
    fun saveItem(item: Item): Item
    fun saveItems(item: List<Item>):List<Item>
    fun removeItem(idItem: Long)
    fun getItemByName(nombreItem: String): Item
    fun updateItem(item: Item): Item
}