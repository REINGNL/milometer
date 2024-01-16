package com.example.millometeradmin.adapter



data class ThresholdModel(
    val parameter: String,
    val value: String
)

data class ValueModel(
    val value: String
)

data class MillModel(
    val PiID: String,
    val thresholds: List<ThresholdModel>,
    val label: String?
)

data class StateModel(
    val stateValue: String
)

data class DataModel(
    val _id: IdModel,
    val phoneNum: String,
    val role: String,
    val devices: List<Any>,
    val mills: List<List<MillModel>>, // Adjust the type here
    val status: String,
    val name: String,
    val state: StateModel
)

data class IdModel(
    val oid: String
)

//data class DataModel(
//    val _id: IdModel,
//    val phoneNum: String,
//    val role: String,
//    val name: String,
//    val email: String,
//    val mills: List<List<MillModel>> // Adjust the type here
//)
//

