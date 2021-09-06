package com.tool.agecalculator.viewModel

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.tool.agecalculator.BR
import com.tool.agecalculator.model.User
import java.util.Observable
import java.util.Observer
import java.util.Locale

class UserViewModel(private val user: User) : Observer, BaseObservable() {

    /// Register itself as the observer of Model
    init {
        user.addObserver(this)
    }

    /// Notify the UI when change event emitting from Model is received.
    override fun update(p0: Observable?, p1: Any?) {
        if (p1 is String) {
            when (p1) {
                "age" -> {
                    notifyPropertyChanged(BR.age)
                }
                "firstName" -> {
                    notifyPropertyChanged(BR.firstName)
                }
                "lastName" -> {
                    notifyPropertyChanged(BR.lastName)
                }
            }
        }
    }

    val firstName: String
        @Bindable get() {
            return user.firstName
        }

    val lastName: String
        @Bindable get() {
            return user.lastName
        }

    val age: String
        @Bindable get() {
            return if (user.age <= 0) return "0 year"
            else String.format(Locale.ENGLISH, "%d years old", user.age)
        }
}