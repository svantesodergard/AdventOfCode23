package day1

import java.io.File

val input = File("src/main/kotlin/day1/input").readLines().toList()

fun main() = input.part2()

fun List<String>.part1() =
    println(this.map { s -> "" + s.first { it.isDigit() } + s.last { it.isDigit() } }.sumOf { it.toInt() })

val numbers = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun List<String>.part2() {
    input.map { s ->
        var new = s
        numbers.forEach { new = new.replace(it.key, it.value.toString()) }
        new
    }.part1()
}
