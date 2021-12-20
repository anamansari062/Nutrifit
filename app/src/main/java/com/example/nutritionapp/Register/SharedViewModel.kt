package com.example.nutritionapp.Register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val email = MutableLiveData<String>()
    val pass = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    private val mobile = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val height = MutableLiveData<String>()
    val weight = MutableLiveData<String>()
    val gender = MutableLiveData<String>()
    val active = MutableLiveData<String>()
    val calories = MutableLiveData<Double>()
    private var a = 0.0
    private var calorie = 0.0
    fun setCalories(height: String, weight: String, age: String, gender: String, active: String) {
        a = if (active == "Light") 1.2 else if (active == "Moderate") 1.55 else 1.9
        calorie =
            if (gender == "Male") 66.5 + 13.8 * weight.toInt() + 5 * height.toInt() - 6.8 * age.toInt() * a else 655.1 + 9.5 * weight.toInt() + 1.8 * height.toInt() - 4.6 * age.toInt() * a
        calories.value = calorie
    }

    fun setEmailMobile(input1: String, input2: String) {
        email.value = input1
        mobile.value = input2
    }

    fun setPass(input3: String) {
        pass.value = input3
    }

    fun setName(input4: String) {
        name.value = input4
    }

    fun setAge(input5: String) {
        age.value = input5
    }

    fun setHeight(input6: String) {
        height.value = input6
    }

    fun setWeight(input7: String) {
        weight.value = input7
    }

    fun setGender(input8: String) {
        gender.value = input8
    }

    fun setActive(input9: String) {
        active.value = input9
    }

    fun getEmail(): LiveData<String> {
        return email
    }

    fun getMobile(): LiveData<String> {
        return mobile
    }
}