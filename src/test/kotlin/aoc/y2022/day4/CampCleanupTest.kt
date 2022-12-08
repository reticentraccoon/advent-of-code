package aoc.y2022.day4

import org.junit.jupiter.api.Test

class CampCleanupTest {
    @Test
    fun `test findCompleteOverlaps`() {
        println(findCompleteOverlaps("./src/main/resources/input_day4_campcleanup"))
    }

    @Test
    fun `test findPartialOverlaps`() {
        println(findPartialOverlaps("./src/main/resources/input_day4_campcleanup"))
    }
}