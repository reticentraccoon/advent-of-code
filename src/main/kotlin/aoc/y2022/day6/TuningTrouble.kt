package aoc.y2022.day6

import java.io.File

fun findMarker(pathname: String, length: Int): Int {
    /**
     * One way to do this was to use a sliding window and for each window convert the list to set and check its size.
     *
     * Following implementation does the same but leverages features provided by Kotlin directly.
     */
    File(pathname).useLines {
        return it.first()
            .windowed(length, 1, false)
            .indexOfFirst { win -> win.toSet().size == length } + length
    }
}