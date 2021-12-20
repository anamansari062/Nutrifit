package com.example.nutritionapp.Register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.example.nutritionapp.Activity.LoginActivity
import com.example.nutritionapp.R
import com.example.nutritionapp.databinding.FragmentEmailBinding
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult

class FragmentEmail : Fragment() {
    private var binding: FragmentEmailBinding? = null
    var registerMain: RegisterMain? = null
    lateinit var textEmail: EditText
    lateinit var textMobile: EditText
    lateinit var textName: EditText
    lateinit var next: ExtendedFloatingActionButton
    lateinit var back: ExtendedFloatingActionButton
    lateinit var nameL: TextInputLayout
    lateinit var emailL: TextInputLayout
    lateinit var mobileL: TextInputLayout
    var progressBar: ProgressBar? = null
    val EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentEmailBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        textEmail = root.findViewById(R.id.text_email)
        textMobile = root.findViewById(R.id.text_mobile)
        textName = root.findViewById(R.id.text_name)
        next = root.findViewById(R.id.email_next)
        back = root.findViewById(R.id.email_back)
        nameL = root.findViewById(R.id.nameLayout)
        emailL = root.findViewById(R.id.emailLayout)
        mobileL = root.findViewById(R.id.mobileLayout)
        progressBar = root.findViewById(R.id.next_progress_bar)
        // to get data and set the texts when came from passwordFragment
        var passwd = ""
        var active = "light"
        var selectedMale = false
        var selectedFemale = false
        var weight = 50
        var age = 18
        var height = 180
        val bundle = this.arguments
        if (bundle != null) {
            val name = bundle.getString("name", "")
            val email = bundle.getString("email", "")
            val mobileNo = bundle.getString("mobile", "")
            passwd = bundle.getString("pass", "")
            selectedMale = bundle.getBoolean("selectedMale", false)
            selectedFemale = bundle.getBoolean("selectedFemale", false)
            weight = bundle.getInt("weight", 50)
            age = bundle.getInt("age", 18)
            height = bundle.getInt("height", 180)
            active = bundle.getString("active", "light")
            textName.setText(name)
            textEmail.setText(email)
            textMobile.setText(mobileNo)
        }
        registerMain = activity as RegisterMain?
        val finalPasswd = passwd
        val finalSelectedFemale = selectedFemale
        val finalSelectedMale = selectedMale
        val finalWeight = weight
        val finalAge = age
        val finalHeight = height
        val finalActive = active
        next.setOnClickListener(View.OnClickListener { view: View? ->
//            if (textEmail.getText().toString()!=null!! and textMobile.getText().toString() != null) {
            registerMain!!.myEdit?.putString("email", textEmail.getText().toString())
            registerMain!!.myEdit?.putString("mobile", textMobile.getText().toString())
            registerMain!!.myEdit?.commit()
//            }
            var valid = true
            if (!validateEmail()) {
                valid = false
            }
            if (textMobile.length() == 0 || textMobile.length() < 10 || textMobile.length() > 10) {
                mobileL.setError("Please Enter a Valid Mobile Number")
                textMobile.setError(null)
                valid = false
            }
            if (textName.length() == 0) {
                nameL.setError("Name is required")
                textName.setError(null)
                valid = false
            }
            if (valid) {
                val fragmentPassword = FragmentPassword()
                sendInfo(fragmentPassword, finalPasswd, finalSelectedMale, finalSelectedFemale, finalAge, finalWeight, finalHeight, finalActive)
                parentFragmentManager.beginTransaction()
                        .replace(R.id.register_container, fragmentPassword)
                        .commit()
                registerMain!!.myEdit?.putString("name", textName.getText().toString())
                registerMain!!.myEdit?.commit()
            }
        })
        back.setOnClickListener(View.OnClickListener { view: View? ->
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        })
        textName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                nameL.setError(null)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        textMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                mobileL.setError(null)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        textEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                emailL.setError(null)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
        return root
    }

    private fun validateEmail(): Boolean {
        val email = textEmail!!.text.toString().trim { it <= ' ' }
        val firebaseAuth = FirebaseAuth.getInstance()
        if (email.isEmpty()) {
            emailL.error = "Please Enter an Email-ID"
            textEmail.requestFocus()
            return false
        }
         return if (email.matches(EMAIL_PATTERN.toRegex())) {
            //check email already exist or not.
            val isNewUser = BooleanArray(1)
            firebaseAuth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener { task: Task<SignInMethodQueryResult> ->
                        if (task.isSuccessful) {
                            isNewUser[0] = task.result.signInMethods!!.isEmpty()
                            if (isNewUser[0]) {
                                isNewUser[0] = false
                                Log.e("TAG", "Is New User!")
                            } else {
                                Log.e("TAG", "Is Old User!")
                                emailL!!.error = "User already exists"
                                textEmail!!.requestFocus()
                            }
                        }
                    }
             !isNewUser[0]
        } else {
            emailL!!.error = "Please Enter a valid Email-ID"
            textEmail!!.requestFocus()
            false
        }
    }

    private fun sendInfo(fragment: Fragment, passwd: String, selectedMale: Boolean, selectedFemale: Boolean, age: Int, weight: Int, height: Int, active: String) {

        // get data from SharedPreferences if next/back is pressed
        /** data  only gets stored in shared preferences if next is clicked
         * other wise take data from editText
         */
        // add data to bundle
        val bundle = Bundle()
        bundle.putString("pass", passwd)
        bundle.putBoolean("selectedMale", selectedMale)
        bundle.putBoolean("selectedFemale", selectedFemale)
        bundle.putInt("age", age)
        bundle.putInt("weight", weight)
        bundle.putInt("height", height)
        bundle.putString("active", active)
        fragment.arguments = bundle
    }
}