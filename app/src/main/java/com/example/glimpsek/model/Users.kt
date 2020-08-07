package com.example.glimpsek.model

data class Users(val name: String, val age: Int) {
    val imageUrl = "https://picsum.photos/150?random=$age"

}