package com.dldmswo1209.caerangtutor.data

data class Mentor(
    val name: String
)

data class Post(
    val name: String,
    val text: String
)

val mentors = mutableListOf(
    Mentor("라떼팀 멘토"),
    Mentor("태그팀 멘토"),
    Mentor("텐서팀 멘토"),
    Mentor("라떼팀 팀장"),
    Mentor("텐서팀 팀장"),
)

val posts = mutableListOf(
    Post("홍길동", "제 코드 공유합니다...\nfun add(a:Int, b:Int){ \n   ... \n }"),
    Post("이은재", "제 코드 공유합니다...\nfun add(a:Int, b:Int){ \n   ... \n }"),
    Post("길홍배", "제 코드 공유합니다...\nfun add(a:Int, b:Int){ \n   ... \n }"),
    Post("라떼팀 멘토", "제 코드 공유합니다...\nfun add(a:Int, b:Int){ \n   ... \n }"),
    Post("철수", "제 코드 공유합니다...\nfun add(a:Int, b:Int){ \n   ... \n }"),
    Post("영희", "제 코드 공유합니다...\nfun add(a:Int, b:Int){ \n   ... \n }")
)