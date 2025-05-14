import kotlin.test.Test

class ObserverPatternTests {

    @Test
    fun testRandomizedSelection() {
        val sendHelp = listOf(true, false)
        for (a in sendHelp.shuffled()) {
            for (b in sendHelp.shuffled()) {
                for (cIn in sendHelp.shuffled()) {
                    val actualResult: Pair<Boolean, Boolean> = FullAdder.simulate(a, b, cIn)
                    val inputList = listOf(a, b, cIn)
                    val expectedResult = when (inputList.count{ it }) {
                        0 -> Pair(false, false)
                        1 -> Pair(true, false)
                        2 -> Pair(false, true)
                        3 -> Pair(true, true)
                        else -> throw IllegalStateException("testRandomizedSelection: unreachable code reached!")
                    }
                    assert(actualResult == expectedResult)
                }
            }
        }
    }
}
