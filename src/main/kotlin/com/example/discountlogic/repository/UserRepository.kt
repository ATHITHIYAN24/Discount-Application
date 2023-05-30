package com.example.discountlogic.repository;

import com.example.discountlogic.model.UserDetails
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface UserRepository : JpaRepository<UserDetails?, Long?> {
    fun findByEmail(email: String?): UserDetails?
}

