package com.example.demo.bussines

//import com.example.demo.dao.LoginRepository
//import com.example.demo.exceptions.BusinessException
//import com.example.demo.exceptions.NotFoundException
//import com.example.demo.model.Login
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//import java.util.*
//
//@Service
//class LoginBusiness:ILoginBusiness {
//
//    @Autowired
//    val loginRepository:LoginRepository?=null
//    override fun getUserLoginById(userLoginId: Boolean): Login {
//        TODO("Not yet implemented")
//    }
//
//    @Throws(BusinessException::class,NotFoundException::class)
//    override fun getUserLoginById(userLoginId: Long): Login {
//        val opt: Optional<Login>
//        try {
//            opt = loginRepository!!.findById(userLoginId)
//        }catch (e:Exception){
//            throw BusinessException(e.message)
//        }
//        if (!opt.isPresent){
//            throw NotFoundException("No se encontro el usuario $userLoginId")
//        }
//        return opt.get()
//    }
//
//    override fun saverUserLogin(userLogin: Login): Login {
//        TODO("Not yet implemented")
//    }
//
//    override fun updateUserLogin(userLogin: Login): Login {
//        TODO("Not yet implemented")
//    }
//
//    override fun removeUserLogin(userLoginId: Long) {
//        TODO("Not yet implemented")
//    }
//
//}