package com.example.likedislikelifecycle

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Module-level variable
    //var like: Int = 0
    //var dislike: Int = 0

    //lateinit=assign value later
    lateinit var counterViewModel:CounterViewModel
    //this method is when destroy the page the quantity will remain
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity","onCreate")

        //Initialize CounterViewModel
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        //Initialise the shared preference
        //one activity use getPreferences(); multiple activity use getSharePreference()

        //one and only
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        //one or more but need to give a name
        //sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE)


        //create an on click
        imageViewLike.setOnClickListener{
            counterViewModel.incrementLike()
            textViewLike.text = counterViewModel.likeCount.toString()
        }

        imageViewDislike.setOnClickListener{
            counterViewModel.incrementDislike()
            textViewDislike.text = counterViewModel.dislikeCount.toString()
        }
    }

    override fun onStart(){
        Log.d("MainActivity","onStart")
        super.onStart()
    }

    override fun onResume(){
        Log.d("MainActivity","onResume")
        //Write preference(hold number)
        val like = sharedPreferences.getInt(getString(R.string.like),0)
        val dislike = sharedPreferences.getInt(getString(R.string.dislike),0)

        counterViewModel.likeCount = like
        counterViewModel.dislikeCount = dislike

        //create a function to hold number(viewModel)
        textViewLike.text = counterViewModel.likeCount.toString()
        textViewDislike.text = counterViewModel.dislikeCount.toString()


        super.onResume()

    }

    override fun onPause(){
        Log.d("MainActivity","onPause")

        with(sharedPreferences.edit()){
            putInt(getString(R.string.like), counterViewModel.likeCount)

            apply()
            //or commit()
        }

        with(sharedPreferences.edit()){
            putInt(getString(R.string.dislike), counterViewModel.dislikeCount)

            apply()
            //or commit()
        }
        super.onPause()
    }

    override fun onStop(){
        Log.d("MainActivity","onStop")
        super.onStop()
    }

    override fun onDestroy(){
        Log.d("MainActivity","onDestroy")
        super.onDestroy()
    }

}
