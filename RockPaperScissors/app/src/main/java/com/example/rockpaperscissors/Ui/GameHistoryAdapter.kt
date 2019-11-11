package com.example.rockpaperscissors.Ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.Model.Game
import com.example.rockpaperscissors.Model.Choice
import com.example.rockpaperscissors.Model.Result
import com.example.rockpaperscissors.R
import kotlinx.android.synthetic.main.content_history.view.*

class GameHistoryAdapter (private val games: List<Game>, private val context: Context) :
    RecyclerView.Adapter<GameHistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.content_history, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    //show gameresults
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {

            fun result(result: Result): String {
                return when (result) {
                    Result.WIN -> "You win!"
                    Result.LOSS -> "Computer wins!"
                    Result.DRAW -> "Draw!"
                }
            }

            itemView.tvResult.text = result(game.result)
            itemView.tvDate.text = game.date.toString()
            itemView.ivPlayer.setImageDrawable(game.playerMove.getDrawable(context))
            itemView.ivAi.setImageDrawable(game.aiMove.getDrawable(context))
        }
    }

}