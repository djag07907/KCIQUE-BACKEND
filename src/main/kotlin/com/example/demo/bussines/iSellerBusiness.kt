package com.example.demo.bussines

import com.example.demo.model.Seller


interface iSellerBusiness {
    fun getSellers():List<Seller>
    fun getSellerBySellerId(sellerId: Long): Seller
    fun getSellerByOrderId(orderIdSeller:Int):Seller
    fun getSellerByShopId(shopIdSeller:Int):Seller
    fun saveSeller(seller: Seller): Seller
    fun saveSellers(seller: List<Seller>):List<Seller>
    fun removeSeller(sellerId: Long)
    fun getSellerBySellerName(sellerName: String): Seller
    fun updateSeller(seller: Seller): Seller
}