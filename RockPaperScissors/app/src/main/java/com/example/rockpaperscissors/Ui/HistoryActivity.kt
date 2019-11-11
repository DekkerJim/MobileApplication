package com.example.rockpaperscissors.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.Database.GameRepository
import com.example.rockpaperscissors.Model.Game
import com.example.rockpaperscissors.R
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository
    private val gamesList = arrayListOf<Game>()
    private val gameHistoryAdapter = GameHistoryAdapter(gamesList, this)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    //init declarization
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Your Game History"

        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvHistory.adapter = gameHistoryAdapter
        rvHistory.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        getGames()
    }

    //get all games from database function
    private fun getGames() {
        mainScope.launch {
            val gamesList = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            this@HistoryActivity.gamesList.clear()
            this@HistoryActivity.gamesList.addAll(gamesList)
            this@HistoryActivity.gameHistoryAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_history, menu)
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
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
        }
        getGames()
    }
}
