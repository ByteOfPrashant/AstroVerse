package com.byteofprashant.domain.model

data class Apod(
    var title: String?=null,
    var explanation: String?=null,
    var url: String?=null,
    var date: String?=null,
    var media_type: String?=null // "image" or "video"
)