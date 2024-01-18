package com.example.millometeradmin.adapter

import org.json.JSONObject

data class UserData(
    val _id: JSONObject,
    val phoneNum: String,
    val role: String,
    val devices: List<Any>,
    val mills: List<Mill>,
    val status: String,
    val name: String,
    val state: State
)

data class Id(
    val `$oid`: String
)

data class Mill(
    val PiID: String,
    val thresholds: List<Threshold>,
    val label: String?
)

data class Threshold(
    val parameter: String,
    val value: Value
)

data class Value(
    val `$numberInt`: String
)

data class State(
    val `$numberInt`: String
)
