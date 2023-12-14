package day6

import java.io.File
import kotlin.math.sqrt

val input = File("src/main/kotlin/day6/input").readLines().toList()

fun String.getValues() = this.substring(this.indexOf(':') + 1)
    .split(' ').filter { it.isNotBlank() }.map { it.trim().toInt() }

val times = input[0].getValues()
val distances = input[1].getValues()

fun main() {
    part2()
}

fun part1() {
   var res = 1
   times.getDistances().mapIndexed { i, potentialDistances ->
        potentialDistances.count { distance -> distance > distances[i] } }
       .forEach { res *= it }

    println(res)
}

fun part2() {
    val time = times.joinToString("").toULong()
    val distance = distances.joinToString("").toULong()

    println( time.getDistances().count { it > distance } )
}

fun List<Int>.getDistances() : List<List<Int>> {
    return this.map {
        (0..it).map { timeStationary ->
            val travelTime = it - timeStationary
            return@map timeStationary * travelTime
        }
    }
}
fun ULong.getDistances() : List<ULong> {
    return ULongRange(0u, this).map { timeStationary ->
        val travelTime = this - timeStationary
        return@map timeStationary * travelTime
    }
}
