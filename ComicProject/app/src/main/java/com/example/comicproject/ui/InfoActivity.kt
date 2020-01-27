package com.example.comicproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.comicproject.R
import com.example.comicproject.database.ComicRepository
import com.example.comicproject.model.api.Data
import com.example.comicproject.model.entity.Comic
import com.example.comicproject.model.viewmodel.InfoActivityViewModel
import kotlinx.android.synthetic.main.activity_info.*
import java.text.SimpleDateFormat

class InfoActivity : AppCompatActivity() {

    private lateinit var comicRepository: ComicRepository
    private lateinit var viewModel: InfoActivityViewModel
    private val comics = arrayListOf<Comic>()
    private val comicSaveAdapter = ComicSaveAdapter(comics)

    companion object {
        val COMIC_VALUE = "COMIC"
    }

    lateinit var data: Data
    val formatter = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreate(savedInstanceState: Bundle?) {
        //Log.w("MyList", "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        //setSupportActionBar(toolbar)
        data = intent.getParcelableExtra(COMIC_VALUE)

        supportActionBar?.title = data.attributes.slug
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        comicRepository = ComicRepository(this)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        //Log.w("MyList", "initViews")

        Glide.with(this).load(data.getPosterUrl()).into(ivPoster)
        tvTitle.text = data.attributes.slug
        tvStartDate.text = "Release: " + formatter.format(data.attributes.startDate)
        tvEndDate.text = formatter.format(data.attributes.endDate)
        tvSynopsis.text = data.attributes.synopsis

        val comic = Comic(
            slug = data.attributes.slug
            //id = data.id
            //synopsis = data.attributes.synopsis,
            //date =
        )

        btSave.setOnClickListener {
            //Log.w("BtnTest", "initViews")
            Log.d("myTag", comic.toString())
            viewModel.insertComic(comic)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(InfoActivityViewModel::class.java)

        // Observe comics from the view model, update the list when the data is changed.
        viewModel.comics.observe(this, Observer { reminders ->
            comics.clear()
            comics.addAll(reminders)
            comicSaveAdapter.notifyDataSetChanged()
        })
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //Log.w("MyList", "onOptionsItemSelected")

        return when (item?.itemId) {
            android.R.id.home -> { // Used to identify when the user has clicked the back button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
