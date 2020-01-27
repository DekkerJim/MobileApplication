package com.example.comicproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.comicproject.R
import com.example.comicproject.model.api.Data
import com.example.comicproject.model.viewmodel.MainActivityViewModel
import com.example.comicproject.ui.InfoActivity.Companion.COMIC_VALUE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val comics = arrayListOf<Data>()
    private val comicAdapter = ComicAdapter(comics) { comic -> onComicClick(comic) }
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Comics"

        initViews()
        initViewModel()
    }

    private fun initViews() {
        btSubmit.setOnClickListener {
            viewModel.textInput.apply { value = etSearch.text.toString() }
            viewModel.getComics()
        }

        rvComics.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        rvComics.adapter = comicAdapter
    }

    private fun initViewModel() {
        // Initialize the MainActivityViewModel.
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

       //Log.i("MyList", comics.toString())

        // Observe the comic object.
        viewModel.comics.observe(this, Observer {
            comics.clear()
            comics.addAll(it)
            comicAdapter.notifyDataSetChanged()
        })

        // Observe the error message.
        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //Start saved activity
    private fun openHistory() {
        val intent = Intent(this, SavedActivity::class.java)
        startActivity(intent)
    }

    //toolbar button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.history -> {
                openHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onComicClick(data: Data) {
        val intent = Intent(this, InfoActivity::class.java).apply {
            putExtra(COMIC_VALUE, data)
        }
        startActivity(intent)
    }
}
