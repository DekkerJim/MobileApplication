package com.example.comicproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comicproject.R
import com.example.comicproject.database.ComicRepository
import com.example.comicproject.model.entity.Comic
import com.example.comicproject.model.viewmodel.SavedActivityViewModel
import kotlinx.android.synthetic.main.activity_saved.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SavedActivity : AppCompatActivity() {

    private val comics = arrayListOf<Comic>()
    private val comicSaveAdapter = ComicSaveAdapter(comics)

    private lateinit var viewModel: SavedActivityViewModel
    //private lateinit var comicRepository: ComicRepository
    //private val mainScope = CoroutineScope(Dispatchers.Main)

    companion object {
        val SAVE_VALUE = "COMIC_SAVE"
    }

    //init declarization
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Your saved comics"

        initViews()
        initViewModel()
    }

    private fun initViews() {
        rvComics.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvComics.adapter = comicSaveAdapter
        createItemTouchHelper().attachToRecyclerView(rvComics)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SavedActivityViewModel::class.java)

        // Observe reminders from the view model, update the list when the data is changed.
        viewModel.comics.observe(this, Observer { reminders ->
            this@SavedActivity.comics.clear()
            this@SavedActivity.comics.addAll(reminders)
            comicSaveAdapter.notifyDataSetChanged()
        })
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val reminderToDelete = comics[position]

                viewModel.deleteComic(reminderToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.saved_menu, menu)
        return true
    }

    //toolbar button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete -> {
                clearHistory()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //clear games history from database
    private fun clearHistory() {
        viewModel = ViewModelProviders.of(this).get(SavedActivityViewModel::class.java)

        viewModel.deleteComics()
    }
}
