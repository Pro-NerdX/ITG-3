import org.example.gates.XOR
import org.example.utils.Wire
import kotlin.test.Test

import kotlin.test.assertFalse

class GateTests {
    val aWire = Wire(false, mutableListOf())
    val bWire = Wire(false, mutableListOf())
    val outWire = Wire(false, mutableListOf())

    @Test
    fun testXOR() {
        val xor = XOR(aWire, bWire, null, outWire)
        aWire.connectedGates.add(xor)
        bWire.connectedGates.add(xor)

        aWire.setVoltage(false)
        bWire.setVoltage(false)

        // both statements should refer to the same reference, but just in case...
        assertFalse(xor.out.getVoltage())
        assertFalse(outWire.getVoltage())

        // setting aWire to true -> XOR evaluates to true
        aWire.setVoltage(true)
        assert(xor.out.getVoltage())
        assert(outWire.getVoltage())

        // setting bWire to true -> XOR evaluates to false
        bWire.setVoltage(true)
        assertFalse(xor.out.getVoltage())
        assertFalse(outWire.getVoltage())
    }
}
