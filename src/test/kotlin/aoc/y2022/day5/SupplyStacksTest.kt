package aoc.y2022.day5

import kotlin.test.Test

class SupplyStacksTest {
    @Test
    fun `test findFinalTops`() {
        println(findFinalTops("./src/main/resources/input_day5_supplystacks"))
    }

    @Test
    fun `test findFinalTopsWithNewCrane`() {
        println(findFinalTops("./src/main/resources/input_day5_supplystacks", true))
    }
}