import org.example.gates.NAND
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

    @Test
    fun testNAND() {
        val in1 = Wire(false, mutableListOf())
        val in2 = Wire(false, mutableListOf())
        val out = Wire(false, mutableListOf())

        val nand = NAND(in1, in2, out)

        // T NAND F => NAND == TRUE
        in1.setVoltage(true)
        assert(nand.out.getVoltage())

        // T NAND T => NAND == FALSE
        in2.setVoltage(true)
        assertFalse(nand.out.getVoltage())

        // F NAND T => NAND == TRUE
        in1.setVoltage(false)
        assert(nand.out.getVoltage())

        // F NAND F => NAND == TRUE
        in2.setVoltage(false)
        assert(nand.out.getVoltage())
    }
}
