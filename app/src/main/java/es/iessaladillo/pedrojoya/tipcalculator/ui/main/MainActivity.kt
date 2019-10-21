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
    lateinit var tipCalculator : TipCalculator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }
    //Tengo que lograr que se actualize todo

    @SuppressLint("ResourceType")
    private fun setupViews() {
        txtBill.setText("10")
        txtPercentage.setText("10.00")
        txtDiners.setText("1")
        txtBill.requestFocus()
        tipCalculator = TipCalculator(
            txtBill.text.toString().toFloat(),
            txtPercentage.text.toString().toFloat(),
            txtDiners.text.toString().toInt()
        )
         txtLinks()

         txtOnChanged()


    }

    private fun txtLinks() {
        txtTip.setText(tipCalculator.calculateTip().toString())
        txtTotal.setText(tipCalculator.calculateTotal().toString())
        txtPerDiner.setText(tipCalculator.calculatePerDiner().toString())
        txtPerDinerRounded.setText(tipCalculator.calculatePerDinerRounded().toString())
    }

    private fun txtOnChanged() {
        txtBill.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtLinks()
            }
        })
    }


}
