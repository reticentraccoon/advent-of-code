package aoc.y2022.day6

import kotlin.test.Test

class TuningTroubleTest {
    @Test
    fun `test findMarker start`() {
        println(findMarker("src/main/resources/input_day6_tuningtrouble", 4))
    }

    @Test
    fun `test findMarker message`() {
        println(findMarker("src/main/resources/input_day6_tuningtrouble", 14))
    }
}