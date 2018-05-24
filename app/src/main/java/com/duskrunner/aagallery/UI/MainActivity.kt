package com.duskrunner.aagallery.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.duskrunner.aagallery.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragment(PagerFragment().newInstance(), false)
        }
    }


    private fun openFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager
                .beginTransaction()

        if (addToBackStack) {
            transaction
                    .addToBackStack(null)
        }

        transaction
                .replace(R.id.activity_main_frame, fragment)
                .commit()
    }


}
