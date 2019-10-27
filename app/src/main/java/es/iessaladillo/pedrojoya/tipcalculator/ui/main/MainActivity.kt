package es.iessaladillo.pedrojoya.tipcalculator.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.tipcalculator.R
import es.iessaladillo.pedrojoya.tipcalculator.model.TipCalculator
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


class MainActivity : AppCompatActivity() {
    lateinit var tipCalculator: TipCalculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()

    }

    /**
     * Call functions to start activities
     * Initialize tipCalculator
     */
    private fun setupViews() {
        putEditTextsDefault()
        tipCalculator = TipCalculator(
            txtBill.text.toString().toFloat(),
            txtPercentage.text.toString().toFloat(),
            txtDiners.text.toString().toInt()
        )
        editTextsOnChanged(txtBill)
        editTextsOnChanged(txtPercentage)
        editTextsOnChanged(txtDiners)
    }

    /**
     * Put all EditTexts defaults and txtBill get the focus
     */
    private fun putEditTextsDefault() {
        txtBill.setText("0.00")
        txtPercentage.setText("10.00")
        txtDiners.setText("1")
        txtTip.setText("0.00")
        txtTotal.setText("0.00")
        txtPerDiner.setText("0.00")
        txtPerDinerRounded.setText("0.00")

        txtBill.requestFocus()
    }

    /**
     * It assign to editTexts the formatted values of tipCalculator
     */
    private fun txtUnion() {
        val symbolFormat = DecimalFormatSymbols()
        symbolFormat.setDecimalSeparator('.')
        val format = DecimalFormat("0.00", symbolFormat)

        txtTip.setText(format.format(tipCalculator.calculateTip().toString().toFloat())).toString()
        txtTotal.setText(format.format(tipCalculator.calculateTotal().toString().toFloat()))
            .toString()
        txtPerDiner.setText(format.format(tipCalculator.calculatePerDiner().toString().toFloat()))
            .toString()
        txtPerDinerRounded.setText(format.format(tipCalculator.calculatePerDinerRounded().toString().toFloat()))
            .toString()
    }

    /**
     * @param editText EditText
     * It receive an EditText that will be changed while the text is changed
     */
    private fun editTextsOnChanged(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFields(editText)
                changeField(editText)
                txtUnion()
            }
        })
        btnResetTip.setOnClickListener { resetFields(true) }
        btnResetDiners.setOnClickListener { resetFields(false) }
    }

    /**
     * @param editText EditText
     * Change the value of attribute in tipCalculator depends of the type of editText
     */
    private fun changeField(editText: EditText) {
        when (editText) {
            txtBill ->
                tipCalculator.bill = editText.text.toString().toFloat()

            txtPercentage ->
                tipCalculator.percentage = editText.text.toString().toFloat()

            txtDiners ->
                tipCalculator.diners = editText.text.toString().toInt()

        }

    }

    /**
     * @param editText EditText
     * Check if editText are empty and change his value
     */
    private fun checkFields(editText: EditText) {
        when (editText) {
            txtBill ->
                if (editText.text.toString() == "") {
                    editText.setText("0.00")
                }
            txtPercentage ->
                if (editText.text.toString() == "") {
                    editText.setText("10.00")
                }
            txtDiners ->
                if (editText.text.toString() == "" || editText.text.toString() == ("0")) {
                    editText.setText("1")
                }

        }

    }

    /**
     * @param flag Boolean
     * Depending on the flag, the method will reset any field or others and will focus on a field
     */
    private fun resetFields(flag: Boolean) {
        if (flag) {
            txtBill.setText("0.00")
            txtTip.setText("0.00")
            txtPercentage.setText("10.00")
            txtTotal.setText("0.00")
            txtBill.requestFocus()
        } else {
            txtDiners.setText("1")
            txtPerDiner.setText("0.00")
            txtPerDinerRounded.setText("0.00")
            txtDiners.requestFocus()
        }
    }

}