package com.fantasmas_dev.waybill

sealed class Car(val carName: String, val carMileage: Double, val carImage: Int) {
    object Largus : Car("Lada Largus", 13.6, R.drawable.lada_largus)
    object Vesta : Car("Lada Vesta", 13.2, R.drawable.lada_vesta)
    object Multiwan : Car("VW Multiwan", 14.8, R.drawable.wv_multiwan)
}
