package com.example.demo.web

import com.example.demo.bussines.iSellerBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Seller
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_SELLERS)
class SellerRestController {

    @Autowired
    val sellerBusiness: iSellerBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Seller>> {
        return try {
            ResponseEntity(sellerBusiness!!.getSellers(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/sellerid/{sellerid}")
    fun loadById(@PathVariable("sellerid") sellerId:Long): ResponseEntity<Seller> {
        return try {
            ResponseEntity(sellerBusiness!!.getSellerBySellerId(sellerId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/shopid/{shopid}")
    fun loadByShopId(@PathVariable("shopid") shopId:Int): ResponseEntity<Seller> {
        return try {
            ResponseEntity(sellerBusiness!!.getSellerByShopId(shopId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/orderid/{orderid}")
    fun loadByOrderId(@PathVariable("orderid") orderId:Int): ResponseEntity<Seller> {
        return try {
            ResponseEntity(sellerBusiness!!.getSellerByOrderId(orderId), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/sellername/{sellername}")
    fun loadByNombre(@PathVariable("sellername") sellerName:String): ResponseEntity<Seller> {
        return try {
            ResponseEntity(sellerBusiness!!.getSellerBySellerName(sellerName), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addSeller")
    fun insert(@RequestBody seller: Seller): ResponseEntity<Any> {
        return try {
            sellerBusiness!!.saveSeller(seller)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_SELLERS +"/"+seller.sellerRegId)
            ResponseEntity(seller, responseHeader, HttpStatus.CREATED)
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
    @PostMapping("/addSellers")
    fun insert(@RequestBody sellers:List<Seller>): ResponseEntity<Any> {
        return try {
            ResponseEntity(sellerBusiness!!.saveSellers(sellers), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody seller: Seller): ResponseEntity<Any> {
        return try {
            sellerBusiness!!.updateSeller(seller)
            ResponseEntity(seller, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") sellerId: Long): ResponseEntity<Any> {
        return try {
            sellerBusiness!!.removeSeller(sellerId)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}