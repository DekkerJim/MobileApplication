package com.example.rockpaperscissors.Model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.rockpaperscissors.R
import java.util.*

enum class Choice(val string: String) {
    ROCK("Rock"),
    PAPER("Paper"),
    SCISSORS("Scissors");

    override fun toString(): String{
        return string
    }


    fun getDrawable(context: Context): Drawable? {
        val drawableRef = when(this) {
            ROCK -> R.drawable.rock
            PAPER -> R.drawable.paper
            SCISSORS -> R.drawable.scissors
        }
        return ContextCompat.getDrawable(context, drawableRef)

    }

    companion object {
        fun getRandom(): Choice {
            val random = Random()
            return values()[random.nextInt(values().size)]
        }
    }

}