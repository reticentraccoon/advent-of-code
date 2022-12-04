package aoc.y2022.day1

import java.io.File
import java.util.PriorityQueue

fun findTopNCalories(allElfCalories: List<List<Int>>, n: Int): Int {
    val minHeap = PriorityQueue<Int>(n)
    for (elfCalories in allElfCalories) {
        val elfCalorieSum = elfCalories.sum()
        if (minHeap.size < n) {
            minHeap.add(elfCalorieSum)
        } else if (minHeap.peek() < elfCalorieSum) {
            minHeap.poll()
            minHeap.add(elfCalorieSum)
        }
    }
    return minHeap.sum()
}

/**
 * https://adventofcode.com/2022/day/1/
 */
fun main() {
    val allElfCalories = mutableListOf<MutableList<Int>>()
    allElfCalories.add(mutableListOf())

    val allLines = File("./src/main/resources/input_day1_caloriecounting").readLines()
    for (line in allLines) {
        if (line == "") {
            allElfCalories.add(mutableListOf())
        } else {
            allElfCalories.last().add(line.toInt())
        }
    }

    println(findTopNCalories(allElfCalories, 3))
}