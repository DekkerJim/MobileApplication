package com.example.rockpaperscissors.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissors.Database.GameRepository
import com.example.rockpaperscissors.Model.Choice
import com.example.rockpaperscissors.Model.Game
import com.example.rockpaperscissors.Model.Result
import com.example.rockpaperscissors.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    //init declarization
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameRepository = GameRepository(this)
        supportActionBar?.title = "Rock, Paper, Scissors, Kotlin"

        initViews()
    }

    //btn onclick listeners
    private fun initViews() {
        btnRock.setOnClickListener { gameStart(Choice.ROCK) }
        btnPaper.setOnClickListener { gameStart(Choice.PAPER) }
        btnScissors.setOnClickListener { gameStart(Choice.SCISSORS) }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //gamestart function
    private fun gameStart(playerMove: Choice) {
        val aiMove = Choice.getRandom()
        val result = winnerDecision(playerMove, aiMove)
        val game = Game(
            date = Date(),
            result = result,
            aiMove = aiMove,
            playerMove = playerMove
        )

        showResult(game)
        gameSave(game)
    }

    //decision function
    private fun winnerDecision(playerMove: Choice, aiMove: Choice): Result{
        if (aiMove == playerMove) {
            return Result.DRAW
        }
        return when (playerMove) {
            Choice.ROCK -> if (playerMove == Choice.PAPER) Result.LOSS else Result.WIN
            Choice.PAPER -> if (playerMove == Choice.SCISSORS) Result.LOSS else Result.WIN
            Choice.SCISSORS -> if (playerMove == Choice.ROCK) Result.LOSS else Result.WIN
        }
    }

    //save to room database
    private fun gameSave(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
    }

    //show result (Win, Loss and Draw)
    private fun showResult(game: Game) {
        val gameResult = when {
            game.result == Result.WIN -> "You " + game.result.toString()
            game.result == Result.LOSS -> "Computer wins"
            else -> game.result.string
        }
        tvGameResult.text = gameResult
        ivAiMove.setImageDrawable(game.aiMove.getDrawable(this))
        ivPlayerMove.setImageDrawable(game.playerMove.getDrawable(this))
    }

    //Start history activity
    private fun openHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
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

}
