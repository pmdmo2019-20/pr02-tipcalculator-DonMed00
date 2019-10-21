package es.iessaladillo.pedrojoya.tipcalculator.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.tipcalculator.R
import es.iessaladillo.pedrojoya.tipcalculator.model.TipCalculator
import kotlinx.android.synthetic.main.activity_main.*


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
        txtBill.requestFocus()
        tipCalculator = TipCalculator(
            txtBill.text.toString().toFloat(),
            txtPercentage.text.toString().toFloat(),
            txtDiners.text.toString().toInt()
        )
        txtLinks()
        editTextsOnChanged()


    }

    private fun putEditTextsDefault() {
        txtBill.setText("0")
        txtPercentage.setText("10.00")
        txtDiners.setText("1")
    }

    private fun txtLinks() {
        txtTip.setText(tipCalculator.calculateTip().toString())
        txtTotal.setText(tipCalculator.calculateTotal().toString())
        txtPerDiner.setText(tipCalculator.calculatePerDiner().toString())
        txtPerDinerRounded.setText(tipCalculator.calculatePerDinerRounded().toString())
    }

    private fun editTextsOnChanged() {
        txtBill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txtBill.text.toString() == "") {
                    txtBill.setText("0")
                }
                tipCalculator.bill = txtBill.text.toString().toFloat()
                txtLinks()
            }
        })

        txtPercentage.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txtPercentage.text.toString() == "") {
                    txtPercentage.setText("10")
                }
                tipCalculator.percentage = txtPercentage.text.toString().toFloat()
                txtLinks()
            }
        })
        txtDiners.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (txtDiners.text.toString() == "") {
                    txtDiners.setText("1")
                }
                tipCalculator.diners = txtDiners.text.toString().toInt()
                txtLinks()
            }
        })

        btnResetTip.setOnClickListener { resetFields(true) }
        btnResetDiners.setOnClickListener { resetFields(false) }
    }

    private fun resetFields(flag: Boolean) {
        if (flag) {
            txtBill.setText("0")
            txtTip.setText("0")
            txtPercentage.setText("10")
            txtTotal.setText("0")
        } else {
            txtDiners.setText("1")
            txtPerDiner.setText("0")
            txtPerDinerRounded.setText("0")
        }
    }

}
