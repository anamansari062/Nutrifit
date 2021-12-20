package com.example.nutritionapp.Register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nutritionapp.R
import com.example.nutritionapp.databinding.FragmentPasswordBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class FragmentPassword : Fragment() {
    var binding: FragmentPasswordBinding? = null
    lateinit var textPass: EditText
    lateinit var textPassC: EditText
     var registerMain: RegisterMain? = null
    lateinit var passwordLayout: TextInputLayout
    lateinit var confirmPasswordLayout: TextInputLayout
    lateinit var next: ExtendedFloatingActionButton
    lateinit var back: ExtendedFloatingActionButton
    private var sharedViewModel: SharedViewModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPasswordBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        textPass = root.findViewById(R.id.text_pass)
        textPassC = root.findViewById(R.id.text_passc)
        next = root.findViewById(R.id.pass_next)
        back = root.findViewById(R.id.pass_back)
        passwordLayout = root.findViewById(R.id.passLayout)
        confirmPasswordLayout = root.findViewById(R.id.confirmPassLayout)
        registerMain = activity as RegisterMain?
        var selectedMale = false
        var selectedFemale = false
        var active = "light"
        var weight = 50
        var age = 18
        var height = 180
        // tp get the data
        val bundle = this.arguments
        if (bundle != null) {
            val passwd = bundle.getString("pass", "")
            selectedMale = bundle.getBoolean("selectedMale", false)
            selectedFemale = bundle.getBoolean("selectedFemale", false)
            weight = bundle.getInt("weight", 50)
            age = bundle.getInt("age", 18)
            height = bundle.getInt("height", 180)
            active = bundle.getString("active", "light")

            // set the gotten data
            textPass.setText(passwd)
            textPassC.setText(passwd)
        }
        val tempAge = age
        val finalSelectedMale = selectedMale
        val finalSelectedFemale = selectedFemale
        val finalAge = age
        val finalWeight = weight
        val finalHeight = height
        val finalActive = active
        next.setOnClickListener(View.OnClickListener { view: View? ->
            var valid = true
            if (!validatePassword()) {
                valid = false
            }
            if (textPassC.length() == 0) {
                confirmPasswordLayout.setError("Password cannot be empty")
                textPassC.setError(null)
                valid = false
            }
            if (textPass.getText().toString() != textPassC.getText().toString()) {
                confirmPasswordLayout.setError("Passwords do not match")
                textPassC.setError(null)
                valid = false
            }
            if (valid) {
                val fragmentGender = GenderFragment()
                sendInfo(fragmentGender, finalSelectedMale, finalSelectedFemale, finalAge, finalWeight, finalHeight, finalActive)
                requireFragmentManager()!!.beginTransaction()
                        .replace(R.id.register_container, fragmentGender)
                        .commit()
            }
            sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            if (textPass.getText().toString() != null) {
                registerMain!!.myEdit!!.putString("pass", textPass.getText().toString())
                registerMain!!.myEdit!!.commit()
            }
        })
        back.setOnClickListener(View.OnClickListener { view: View? ->
            val fragmentEmail = FragmentEmail()
            sendInfo(fragmentEmail, finalSelectedMale, finalSelectedFemale, finalAge, finalWeight, finalHeight, finalActive)
            requireFragmentManager().beginTransaction()
                    .replace(R.id.register_container, fragmentEmail)
                    .commit()
        })
        textPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                passwordLayout.setError(null)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        textPassC.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                confirmPasswordLayout.setError(null)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        return root
    }

    private fun validatePassword(): Boolean {
        val password = textPass!!.text.toString()
        return if (password.isEmpty()) {
            passwordLayout!!.error = "Password cannot be empty"
            textPass!!.error = null
            false
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordLayout!!.error = "Password too weak"
            textPass!!.error = null
            false
        } else {
            passwordLayout!!.error = null
            true
        }
    }

    private fun sendInfo(fragment: Fragment, selectedMale: Boolean, selectedFemale: Boolean, age: Int, weight: Int, height: Int, active: String) {

        // get data from SharedPreferences if next/back is pressed
        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         */
        val sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val name = sh.getString("name", "")
        val email = sh.getString("email", "")
        val mobile = sh.getString("mobile", "")
        val passwd = textPass!!.text.toString()
        val confirmPasswd = textPassC!!.text.toString()

        // add data to bundle
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("email", email)
        bundle.putString("mobile", mobile)
        bundle.putString("pass", passwd)
        bundle.putString("confirmPass", confirmPasswd)
        bundle.putBoolean("selectedMale", selectedMale)
        bundle.putBoolean("selectedFemale", selectedFemale)
        bundle.putInt("age", age)
        bundle.putInt("weight", weight)
        bundle.putInt("height", height)
        bundle.putString("active", active)
        fragment.arguments = bundle
    }

    companion object {
        private val PASSWORD_PATTERN = Pattern.compile("^" +
                "(?=.*[0-9])" +  //at least 1 digit
                "(?=.*[a-z])" +  //at least 1 lower case letter
                "(?=.*[A-Z])" +  //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +  //any letter
                "(?=.*[@#$%^&+=])" +  //at least 1 special character
                "(?=\\S+$)" +  //no white spaces
                ".{8,}" +  //at least 8 characters
                "$")
    }
}