package com.example.nutritionapp.Register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nutritionapp.Activity.LoginActivity
import com.example.nutritionapp.R
import com.example.nutritionapp.databinding.FragmentCalorieBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FragmentCalorie : Fragment() {
    var binding: FragmentCalorieBinding? = null
    lateinit var register: Button
    var mAuth: FirebaseAuth? = null
    var a: Double? = null
    var mcalorie: Double? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCalorieBinding.inflate(inflater, container, false)
        val rootView: View = binding!!.root
        register = rootView.findViewById(R.id.button_register)
        register.setOnClickListener(View.OnClickListener { view: View? ->
            //Here register function will come use get functions for taking data eg: for gender do getGender()
            registerUser()
        })
        return rootView
    }

    private fun registerUser() {
        val sh = this.requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val email = sh.getString("email", "")
        val mobile = sh.getString("mobile", "")
        val pass = sh.getString("pass", "")
        val name = sh.getString("name", "")
        val age = sh.getString("age", "")
        val gender = sh.getString("gender", "")
        val height = sh.getString("height", "")
        val weight = sh.getString("weight", "")
        val active = sh.getString("active", "")
        a = if (active == "Light") 1.2 else if (active == "Moderate") 1.55 else 1.9
        mcalorie = if (gender == "Male") 66.5 + 13.8 * weight!!.toInt() + 5 * height!!.toInt() - 6.8 * age!!.toInt() * a!! else 655.1 + 9.5 * weight!!.toInt() + 1.8 * height!!.toInt() - 4.6 * age!!.toInt() * a!!
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.createUserWithEmailAndPassword(email!!.trim { it <= ' ' }, pass!!.trim { it <= ' ' })
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        val use = mAuth!!.currentUser
                        use!!.sendEmailVerification().addOnSuccessListener { unused: Void? -> Toast.makeText(context, "Verification Link Sent", Toast.LENGTH_SHORT).show() }.addOnFailureListener { Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show() }
                        val map = HashMap<String, Any>()
                        map["name"] = name!!.trim { it <= ' ' }
                        map["email"] = email.trim { it <= ' ' }
                        map["mobile"] = mobile!!.trim { it <= ' ' }
                        map["id"] = mAuth!!.currentUser!!.uid
                        map["height"] = height.trim { it <= ' ' }
                        map["weight"] = weight.trim { it <= ' ' }
                        map["age"] = age.trim { it <= ' ' }
                        map["gender"] = gender!!.trim { it <= ' ' }
                        map["activity"] = active!!.trim { it <= ' ' }
                        map["calories"] = mcalorie.toString().trim { it <= ' ' }
                        FirebaseDatabase.getInstance().getReference("USERS")
                                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .setValue(map).addOnCompleteListener { task1: Task<Void?> ->
                                    if (task1.isSuccessful) {
                                        Toast.makeText(context, "You have successfully Registered", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(context, "Failed to Register", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        val i = Intent(activity, LoginActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(context, "Failed to Register", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}