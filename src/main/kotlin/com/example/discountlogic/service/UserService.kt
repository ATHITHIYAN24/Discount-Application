package com.example.discountlogic.service;


import com.example.discountlogic.data.UserData
import com.example.discountlogic.model.UserDetails

interface UserService {
     fun saveUserDetails(userData: UserData): UserDetails
}
