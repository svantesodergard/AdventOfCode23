package day4

import java.io.File
import kotlin.math.pow

val input = File("src/main/kotlin/day4/input").readLines().toList()

fun main() = part2()
fun part1() = println (input.map { it.countWinningNumbers() }.sumOf { 2.toDouble().pow(it - 1).toInt() })

fun part2() {
    val drawnCards = mutableMapOf<Int, Int>()

    fun Int.drawCard() : Int {
        var sum = 1
        (this + 1 until this + 1 + input.getCard(this).countWinningNumbers()).forEach {
            if (drawnCards.contains(it)) {
                sum += drawnCards[it]!!
            } else {
                sum += it.drawCard()
                drawnCards[it] = sum - 1
            }
        }
        return sum
    }

    println((1 .. input.size).sumOf { it.drawCard() })
}

fun List<String>.getCard(gameIndex : Int) = input[gameIndex - 1]

fun String.countWinningNumbers() = this.drawnNumbers().count { this.winningNumbers().contains(it) }

fun String.winningNumbers() = this.substring(this.indexOf(':') + 2, this.indexOf('|')).trim()
    .split(' ').filter { it.isNotBlank() }.map { it.trim().toInt() }

fun String.drawnNumbers() = this.substring(this.indexOf('|') + 2).trim().split(' ')
    .filter { it.isNotBlank() } .map { it.trim().toInt() }
