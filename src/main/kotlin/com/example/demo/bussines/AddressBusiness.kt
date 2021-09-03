package com.example.demo.bussines

import com.example.demo.dao.AddressRepository
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Address
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class AddressBusiness: iAddressBusiness {

    @Autowired
    val addressRepository: AddressRepository?=null

    @Throws(BusinessException::class)
    override fun getAddresses(): List<Address> {
        try {
            return addressRepository!!.findAll();
        }catch (e: Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getAddressById(idAddress: Long): Address {
        val opt: Optional<Address>
        try {
            opt = addressRepository!!.findById(idAddress)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro el articulo con referencia $idAddress")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun saveAddress(address: Address): Address {
        try {
            if (address.streetName.length<3)
                throw BusinessException("Ingrese mas de 3 caracteres")
            return addressRepository!!.save(address)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun saveAddresses(addresses: List<Address>): List<Address> {
        try {
            return addressRepository!!.saveAll(addresses)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun removeAddress(idAddress: Long) {
        val opt:Optional<Address>
        try {
            opt = addressRepository!!.findById(idAddress)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $idAddress")
        }
        else{
            try {
                addressRepository!!.deleteById(idAddress)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getAddressByStreetName(streetNameAddress: String): Address {
        val opt:Optional<Address>
        try {
            opt = addressRepository!!.findByStreetName(streetNameAddress)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $streetNameAddress")
        }
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getAddressByCity(cityAddress: String): Address {
        val opt:Optional<Address>
        try {
            opt = addressRepository!!.findByCity(cityAddress)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona $cityAddress")
        }
        return opt.get()
    }

    override fun updateAddress(address: Address): Address {
        val opt:Optional<Address>
        try {
            opt = addressRepository!!.findById(address.addressRegId)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent){
            throw NotFoundException("No se encontro la persona ${address.addressRegId}")
        }
        else{
            try {
                return addressRepository!!.save(address)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
}