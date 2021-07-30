package com.example.mvvmusingjetpack.model

data class LoginData(var user: String, var pass: String) {


    fun isValid(): Boolean {
        //   return user.equals("Admin") && pass.equals("Admin")
        return user.equals("") && pass.equals("")
    }


}