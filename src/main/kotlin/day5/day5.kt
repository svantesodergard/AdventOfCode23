package day5

import java.io.File
import java.math.BigInteger


val input = File("src/main/kotlin/day5/input").readLines().toList()

val sourceToDestination = mutableMapOf<String, Map<Int, List<Int>>>()

fun main() {
    part1()
}

fun part1() {
    var numbers = input[0].substring(input[0].indexOf(' ') + 1).split(' ').map { it.toULong() }
    val maps = input.filterIndexed { i, _ -> i >= 2}.split("").map { it.filterIndexed { i, _ -> i != 0} }
    println(numbers)
    println(maps)

    (maps.indices).forEach {currentMapI ->
        numbers = numbers.map { num ->
            val ranges = maps[currentMapI].map { range ->
                val destinationRangeStart = range.substring(0, range.indexOf(' ')).toULong()
                val sourceRangeStart = range.substring(range.indexOf(' ') + 1, range.lastIndexOf(' ')).toULong()
                val rangeLength = range.substring(range.lastIndexOf(' ') + 1).toULong()

                (ULongRange(destinationRangeStart, destinationRangeStart + rangeLength - 1u)) to
                        (ULongRange(sourceRangeStart, sourceRangeStart + rangeLength - 1u))
            }

            val correctRange = ranges.find { it.second.contains(num) }
            val index = correctRange?.second?.indexOf(num) ?: return@map num
            correctRange.first.first + index.toUInt()
        }
    }
    println(numbers.min())
}

fun ULongRange.indexOf(num: ULong) = if (this.contains(num)) num - this.first else null;


class BigIntRange(val start : BigInteger, val range : BigInteger) {
    fun contains(num : BigInteger) = num >= this.start && num  - range <= this.start
    fun indexOf(num : BigInteger) = if (this.contains(num)) num - this.start else null

    fun get(index : BigInteger) = if (this.contains(forceGet(index))) forceGet(index) else null

    private fun forceGet(index: BigInteger) = this.start + index
}

fun List<String>.split(s : String) : List<List<String>> {
    val list = mutableListOf<List<String>>()
    val currentList = mutableListOf<String>()
    this.forEach {
        if (it == s) {
            list.add(currentList.map { l -> l })
            currentList.clear()
        } else {
            currentList.add(it)
        }
    }
    return list
}