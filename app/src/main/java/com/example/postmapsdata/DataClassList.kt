package com.example.postmapsdata

data class PostList(
    val imgpath: String,
    val imglat: Double,
    val imglng: Double
)

data class like(
    val likeFlag: Boolean
)

data class PostDataClassList(
    val likeId: String,
    val postId: String,
    val userId: String,
    val userName: String,
    val userIcon: String,
    val postImage: String,
    val postText: String,
    val postDate: String,
    val postLikeCnt: Long
)

data class LoginDataClassList(
    val userLoginFlag: Boolean,
    val result: String,
    val userId: Long
)

data class UserDataClassList(
    val userName: String,
    val userNumber: Long,
    val userBio: String
)

data class SinginDataClassList (
    val userSinginFlag: Boolean,
    val result: String,
    val userId: Long
)

data class MapsDataClassList (
    val imgPath: String,
    val imgLat: Double,
    val imgLng: Double
)