package com.github.erlonprifsp.photos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.erlonprifsp.photos.R
import com.github.erlonprifsp.photos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { // classe MainActivity herda da classe AppCompatActivity

    private val amb: ActivityMainBinding by lazy { // instancia a classe ActivityMainBinding gerada automaticamente pelo ViewBinding
        ActivityMainBinding.inflate(layoutInflater)
    }

    // método onCreate
    override fun onCreate(savedInstanceState: Bundle?) { // parâmetro savedInstanceState contém dados sobre o estado anterior da activity
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        setContentView(amb.root) // define o conteúdo da activity usando a raiz (root) do layout inflado por meio do ViewBinding

        setSupportActionBar(amb.photosToolbar.apply { // define a toolbar como uma barra de ação da activity
            title = getString(R.string.app_name) // define o título da toolbar com o mesmo nome do aplicativo
        })
    }
}