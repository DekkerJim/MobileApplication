package com.example.rockpaperscissors.Model

enum class Result(val string: String) {
    WIN("Win"),
    DRAW("Draw"),
    LOSS("Loss");

    override fun toString(): String {
        return string
    }
}