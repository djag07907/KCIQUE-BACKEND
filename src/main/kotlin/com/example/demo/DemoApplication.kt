package com.example.demo

import com.example.demo.dao.CustomerRepository
import com.example.demo.model.Customer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)


//	@PostConstruct
//	fun insertDefaultUser() {
//		var email: Customer? = CustomerRepository.findByUserAndStatus("default", true)
//		if (email == null) {
//			email.setEmail("default")
//			email.setPassword(BCryptPasswordEncoder().encode("\$PAiC2020@"))
//			CustomerRepository.save(email)
//		}
//	}
}
