package com.example.demo.bussines

import com.example.demo.model.Creditcard


interface iCreditcardBusiness {
    //Gets
    fun getCreditcards():List<Creditcard>
    fun getCreditcardById(idCreditcard: Long): Creditcard
    fun getCreditcardByNametag(nametag: String): Creditcard
//    fun getCreditcardByCustomerDni(customerDni: Int):Creditcard
    //Saves
    fun saveCreditcard(creditcard: Creditcard): Creditcard
    fun saveCreditcards(creditcard: List<Creditcard>):List<Creditcard>
    //Remove
    fun removeCreditcard(idCreditcard: Long)
    //Update
    fun updateCreditcard(creditcard: Creditcard): Creditcard
}