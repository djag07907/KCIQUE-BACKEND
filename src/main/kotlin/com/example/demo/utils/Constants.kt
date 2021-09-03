package com.example.demo.utils

class Constants {
    companion object{
        private const val URL_API_BASE="/api"
        private const val URL_API_VERSION="/v1"
        private const val URL_BASE= URL_API_BASE + URL_API_VERSION

        const val URL_BASE_CUSTOMERS        = "$URL_BASE/customers"
        const val URL_BASE_ITEMS            = "$URL_BASE/items"
        const val URL_BASE_SHOPS            = "$URL_BASE/shops"
        const val URL_BASE_ADDRESSES        = "$URL_BASE/addresses"
        const val URL_BASE_CREDITCARDS      = "$URL_BASE/creditcards"
        const val URL_BASE_SELLERS          = "$URL_BASE/sellers"
        const val URL_BASE_ORDERS           = "$URL_BASE/orders"
        const val URL_BASE_TRANSACTIONS     = "$URL_BASE/transactions"
        const val URL_BASE_ORDERSTATUSES    = "$URL_BASE/orderstatuses"

    }
}