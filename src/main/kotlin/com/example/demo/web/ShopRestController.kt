package com.example.demo.web

import com.example.demo.bussines.iShopBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Address
import com.example.demo.model.Shop
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_SHOPS)
class ShopRestController {

    @Autowired
    val shopBusiness: iShopBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Shop>> {
        return try {
            ResponseEntity(shopBusiness!!.getShops(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idShop:Long): ResponseEntity<Shop> {
        return try {
            ResponseEntity(shopBusiness!!.getShopById(idShop), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreShop:String): ResponseEntity<Shop> {
        return try {
            ResponseEntity(shopBusiness!!.getShopByName(nombreShop), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/city/{city}")
    fun loadByCity(@PathVariable("city") cityShop:String): ResponseEntity<Shop> {
        return try {
            ResponseEntity(shopBusiness!!.getShopByCity(cityShop), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/localePhoneNumber/{localePhoneNumber}")
    fun loadByLocalePhoneNumber(@PathVariable("localePhoneNumber") localePhoneNumberShop:Int): ResponseEntity<Shop> {
        return try {
            ResponseEntity(shopBusiness!!.getShopByLocalePhoneNumber(localePhoneNumberShop), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping("/addShop")
    fun insert(@RequestBody shop: Shop): ResponseEntity<Any> {
        return try {
            shopBusiness!!.saveShop(shop)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_SHOPS +"/"+shop.shopRegId)
            ResponseEntity(shop, responseHeader, HttpStatus.CREATED)
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
    @PostMapping("/addShops")
    fun insert(@RequestBody shops:List<Shop>): ResponseEntity<Any> {
        return try {
            ResponseEntity(shopBusiness!!.saveShops(shops), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody shop: Shop): ResponseEntity<Any> {
        return try {
            shopBusiness!!.updateShop(shop)
            ResponseEntity(shop, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idShop: Long): ResponseEntity<Any> {
        return try {
            shopBusiness!!.removeShop(idShop)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}