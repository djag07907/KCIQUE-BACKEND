package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*


@Entity
@Table(name = "customer")
    data class Customer (val dni:Long= 0,
                         var nombre:String = "",
                         )
    {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var customerId:Long = 0

        @Column(unique = true)
        var email = ""

        @Column
        var mobile = ""

        @Column
        var password = ""
//            @JsonIgnore
            get() = field
            set(value) {
                val passwordEncoder = BCryptPasswordEncoder()
                field = passwordEncoder.encode(value)
            }

        fun comparePassword(password: String): Boolean {
            return BCryptPasswordEncoder().matches(password, this.password)
        }
    }
