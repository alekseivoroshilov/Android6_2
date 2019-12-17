package com.example.lab6_2

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

@RequiresApi(Build.VERSION_CODES.CUPCAKE)

class MainActivity : AppCompatActivity() {

    val FLAG = 0

    val IMG_PATH_1 = "https://sun9-14.userapi.com/c205616/v205616479/4386/JksgL3F-SxA.jpg"
    val IMG_PATH_2 = "https://sun9-61.userapi.com/c855628/v855628405/1789c0/OnJMeedKl6c.jpg"
    val IMG_PATH_3 = "https://sun9-43.userapi.com/c855236/v855236429/140305/9HMTJNKWCDo.jpg"

    private lateinit var viewModel: Coroutine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (FLAG) {
            0 -> Async(iv).execute(IMG_PATH_1)
            1 -> {
                viewModel = ViewModelProviders.of(this).get(Coroutine::class.java)
                viewModel.imageBm.observe(this, Observer {
                    it?.let {
                        iv.setImageBitmap(it)
                    }
                })
            }
            2 -> Picasso.get().load(IMG_PATH_3).into(iv)
        }
    }

    override fun onStart() {
        super.onStart()
        if (FLAG == 1 && viewModel.imageBm.value == null)
            viewModel.load(IMG_PATH_2)
    }
}
