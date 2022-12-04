package aoc.y2022.day3

import java.io.File

/**
 * Explanation:
 *   1. Take each string in the rucksacks list and split it into two strings of equal size.
 *   2. As part of the chunking operation convert the string to the set of characters in the string.
 *   3. At this point, we have a list where each element of the list is a list containing two sets. We need to find the character common to these two sets.
 *   4. Reduce each element in the top level list (lists with two sets in them) to a list of characters that are common in the two sets (there is just one).
 *   5. FlatMap the list of characters into one list and sum their priority levels.
 */
fun findPrioritySum(inputPathname: String): Int {
    val rucksacks = File(inputPathname).readLines()
    return rucksacks
        .map { it.chunked(it.length/2) { s -> s.toSet() } }
        .flatMap { it.reduce { acc, next -> acc.intersect(next) } }
        .sumOf { priorityValue(it) }
}

/**
 * Explanation:
 *   1. Chunk the input list into a list containing lists of 3 strings.
 *   2. While chunking map the strings to set of characters.
 *   3. At this point, we have a list where each element of the list is a list containing 3 sets. We need to find the character common to these 3 sets.
 *   4. Reduce each element in the top level list (lists with 3 sets in them) to a list of characters that are common in the 3 sets (there is just one).
 *   5. FlatMap the list of characters into one list and sum their priority levels.
 */
fun findGroupsSum(inputPathname: String): Int {
    val rucksacks = File(inputPathname).readLines()
    return rucksacks
        .chunked(3) { it.map { str -> str.toSet() }}
        .flatMap { it.reduce { acc, next -> acc.intersect(next) } }
        .sumOf { priorityValue(it) }
}

fun priorityValue(character: Char): Int {
    return if (character.isUpperCase()) {
        character - 'A' + 27
    } else {
        character - 'a' + 1
    }
}