package day9

import java.io.File
import kotlin.math.abs

val input = File("src/main/kotlin/day9/input").readLines().toList()

fun main() = part2()

fun part1() = println( input.sumOf { it.getSequences().first().last() } )

fun part2() = println( input.sumOf { it.getSequences().first().first() } )


fun String.getSequences(): MutableList<MutableList<Int>> {
    val sequences = mutableListOf(
        this.split(' ').map { it.toInt() }.toMutableList()
    )

    while (sequences.last().any { it != 0 }) {
        val values = sequences.last().map { it }
        sequences.add(values.mapIndexed { i, current ->
            if (i + 1 == values.size) {
                null
            } else {
                -(current - values[i + 1])
            }
        }.filterNot { it == null } as MutableList<Int>)
    }

    sequences.last().add(0)
    for (i in (1 until sequences.size).reversed()) {
        val currentSequence = sequences[i]
        val nextSequence = sequences[i - 1]

        //part1
        nextSequence.add(nextSequence.last() + currentSequence.last())
        //part2
        nextSequence.add(0, nextSequence.first() - currentSequence.first())
    }

    return sequences
}