package day4

import java.io.File
import kotlin.math.pow

val input = File("src/main/kotlin/day4/input").readLines().toList()

fun main() = part2()
fun part1() = println (input.map { it.countWinningNumbers() }.sumOf { 2.toDouble().pow(it - 1).toInt() })

fun part2() {
    var sum = 0

    fun Int.drawCard() {
        (this + 1 until this + 1 + input.getCard(this).countWinningNumbers()).forEach {
            it.drawCard()
        }
        sum++
        println(sum)
    }

    (1 .. 6).forEach { it.drawCard() }
    println(sum)
}

fun List<String>.getCard(gameIndex : Int) = input[gameIndex - 1]

fun String.countWinningNumbers() = this.drawnNumbers().count { this.winningNumbers().contains(it) }

fun String.winningNumbers() = this.substring(this.indexOf(':') + 2, this.indexOf('|')).trim()
    .split(' ').filter { it.isNotBlank() }.map { it.trim().toInt() }

fun String.drawnNumbers() = this.substring(this.indexOf('|') + 2).trim().split(' ')
    .filter { it.isNotBlank() } .map { it.trim().toInt() }
