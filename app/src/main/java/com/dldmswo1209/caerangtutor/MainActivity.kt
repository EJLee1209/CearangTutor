package com.dldmswo1209.caerangtutor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dldmswo1209.caerangtutor.Fragment.HomeFragment
import com.dldmswo1209.caerangtutor.Fragment.MentorFragment
import com.dldmswo1209.caerangtutor.Fragment.PostFragment
import com.dldmswo1209.caerangtutor.databinding.ActivityMainBinding
import com.dldmswo1209.caerangtutor.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    val homeFragment = HomeFragment()
    val mentorFragment = MentorFragment()
    val postFragment = PostFragment()
    private lateinit var viewModel : MainViewModel

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        replaceFragment(postFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.share -> replaceFragment(postFragment)
                R.id.mentor -> replaceFragment(mentorFragment)
            }
            true
        }

    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .apply{
                replace(R.id.containerFrameLayout, fragment)
                commit()
            }
    }

}