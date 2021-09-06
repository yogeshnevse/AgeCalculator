package com.tool.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tool.agecalculator.model.User
import com.tool.agecalculator.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /// Create the model with initial data

        user.age = 20
        user.firstName = "Yogesh"
        user.lastName = "Nevse"


        /// Create the view model
        val userViewModel = UserViewModel(user)

        /// Data-Binding
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)
        binding.setVariable(BR.user, userViewModel)

        buttonCalculate.setOnClickListener { datePicker() }
    }

    fun datePicker(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            // Display Selected date in textbox
            user.age = getPerfectAgeInYears(year, monthOfYear, dayOfMonth)
        }, year, month, day)

        dpd.show()
    }

    fun getPerfectAgeInYears(year: Int, month: Int, date: Int): Int {
        val dobCalendar: Calendar = Calendar.getInstance()
        dobCalendar.set(Calendar.YEAR, year)
        dobCalendar.set(Calendar.MONTH, month)
        dobCalendar.set(Calendar.DATE, date)
        var ageInteger = 0
        val today: Calendar = Calendar.getInstance()
        ageInteger = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR)
        if (today.get(Calendar.MONTH) === dobCalendar.get(Calendar.MONTH)) {
            if (today.get(Calendar.DAY_OF_MONTH) < dobCalendar.get(Calendar.DAY_OF_MONTH)) {
                ageInteger -= 1
            }
        } else if (today.get(Calendar.MONTH) < dobCalendar.get(Calendar.MONTH)) {
            ageInteger -= 1
        }
        return ageInteger
    }
}