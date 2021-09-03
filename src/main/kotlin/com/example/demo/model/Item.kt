package com.example.demo.model

import javax.persistence.*

@Entity
@Table(name = "item")
data class Item (val itemId:Long=0,
                 val nombre:String ="",
                 val itemPrice:Double=0.0,
                 val photo_url: String="",
                 val category:String="",
                 val shop_id:Int = 0,
                 val is_available:Boolean = false)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var itemRegId:Long = 0
}