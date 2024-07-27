package com.app.bloconotas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.app.bloconotas.adapter.AdapterItem
import com.app.bloconotas.databinding.ActivityMainBinding
import com.app.bloconotas.interfac.NotaDatabase
import com.app.bloconotas.models.Notass
import com.google.android.material.slider.Slider.OnChangeListener
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: NotaDatabase

    private var list: ArrayList<Notass> = arrayListOf<Notass>()

    private val adapterItem by lazy {
        AdapterItem(
            list
        )
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addNotas.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }


        db = Room.databaseBuilder(
            this,
            NotaDatabase::class.java,
            "audioRecords"
        ).build()

        GlobalScope.launch {
            val lista = db.notaDao().getAll()
            list.addAll(lista)
            adapterItem.clearSearch()
        }

        binding.myRecyclerView.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
        binding.myRecyclerView.adapter = adapterItem
        setSupportActionBar(findViewById(R.id.toolbar))


        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { return true}

            override fun onQueryTextChange(newText: String): Boolean {
                adapterItem.filter(newText)
                return true
            }

        })
    }


}