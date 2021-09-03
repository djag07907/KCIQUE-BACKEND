package com.example.demo.web

import com.example.demo.bussines.ICustomerBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Customer
import com.example.demo.utils.Constants
import com.example.demo.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CUSTOMERS)
class CustomerRestController {
    @Autowired
    val customerBusiness: ICustomerBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Customer>> {
        return try {
            ResponseEntity(customerBusiness!!.getCustomers(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCustomer:Long):ResponseEntity<Customer>{
        return try {
            ResponseEntity(customerBusiness!!.getCustomerById(idCustomer), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreCustomer:String):ResponseEntity<Customer>{
        return try {
            ResponseEntity(customerBusiness!!.getCustomerByNombre(nombreCustomer), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addCustomer")
    fun insert(@RequestBody customer: Customer):ResponseEntity<Any>{
        return try {
            customerBusiness!!.saveCustomer(customer)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_CUSTOMERS+"/"+customer.customerId)
            ResponseEntity(customer, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            //return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion Enviada no es Valida",
                e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Para agregar varios TEST
    @PostMapping("/addCustomers")
    fun insert(@RequestBody customers:List<Customer>):ResponseEntity<Any>{
        return try {
            ResponseEntity(customerBusiness!!.saveCustomers(customers),HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody customer:Customer):ResponseEntity<Any>{
        return try {
            customerBusiness!!.updateCustomer(customer)
            ResponseEntity(customer,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idCustomer: Long):ResponseEntity<Any> {
        return try {
            customerBusiness!!.removeCustomer(idCustomer)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }
}