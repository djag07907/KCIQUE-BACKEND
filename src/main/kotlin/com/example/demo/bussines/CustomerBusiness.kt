package com.example.demo.bussines

import com.example.demo.dao.CustomerRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*


//Aqui es donde se deben de hacer todas las validaciones
@Service
class CustomerBusiness:ICustomerBusiness {

    @Autowired
    val customerRepository:CustomerRepository?=null

    @Throws(BusinessException::class)
    override fun getCustomers(): List<Customer> {
        try {
            return customerRepository!!.findAll();
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getCustomerById(idCustomer: Long): Customer {
        val opt: Optional<Customer>
        try {
            opt = customerRepository!!.findById(idCustomer)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro al usuario $idCustomer")
        }
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveCustomer(customer: Customer): Customer {
        try {
            if (customer.nombre.length<5)
                throw BusinessException("Ingrese mas de 5 caracteres")
            return customerRepository!!.save(customer)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveCustomers(customers: List<Customer>): List<Customer> {
        try {
            return customerRepository!!.saveAll(customers)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeCustomer(idCustomer: Long) {
        val opt:Optional<Customer>
        try {
            opt = customerRepository!!.findById(idCustomer)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $idCustomer")
        }
        else{
            try {
                customerRepository!!.deleteById(idCustomer)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getCustomerByNombre(nombreCustomer: String): Customer {
        val opt:Optional<Customer>
        try {
            opt = customerRepository!!.findByNombre(nombreCustomer)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $nombreCustomer")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateCustomer(customer: Customer): Customer {
        val opt:Optional<Customer>
        try {
            opt = customerRepository!!.findById(customer.customerId.toLong())
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${customer.customerId}")
        }
        else{
            try {
                return customerRepository!!.save(customer)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
 }