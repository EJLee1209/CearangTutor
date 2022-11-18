package com.dldmswo1209.caerangtutor.model

import java.io.Serializable

data class SignUpBody(
    val name: String,
    val password: String,
    val studentId: String,
    val team: String
): Serializable

//{
//    "name": "길홍배",
//    "password": "kim0326",
//    "studentId": "20185109",
//    "team": "태그"
//}