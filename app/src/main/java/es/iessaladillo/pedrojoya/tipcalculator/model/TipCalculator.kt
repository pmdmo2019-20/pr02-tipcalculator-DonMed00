package es.iessaladillo.pedrojoya.tipcalculator.model

import kotlin.math.ceil

class TipCalculator {
    var bill: Float = 0.00f
    var percentage: Float = 0.00f
    var diners: Int = 0

    /**
     * @param bill Float
     * @param percentage Float
     * @param diners Int
     * @constructor
     * It initialize all the attributes after check all the parameters are corrects, >=0
     */
    constructor(bill: Float, percentage: Float, diners: Int) {
        require(bill >= 0) { "Error, the bill must be a positive number" }
        require(percentage >= 0) { "Error, the percentage must be a positive number" }
        require(diners >= 0) { "Error, the diners must be a positive number" }

        this.bill = bill
        this.percentage = percentage
        this.diners = diners
    }

    /**
     * @return Float of Tip
     */
    fun calculateTip(): Float {
        return (bill * percentage) / 100
    }


    /**
     * @return Float of Total with tip
     */
    fun calculateTotal(): Float {
        return bill + calculateTip()
    }

    /**
     * @return Float of calculateTotal()/ diners
     */
    fun calculatePerDiner(): Float {
        return calculateTotal() / diners
    }

    /**
     * @return Float of calculatedPerDiner() rounded
     */
    fun calculatePerDinerRounded(): Float {
        return ceil(calculatePerDiner().toDouble()).toFloat()
    }

}