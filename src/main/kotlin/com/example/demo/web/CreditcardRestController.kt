package com.example.demo.web

import com.example.demo.bussines.iCreditcardBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Creditcard
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CREDITCARDS)
class CreditcardRestController {

    @Autowired
    val creditcardBusiness:iCreditcardBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Creditcard>> {
        return try {
            ResponseEntity(creditcardBusiness!!.getCreditcards(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCreditcard:Long): ResponseEntity<Creditcard> {
        return try {
            ResponseEntity(creditcardBusiness!!.getCreditcardById(idCreditcard), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nametag/{nametag}")
    fun loadByNombre(@PathVariable("nametag") nametagCreditcard:String): ResponseEntity<Creditcard> {
        return try {
            ResponseEntity(creditcardBusiness!!.getCreditcardByNametag(nametagCreditcard), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

//    @GetMapping("/customerdni/{customerdni}")
//    fun loadByCustomerDni(@PathVariable("customerdni") customerDni:Int): ResponseEntity<Creditcard> {
//        return try {
//            ResponseEntity(creditcardBusiness!!.getCreditcardByCustomerDni(customerDni), HttpStatus.OK)
//        }catch (e: BusinessException){
//            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
//        }catch (e: NotFoundException){
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }

    @PostMapping("/addCreditcard")
    fun insert(@RequestBody creditcard: Creditcard): ResponseEntity<Any> {
        return try {
            creditcardBusiness!!.saveCreditcard(creditcard)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_CREDITCARDS +"/"+creditcard.creditcardRegId)
            ResponseEntity(creditcard, responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            //return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion Enviada no es Valida",
                e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Para agregar varios TEST
    @PostMapping("/addCreditcards")
    fun insert(@RequestBody creditcards:List<Creditcard>): ResponseEntity<Any> {
        return try {
            ResponseEntity(creditcardBusiness!!.saveCreditcards(creditcards), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody creditcard: Creditcard): ResponseEntity<Any> {
        return try {
            creditcardBusiness!!.updateCreditcard(creditcard)
            ResponseEntity(creditcard, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idCreditcard: Long): ResponseEntity<Any> {
        return try {
            creditcardBusiness!!.removeCreditcard(idCreditcard)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}