package aoc.y2022.day5

import java.io.File
import java.util.Stack

fun findFinalTops(inputPathname: String, isNewCrane: Boolean = false): String {
    val (allLines, stacks, blankLineIndex) = getInitialStackConfiguration(inputPathname)

    for (i in (blankLineIndex + 1) until allLines.size) {
        val moves = allLines[i].split(" ")
        val count = moves[1].toInt()
        val fromIndex = moves[3].toInt() - 1
        val toIndex = moves[5].toInt() - 1

        val tempStack = Stack<Char>()
        if (isNewCrane) {
            repeat(count) {
                tempStack.push(stacks[fromIndex].pop())
            }

            repeat(count) {
                stacks[toIndex].push(tempStack.pop())
            }
        } else {
            repeat(count) {
                stacks[toIndex].push(stacks[fromIndex].pop())
            }
        }
    }

     return stacks.map { it.peek() }.fold("") { acc, c -> acc + c }
}

private fun getInitialStackConfiguration(inputPathname: String): FileMeta {
    val allLines = File(inputPathname).readLines()

    var blankLineIndex = 0
    for (i in 0..allLines.size) {
        if (allLines[i].isBlank()) {
            blankLineIndex = i
            break
        }
    }
    val stacks =  allLines[blankLineIndex - 1]
        .split("\\s+".toRegex())
        .filter { it.isNotBlank() }
        .map { Stack<Char>() }

    stacks.forEachIndexed { index, stack ->
        for (i in (blankLineIndex-2) downTo 0) {
            if (allLines[i][4 * index + 1] != ' ') {
                stack.push(allLines[i][4 * index + 1])
            }
        }
    }

    return FileMeta(allLines, stacks, blankLineIndex)
}

data class FileMeta(val allLines: List<String>, val stacks: List<Stack<Char>>, val blankLineIndex: Int)