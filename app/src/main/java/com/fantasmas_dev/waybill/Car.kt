package com.fantasmas_dev.waybill

sealed class Car(val carName: String, val carMileage: Double){
    object Largus: Car("Lada Largus", 13.6)
    object Vesta: Car("Lada Vesta", 13.2)
    object Multiwan: Car("VW Multiwan", 14.8)
}
