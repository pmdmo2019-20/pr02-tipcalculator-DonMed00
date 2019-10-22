package es.iessaladillo.pedrojoya.tipcalculator.model

import kotlin.math.ceil


// TipCalculator class. Its constructor receives bill, percentage and diners


class TipCalculator {
     var bill: Float = 0.00f
     var percentage: Float = 0.00f
     var diners: Int = 0


    constructor(bill: Float, percentage: Float, diners: Int) {
        require(bill >= 0) { "Error, the bill must be a positive number" }
        require(percentage >= 0) { "Error, the percentage must be a positive number" }
        require(diners >= 0) { "Error, the diners must be a positive number" }

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