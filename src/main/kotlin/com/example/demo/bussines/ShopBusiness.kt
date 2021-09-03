package com.example.demo.bussines

import com.example.demo.dao.ShopRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Address
import com.example.demo.model.Shop
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class ShopBusiness: iShopBusiness {

    @Autowired
    val shopRepository: ShopRepository?=null

    @Throws(BusinessException::class)
    override fun getShops(): List<Shop> {
        try {
            return shopRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getShopById(idShop: Long): Shop {
        val opt: Optional<Shop>
        try {
            opt = shopRepository!!.findById(idShop)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $idShop")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getShopByCity(cityShop: String): Shop {
        val opt:Optional<Shop>
        try {
            opt = shopRepository!!.findByCity(cityShop)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $cityShop")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getShopByLocalePhoneNumber(localePhoneNumberShop: Int): Shop {
        val opt:Optional<Shop>
        try {
            opt = shopRepository!!.findByLocalePhoneNumber(localePhoneNumberShop)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $localePhoneNumberShop")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun saveShop(shop: Shop): Shop {
        try {
            if (shop.nombre.length<3)
                throw BusinessException("Ingrese mas de 3 caracteres")
            return shopRepository!!.save(shop)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveShops(shop: List<Shop>): List<Shop> {
        try {
            return shopRepository!!.saveAll(shop)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeShop(idShop: Long) {
        val opt:Optional<Shop>
        try {
            opt = shopRepository!!.findById(idShop)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $idShop")
        }
        else{
            try {
                shopRepository!!.deleteById(idShop)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getShopByName(nombreShop: String): Shop {
        val opt:Optional<Shop>
        try {
            opt = shopRepository!!.findByNombre(nombreShop)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $nombreShop")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateShop(shop: Shop): Shop {
        val opt:Optional<Shop>
        try {
            opt = shopRepository!!.findById(shop.shopRegId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${shop.shopRegId}")
        }
        else{
            try {
                return shopRepository!!.save(shop)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
}