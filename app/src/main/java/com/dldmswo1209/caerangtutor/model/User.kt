package com.dldmswo1209.caerangtutor.model

data class User(
    val email: String,
    val password: String,
    val studentId: String,
    var name: String,
    val team: String,
    val uid: String
){
    constructor(): this("","","","","","")
}