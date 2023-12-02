package day2

import java.io.File

val input = File("src/main/kotlin/day2/test").readLines().toList()
val games = input.map { it.substring(it.indexOf(':') + 2).split(';') }
    .map {game ->  game.map { sets -> sets.split(',').map { set -> set.trim() } } }

val bag = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
)

fun main() = part1()

fun part1() {
    val possible = games.map { it.flatten() }.map { g -> g.all { bag[it.parseColor()]!! >= it.parseInt() } }
    println( possible.mapIndexed { index, b -> if (b) index + 1 else 0 }.sum() )
}

fun part2() {
    println( games.sumOf { game ->
        //Group by colors sorted by count
        val colors: List<String> = game.flatten()
        val colorsSorted = colors.sortedBy { it.parseInt() }.sortedBy { it.parseColor() }

        //Max for each color
        val requiredColors = bag.keys.map { c -> colorsSorted.filter { it.contains(c) }.maxOfOrNull { it.parseInt() } }

        //Calculate product
        requiredColors[0]!! * requiredColors[1]!! * requiredColors[2]!!
    })
}

fun String.parseInt() = this.substring(0, this.indexOf(' ')).toInt()

fun String.parseColor() = this.substring(this.indexOf(' ') + 1).trim()