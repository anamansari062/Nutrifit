package com.example.nutritionapp.ui.profile

import com.example.nutritionapp.ui.profile.ProfileViewModel
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import android.widget.ProgressBar
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.nutritionapp.R
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.example.nutritionapp.ui.profile.ProfileFragment
import com.google.firebase.database.DatabaseError
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nutritionapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private val profileViewModel: ProfileViewModel? = null
    private var binding: FragmentProfileBinding? = null
    lateinit var nametxt: TextView
    lateinit var emailtxt: TextView
    lateinit var gender: TextView
    lateinit var height: TextView
    lateinit var age: TextView
    lateinit var weight: TextView
    lateinit var mobile: TextView
    lateinit var activity: TextView
    lateinit var calories: TextView
    lateinit var databaseReference: DatabaseReference
    lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        nametxt = root.findViewById(R.id.nametxt)
        emailtxt = root.findViewById(R.id.emailtxt)
        gender = root.findViewById(R.id.gendertxt)
        height = root.findViewById(R.id.heighttxt)
        age = root.findViewById(R.id.agetxt)
        mobile = root.findViewById(R.id.mobiletxt)
        weight = root.findViewById(R.id.weighttxt)
        activity = root.findViewById(R.id.act)
        calories = root.findViewById(R.id.calorie)
        progressBar = root.findViewById(R.id.progress_bar)
        val user = FirebaseAuth.getInstance().currentUser
        databaseReference = FirebaseDatabase.getInstance().getReference("USERS")
        if (user != null) {
            timer()
            databaseReference!!.child(user.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val map = snapshot.value as Map<String, String>?
                    val name = map!!["name"]
                    val email = map["email"]
                    val Gender = map["gender"]
                    val Height = map["height"]
                    val Weight = map["weight"]
                    val Age = map["age"]
                    val Mobile = map["mobile"]
                    val Act = map["activity"]
                    val Calories = map["calories"]
                    nametxt.setText(name)
                    emailtxt.setText(email)
                    gender.setText(Gender)
                    height.setText(String.format("%s cm", Height))
                    age.setText(String.format("%s yrs", Age))
                    mobile.setText(Mobile)
                    weight.setText(String.format("%sKg", Weight))
                    activity.setText(Act)
                    var totalCaloriesEaten = 0f
                    if (Calories != null) {
                        totalCaloriesEaten = Calories.toFloat()
                    }
                    calories.setText(round(totalCaloriesEaten.toDouble(), 2).toString())
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun timer() {
        object : CountDownTimer(1500, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar!!.visibility = View.VISIBLE
            }

            override fun onFinish() {
                progressBar!!.visibility = View.GONE
            }
        }.start()
    }

    companion object {
        private fun round(value: Double, precision: Int): Double {
            val scale = Math.pow(10.0, precision.toDouble()).toInt()
            return Math.round(value * scale).toDouble() / scale
        }
    }
}