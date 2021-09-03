package com.example.demo.web

import com.example.demo.bussines.iItemBusiness
import com.example.demo.exceptions.BusinessException
import com.example.demo.exceptions.NotFoundException
import com.example.demo.model.Item
import com.example.demo.utils.Constants.Companion.URL_BASE_ITEMS
import com.example.demo.utils.RestApiError
import org.apache.coyote.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(URL_BASE_ITEMS)
class ItemRestController {

    @Autowired
    val itemBusiness: iItemBusiness?=null

    @GetMapping("")
    fun list(): ResponseEntity<List<Item>> {
        return try {
            ResponseEntity(itemBusiness!!.getItems(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idItem:Long):ResponseEntity<Item>{
        return try {
            ResponseEntity(itemBusiness!!.getItemById(idItem), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreItem:String):ResponseEntity<Item>{
        return try {
            ResponseEntity(itemBusiness!!.getItemByName(nombreItem), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addItem")
    fun insert(@RequestBody item: Item):ResponseEntity<Any>{
        return try {
            itemBusiness!!.saveItem(item)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", URL_BASE_ITEMS+"/"+item.itemRegId)
            ResponseEntity(item, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            //return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion Enviada no es Valida",
                e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    //Para agregar varios TEST
    @PostMapping("/addItems")
    fun insert(@RequestBody items:List<Item>):ResponseEntity<Any>{
        return try {
            ResponseEntity(itemBusiness!!.saveItems(items),HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody item:Item):ResponseEntity<Any>{
        return try {
            itemBusiness!!.updateItem(item)
            ResponseEntity(item,HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idItem: Long):ResponseEntity<Any> {
        return try {
            itemBusiness!!.removeItem(idItem)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)

        }
    }

}