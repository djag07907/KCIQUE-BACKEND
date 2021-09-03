package com.example.demo.bussines

import com.example.demo.dao.SellerRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Seller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class SellerBusiness: iSellerBusiness {

    @Autowired
    val sellerRepository: SellerRepository?=null

    @Throws(BusinessException::class)
    override fun getSellers(): List<Seller> {
        try {
            return sellerRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSellerBySellerId(sellerId: Long): Seller {
        val opt: Optional<Seller>
        try {
            opt = sellerRepository!!.findById(sellerId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $sellerId")
        }
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSellerByOrderId(orderIdSeller: Int): Seller {
        val opt: Optional<Seller>
        try {
            opt = sellerRepository!!.findByOrderId(orderIdSeller)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $orderIdSeller")
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSellerByShopId(shopIdSeller: Int): Seller {
        val opt: Optional<Seller>
        try {
            opt = sellerRepository!!.findByShopId(shopIdSeller)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $shopIdSeller")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun saveSeller(seller: Seller): Seller {
        try {
            if (seller.sellerName.length<3)
                throw BusinessException("Ingrese mas de 3 caracteres")
            return sellerRepository!!.save(seller)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveSellers(seller: List<Seller>): List<Seller> {
        try {
            return sellerRepository!!.saveAll(seller)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeSeller(sellerId: Long) {
        val opt:Optional<Seller>
        try {
            opt = sellerRepository!!.findById(sellerId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $sellerId")
        }
        else{
            try {
                sellerRepository!!.deleteById(sellerId)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getSellerBySellerName(sellerName: String): Seller {
        val opt:Optional<Seller>
        try {
            opt = sellerRepository!!.findBySellerName(sellerName)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $sellerName")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateSeller(seller: Seller): Seller {
        val opt:Optional<Seller>
        try {
            opt = sellerRepository!!.findById(seller.sellerRegId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${seller.sellerRegId}")
        }
        else{
            try {
                return sellerRepository!!.save(seller)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
}