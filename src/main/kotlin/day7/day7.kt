package day7

import java.io.File
import java.lang.IndexOutOfBoundsException

val input = File("src/main/kotlin/day7/test").readLines().toList()

val handToBid = input.associate { it.substring(0, it.indexOf(' ')) to it.substring(it.indexOf(' ') + 1).toInt() }

val cardToValue = mapOf(
    'A' to 14,
    'K' to 13,
    'Q' to 12,
    'J' to 11,
    'T' to 10
)

val types = listOf("AAAAA", "AAAA*", "AAABB", "AAA**", "AABB*", "AA***", "*****").map { it.toCharArray().sorted() }

fun main() {
    part2()
}

fun part1() {
    val handsSorted = handToBid.toSortedMap { a, b -> compareHands(b, a) }
    println(handsSorted)
    println((1..handsSorted.size).sumOf { (handsSorted.values.toList()[it - 1] * it).toULong() })
}

fun part2() {
    val handsSorted = handToBid.toSortedMap { a, b -> compareHands(b, a, true) }
    println(handsSorted)
    println((1..handsSorted.size).sumOf { (handsSorted.values.toList()[it - 1] * it).toULong() })
}

fun String.generic() : List<Char> {
    val twoMostCommonCards =
        this.toCharArray().filter { this.count { c -> c == it } >= 2 }
            .sortedBy { this.count { c -> c == it } }.reversed().toSet().filterIndexed { i, _ -> i < 2 }
    return this.toCharArray().map {
        listOf('A', 'B').getOrNull(twoMostCommonCards.indexOf(it)) ?: '*'
    }.sorted()
}

fun Char.cardValue() = cardToValue[this] ?: (""+this).toInt()

fun String.bestHand() : List<Char> {
    val nonJokers = this.replace('J', '*').generic().filter { it != '*' }
    return types.first { it.containsAll(nonJokers) }
}

fun compareHands(a : String, b : String, jokers: Boolean = false) : Int {
    val difference = if (jokers) {
        types.indexOf(a.bestHand()) - types.indexOf(b.bestHand())
    } else {
        types.indexOf(a.generic()) - types.indexOf(b.generic())
    }

    if (difference != 0) {
        return difference
    }

    a.forEachIndexed { i, cardA ->
        val cardB = b[i]
        val cardDifference = -cardA.cardValue() + cardB.cardValue()
        if (cardDifference != 0) {
            return cardDifference
        }
    }

    return 0
}