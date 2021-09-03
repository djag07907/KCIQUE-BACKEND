package com.example.demo.bussines

import com.example.demo.dao.ItemRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Item
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class ItemBusiness: iItemBusiness {

    @Autowired
    val itemRepository: ItemRepository?=null

    @Throws(BusinessException::class)
    override fun getItems(): List<Item> {
        try {
            return itemRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getItemById(idItem: Long): Item {
        val opt: Optional<Item>
        try {
            opt = itemRepository!!.findById(idItem)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $idItem")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getItemByName(nombreItem: String): Item {
        val opt:Optional<Item>
        try {
            opt = itemRepository!!.findByNombre(nombreItem)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $nombreItem")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveItem(item: Item): Item {
        try {
            if (item.nombre.length<3)
                throw BusinessException("Ingrese mas de 3 caracteres")
            return itemRepository!!.save(item)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveItems(item: List<Item>): List<Item> {
        try {
            return itemRepository!!.saveAll(item)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateItem(item: Item): Item {
        val opt:Optional<Item>
        try {
            opt = itemRepository!!.findById(item.itemRegId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${item.itemRegId}")
        }
        else{
            try {
                return itemRepository!!.save(item)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeItem(idItem: Long) {
        val opt:Optional<Item>
        try {
            opt = itemRepository!!.findById(idItem)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $idItem")
        }
        else{
            try {
                itemRepository!!.deleteById(idItem)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}