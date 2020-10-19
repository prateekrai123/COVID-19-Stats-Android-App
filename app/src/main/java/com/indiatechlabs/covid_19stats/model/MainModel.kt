package com.indiatechlabs.covid_19stats.model

data class MainModel(
    val country : String,
    val newConf : Int,
    val totalConf : Int,
    val newDeaths : Int,
    val totalDeaths : Int,
    val newRec : Int,
    val totalRec : Int,
    val flagUrl : String
)