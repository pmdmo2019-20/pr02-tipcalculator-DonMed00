package es.iessaladillo.pedrojoya.tipcalculator.ui.main

import android.annotation.SuppressLint
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

    @SuppressLint("ResourceType")
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

    private fun txtLinks() {
        val separadoresPersonalizados = DecimalFormatSymbols()
        separadoresPersonalizados.setDecimalSeparator('.')
        val formato = DecimalFormat("0.00", separadoresPersonalizados)

        txtTip.setText(formato.format(tipCalculator.calculateTip().toString().toFloat())).toString()
        txtTotal.setText(formato.format(tipCalculator.calculateTotal().toString().toFloat()))
            .toString()
        txtPerDiner.setText(formato.format(tipCalculator.calculatePerDiner().toString().toFloat()))
            .toString()
        txtPerDinerRounded.setText(formato.format(tipCalculator.calculatePerDinerRounded().toString().toFloat()))
            .toString()
    }

    private fun editTextsOnChanged(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFields(editText)
                changeField(editText)
                txtLinks()
            }
        })
        btnResetTip.setOnClickListener { resetFields(true) }
        btnResetDiners.setOnClickListener { resetFields(false) }
    }

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
