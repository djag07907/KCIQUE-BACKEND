package com.example.demo.bussines

import com.example.demo.dao.CreditcardRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Creditcard
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class CreditcardBusiness: iCreditcardBusiness {

    @Autowired
    val creditcardRepository: CreditcardRepository?=null

    @Throws(BusinessException::class)
    override fun getCreditcards(): List<Creditcard> {
        try {
            return creditcardRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCreditcardById(idCreditcard: Long): Creditcard {
        val opt: Optional<Creditcard>
        try {
            opt = creditcardRepository!!.findById(idCreditcard)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $idCreditcard")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getCreditcardByNametag(nametagCreditcard: String): Creditcard {
        val opt:Optional<Creditcard>
        try {
            opt = creditcardRepository!!.findByNametag(nametagCreditcard)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $nametagCreditcard")
        }
        return opt.get()
    }

//    @Throws(BusinessException::class,NotFoundException::class)
//    override fun getCreditcardByCustomerDni(customerDni: Int): Creditcard {
//        val opt: Optional<Creditcard>
//        try {
//            opt = creditcardRepository!!.findByCustomerDni(customerDni)
//        }catch (e:Exception){
//            throw BusinessException(e.message)
//        }
//        if (!opt.isPresent){
//            throw NotFoundException("No se encontro el articulo con referencia $customerDni")
//        }
//        return opt.get()
//    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun saveCreditcard(creditcard: Creditcard): Creditcard {
        try {
            if (creditcard.nametag.length<3)
                throw BusinessException("Ingrese mas de 3 caracteres")
            return creditcardRepository!!.save(creditcard)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveCreditcards(creditcard: List<Creditcard>): List<Creditcard> {
        try {
            return creditcardRepository!!.saveAll(creditcard)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeCreditcard(idCreditcard: Long) {
        val opt:Optional<Creditcard>
        try {
            opt = creditcardRepository!!.findById(idCreditcard)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $idCreditcard")
        }
        else{
            try {
                creditcardRepository!!.deleteById(idCreditcard)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateCreditcard(creditcard: Creditcard): Creditcard {
        val opt:Optional<Creditcard>
        try {
            opt = creditcardRepository!!.findById(creditcard.creditcardRegId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${creditcard.creditcardRegId}")
        }
        else{
            try {
                return creditcardRepository!!.save(creditcard)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
}