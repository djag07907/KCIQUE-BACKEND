package com.example.demo.bussines

import com.example.demo.model.Address


interface iAddressBusiness {
    fun getAddresses():List<Address>
    fun getAddressById(idAddress: Long): Address
    fun saveAddress(address: Address): Address
    fun saveAddresses(address: List<Address>):List<Address>
    fun removeAddress(idAddress: Long)
    fun getAddressByStreetName(streetNameAddress: String): Address
    fun getAddressByCity(cityAddress: String): Address
    fun updateAddress(address: Address): Address
}