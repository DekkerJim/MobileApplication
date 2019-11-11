package com.example.rockpaperscissors.Database

import android.content.Context
import com.example.rockpaperscissors.Model.Game

class GameRepository(context : Context) {

    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    suspend fun insertGame(game: Game) {
        gameDao.insertGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun updateGame(reminder: Game) {
        gameDao.updateGame(reminder)
    }

    suspend fun deleteAllGames(){
        gameDao.deleteAllGames()
    }
}