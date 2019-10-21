package es.iessaladillo.pedrojoya.tipcalculator.model

import kotlin.math.ceil


// TipCalculator class. Its constructor receives bill, percentage and diners

class TipCalculator {
     var bill: Float
     var percentage: Float
     var diners: Int


    constructor(bill: Float, percentage: Float, diners: Int) {
        this.bill = bill
        this.percentage = percentage
        this.diners = diners
    }


    fun calculateTip(): Float {
        return (bill * percentage) / 100
    }

    fun calculateTotal(): Float {
        return bill + calculateTip()
    }

    fun calculatePerDiner(): Float {
        return calculateTotal() / diners
    }

    fun calculatePerDinerRounded(): Float {
        return ceil(calculatePerDiner().toDouble()).toFloat()
    }

}