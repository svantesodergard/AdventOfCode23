package day8

import java.io.File
import java.lang.Exception
import java.util.InputMismatchException


val input = File("src/main/kotlin/day8/input").readLines().toList()

val instructions = input[0]

val nodes = input.filterIndexed { i, _ -> i >= 2 }.associate {
    val current = it.substring(0, it.indexOf(' '))
    val left = it.substring(it.indexOf('(') + 1, it.indexOf(','))
    val right = it.substring(it.indexOf(',') + 2, it.indexOf(')'))
    current to Pair(left, right)
}

fun main() = part2()

fun part2() {
    val queue = instructions.toMutableList()
    val stepsToEnd = mutableMapOf<String, Int>()

}

fun part1() {
    var steps = 0
    var currentNode : String? = "AAA"

    while (currentNode != "ZZZ") {
        val instruction = instructions[steps % instructions.length]
        currentNode = when (instruction) {
            'L' -> nodes[currentNode]?.first
            'R' -> nodes[currentNode]?.second
            else -> null
        }
        steps++
    }

    println(steps)
}