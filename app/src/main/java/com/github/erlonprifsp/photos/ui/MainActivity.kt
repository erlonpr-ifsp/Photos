package com.github.erlonprifsp.photos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.erlonprifsp.photos.R
import com.github.erlonprifsp.photos.adapter.PhotoAdapter
import com.github.erlonprifsp.photos.databinding.ActivityMainBinding
import com.github.erlonprifsp.photos.model.PhotoList
import com.github.erlonprifsp.photos.model.PhotoListItem
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() { // classe MainActivity herda da classe AppCompatActivity

    private val amb: ActivityMainBinding by lazy { // instancia a classe ActivityMainBinding gerada automaticamente pelo ViewBinding
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val photoList: MutableList<PhotoListItem> = mutableListOf() // data source



    private val photoAdapter: PhotoAdapter by lazy { // adapter
        PhotoAdapter(this, photoList)
    }

    companion object {
        const val PHOTOS_ENDPOINT = "https://jsonplaceholder.typicode.com/photos"
    }


    // método onCreate
    override fun onCreate(savedInstanceState: Bundle?) { // parâmetro savedInstanceState contém dados sobre o estado anterior da activity
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        setContentView(amb.root) // define o conteúdo da activity usando a raiz (root) do layout inflado por meio do ViewBinding

        setSupportActionBar(amb.photosToolbar.apply { // define a toolbar como uma barra de ação da activity
            title =
                getString(R.string.app_name) // define o título da toolbar com o mesmo nome do aplicativo
        })

        // spinner
        amb.photosSpinner.adapter = photoAdapter

        retrievePhotos()

    }

    private fun retrievePhotos() = Thread {

        val photosConnection = URL(PHOTOS_ENDPOINT).openConnection() as HttpURLConnection
        try {
            if (photosConnection.responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader(photosConnection.inputStream).readText().let {
                    runOnUiThread {
                        // photoAdapter.addAll(Gson().fromJson(it, PhotoList::class.java).photos)

                        val photoList = Gson().fromJson(it, PhotoList::class.java)
                        photoAdapter.addAll(photoList)  // Adiciona diretamente a instância de PhotoList ao adapter
                    }
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this, getString(R.string.request_problem), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } catch (ioe: IOException) {
            runOnUiThread {
                Toast.makeText(this, getString(R.string.connection_failed), Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (jse: JsonSyntaxException) {
            runOnUiThread {
                Toast.makeText(this, getString(R.string.response_problem), Toast.LENGTH_SHORT)
                    .show()
            }
        } finally {
            photosConnection.disconnect()
        }

    }.start()


}