package aoc.y2022.day2

import java.io.File

val choiceScores = mutableMapOf(
    'R' to 1,
    'P' to 2,
    'S' to 3,
)

val outcomeScores = mutableMapOf(
    'L' to 0,
    'D' to 3,
    'W' to 6,
)

val outcomeMappings = mapOf(
    'L' to mapOf(
        'R' to 'S',
        'P' to 'R',
        'S' to 'P'
    ),
    'D' to mapOf(
        'R' to 'R',
        'P' to 'P',
        'S' to 'S'
    ),
    'W' to mapOf(
        'R' to 'P',
        'P' to 'S',
        'S' to 'R'
    ),
)

val outcomes = mapOf(
    ('R' to 'S') to 'L',
    ('P' to 'R') to 'L',
    ('S' to 'P') to 'L',
    ('R' to 'R') to 'D',
    ('P' to 'P') to 'D',
    ('S' to 'S') to 'D',
    ('R' to 'P') to 'W',
    ('P' to 'S') to 'W',
    ('S' to 'R') to 'W'
)

val mapInputAsChoice = mapOf(
    'X' to 'R',
    'Y' to 'P',
    'Z' to 'S'
)

val mapInputAsOutcome = mapOf(
    'X' to 'L',
    'Y' to 'D',
    'Z' to 'W'
)

val mapAdversarialChoice = mapOf(
    'A' to 'R',
    'B' to 'P',
    'C' to 'S'
)

fun findValue(scenario: Pair<Char, Char>): Int {
    return choiceScores[scenario.second]!! + outcomeScores[outcomes[scenario]!!]!!
}

fun mapInput(input: Pair<Char, Char>, isChoice: Boolean): Pair<Char, Char> {
    val adversarialChoice = mapAdversarialChoice[input.first]!!
    return if (isChoice) {
        Pair(
            adversarialChoice,
            mapInputAsChoice[input.second]!!)
    } else {
        Pair(
            adversarialChoice,
            outcomeMappings[mapInputAsOutcome[input.second]!!]!![adversarialChoice]!!)
    }
}

fun main() {
    val score = File("./src/main/resources/input_day2_rockpaperscissor")
        .readLines()
        .map { it.split(" ") }
        .map { mapInput(it[0].toCharArray()[0] to it[1].toCharArray()[0], false) }
        .sumOf { findValue(it) }

    println(score)
}