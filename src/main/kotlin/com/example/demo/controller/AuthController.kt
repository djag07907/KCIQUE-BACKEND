package com.example.demo.controller

import com.example.demo.dtos.LoginDTO
import com.example.demo.dtos.Message
import com.example.demo.dtos.RegisterDTO
import com.example.demo.model.Customer
import com.example.demo.services.CustomerService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("api")
class AuthController(private val customerService: CustomerService) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Customer> {
        val customer = Customer()
        customer.nombre = body.name
        customer.email = body.email
        customer.password = body.password
        customer.mobile = body.mobile

        return ResponseEntity.ok(this.customerService.save(customer))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val customer = this.customerService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body(Message("customer not found!"))

        if (!customer.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body(Message("invalid password!"))
        }

        val issuer = customer.customerId.toString()

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }

    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("unauthenticated"))
            }

            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body

            return ResponseEntity.ok(this.customerService.getById(body.issuer.toInt()))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("unauthenticated"))
        }
    }

    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("success"))
    }
}