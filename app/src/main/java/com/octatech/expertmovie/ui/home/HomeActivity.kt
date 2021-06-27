package com.octatech.expertmovie.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.octatech.expertmovie.R
import com.octatech.expertmovie.databinding.ActivityHomeBinding
import com.octatech.expertmovie.ui.favorite.FavoriteFragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null ){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_home, HomeFragment())
                .commit()
        }

        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                var fragment : Fragment? = null
                when(newIndex){
                    0 -> {
                        fragment = HomeFragment()
                    }
                    1 -> {
                        fragment = FavoriteFragment()
                    }
                }
                if(fragment != null){
                    val transaction =supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fl_home, fragment)
                    transaction.commit()
                }
            }
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
            }
        })

    }


}