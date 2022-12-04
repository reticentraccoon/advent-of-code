package aoc

import java.io.File

fun findPrioritySum(rucksacks: List<String>): Int {
    return rucksacks.map { rucksack ->
        val characterIndices = mutableMapOf<Char, Int>()
        for ((index, item) in rucksack.toCharArray().withIndex()) {
            if (index >= rucksack.length/2
                && item in characterIndices
                && characterIndices[item]!! < rucksack.length/2) {
                return@map item
            }
            characterIndices[item] = index
        }
        error("Not happening.")
    }.sumOf { getPriorityValue(it) }
}

fun findGroupSums(inputRucksacks: List<String>): Int {
    return inputRucksacks.chunked(3).map { rucksackGroup ->
        rucksackGroup[0].toCharArray()
            .intersect(rucksackGroup[1].toCharArray().toSet())
            .intersect(rucksackGroup[2].toCharArray().toSet())
            .first()
    }.sumOf { getPriorityValue(it) }
}

fun getPriorityValue(character: Char): Int {
    return if (character.isUpperCase()) {
        character - 'A' + 27
    } else {
        character - 'a' + 1
    }
}

fun main() {
    val inputRucksacks = File("./src/main/resources/input_day3_rucksackreorganization").readLines()
    println(findPrioritySum(inputRucksacks))
    println(findGroupSums(inputRucksacks))
}