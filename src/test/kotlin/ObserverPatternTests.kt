import org.example.gates.FullAdder
import org.example.utils.Global
import org.example.utils.Wire
import kotlin.test.Test

class ObserverPatternTests {

    val aWire = Wire(false, mutableListOf())
    val bWire = Wire(false, mutableListOf())
    val cInWire = Wire(false, mutableListOf())
    val sWire = Wire(false, mutableListOf())
    val cOutWire = Wire(false, mutableListOf())
    val fullAdder = FullAdder(aWire, bWire, cInWire, sWire, cOutWire)

    init {
        Global.allActiveGates.add(fullAdder)
    }

    @Test
    fun testRandomizedSelection() {
        val sendHelp = listOf(true, false)
        for (a in sendHelp.shuffled()) {
            for (b in sendHelp.shuffled()) {
                for (cIn in sendHelp.shuffled()) {
                    fullAdder.aWire.setVoltage(a)
                    fullAdder.bWire.setVoltage(b)
                    fullAdder.cInWire.setVoltage(cIn)

                    val actualResult: Pair<Boolean, Boolean> = Pair(
                        fullAdder.sWire.getVoltage(),
                        fullAdder.cOutWire.getVoltage()
                    )
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

    @Test
    fun testWithInitialSelection111() {
        fullAdder.aWire.setVoltage(true)
        fullAdder.bWire.setVoltage(true)
        fullAdder.cInWire.setVoltage(true)

        val sendHelp = listOf(true, false)
        for (a in sendHelp) {
            for (b in sendHelp) {
                for (cIn in sendHelp) {
                    fullAdder.aWire.setVoltage(a)
                    fullAdder.bWire.setVoltage(b)
                    fullAdder.cInWire.setVoltage(cIn)

                    val actualResult: Pair<Boolean, Boolean> = Pair(
                        fullAdder.sWire.getVoltage(),
                        fullAdder.cOutWire.getVoltage()
                    )
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
