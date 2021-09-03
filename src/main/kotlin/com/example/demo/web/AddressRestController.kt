package com.example.demo.web

import com.example.demo.bussines.iAddressBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Address
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_ADDRESSES)
class AddressRestController {

    @Autowired
    val addressBusiness: iAddressBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Address>> {
        return try {
            ResponseEntity(addressBusiness!!.getAddresses(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idAddress:Long): ResponseEntity<Address> {
        return try {
            ResponseEntity(addressBusiness!!.getAddressById(idAddress), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/streetName/{streetName}")
    fun loadByStreetName(@PathVariable("streetName") streetNameAddress:String): ResponseEntity<Address> {
        return try {
            ResponseEntity(addressBusiness!!.getAddressByStreetName(streetNameAddress), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/city/{city}")
    fun loadByCity(@PathVariable("city") cityAddress:String): ResponseEntity<Address> {
        return try {
            ResponseEntity(addressBusiness!!.getAddressByCity(cityAddress), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addAddress")
    fun insert(@RequestBody address: Address): ResponseEntity<Any> {
        return try {
            addressBusiness!!.saveAddress(address)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_ADDRESSES + "/" + address.addressRegId)
            ResponseEntity(address, responseHeader, HttpStatus.CREATED)
        } catch (e: BusinessException) {
            //return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion Enviada no es Valida",
                e.message.toString())
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Para agregar varios TEST
    @PostMapping("/addAdresses")
    fun insert(@RequestBody addresses:List<Address>): ResponseEntity<Any> {
        return try {
            ResponseEntity(addressBusiness!!.saveAddresses(addresses), HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody address: Address): ResponseEntity<Any> {
        return try {
            addressBusiness!!.updateAddress(address)
            ResponseEntity(address, HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idAdress: Long): ResponseEntity<Any> {
        return try {
            addressBusiness!!.removeAddress(idAdress)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}