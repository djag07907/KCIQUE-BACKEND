package com.example.demo.model

import javax.persistence.*


@Entity
@Table(name="addresses")
    data class Address(val addressId:Long = 0,
                       val country:String="",
                       val department:String="",
                       val city:String="",
                       val postalCode:Int,
                       val streetName:String="",
                       val houseNumber:Int
                       )
    {
        @Id
        @GeneratedValue
        var addressRegId:Long=0
    }