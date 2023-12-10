package com.example.loginandregistercompoase.login.data


data class AuthenticationState(
    val userName:String="",
    val password:String ="",
    val isLoading :Boolean = false,
    var toggleShowPassword :Boolean = true
){
    fun isValidForm(): Boolean {
        return userName.isNotEmpty() && password.isNotEmpty()
    }
    companion object{
        val EMPTY_STATE = AuthenticationState()
    }
}
