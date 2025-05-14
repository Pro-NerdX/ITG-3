import org.example.gates.AND
import org.example.gates.OR
import org.example.gates.XOR
import org.example.utils.Wire

object FullAdder {
    fun simulate(a: Boolean, b: Boolean, cIn: Boolean): Pair<Boolean, Boolean> {
        return this.sim(a, b, cIn, false)
    }

    fun sim(a: Boolean, b: Boolean, cIn: Boolean, flag: Boolean): Pair<Boolean, Boolean> {
        val aWire = Wire(false, mutableListOf())
        val bWire = Wire(false, mutableListOf())
        val cInWire = Wire(false, mutableListOf())

        // put all of them in ternary XOR-gate for output s
        val sWire = Wire(false, mutableListOf())
        val sumXOR = XOR(aWire, bWire, cInWire, sWire)
        aWire.connectedGates.add(sumXOR)
        bWire.connectedGates.add(sumXOR)
        cInWire.connectedGates.add(sumXOR)

        // (a ^ b) v (a ^ cIn) v (b ^ cIn)
        // AND-gates
        val firstAndOutWire = Wire(false, mutableListOf())
        val firstAND = AND(aWire, bWire, null, firstAndOutWire)
        aWire.connectedGates.add(firstAND)
        bWire.connectedGates.add(firstAND)

        val secondAndOutWire = Wire(false, mutableListOf())
        val secondAND = AND(aWire, cInWire, null, secondAndOutWire)
        aWire.connectedGates.add(secondAND)
        cInWire.connectedGates.add(secondAND)

        val thirdAndOutWire = Wire(false, mutableListOf())
        val thirdAND = AND(bWire, cInWire, null, thirdAndOutWire)
        bWire.connectedGates.add(thirdAND)
        cInWire.connectedGates.add(thirdAND)

        // OR-gates
        val cOutWire = Wire(false, mutableListOf())
        val carryOutOR = OR(firstAndOutWire, secondAndOutWire, thirdAndOutWire, cOutWire)
        firstAndOutWire.connectedGates.add(carryOutOR)
        secondAndOutWire.connectedGates.add(carryOutOR)
        thirdAndOutWire.connectedGates.add(carryOutOR)

        // flag handling
        if (flag) {
            aWire.setVoltage(true)
            bWire.setVoltage(true)
            cInWire.setVoltage(true)
        }

        // execution
        aWire.setVoltage(a)
        bWire.setVoltage(b)
        cInWire.setVoltage(cIn)

        return Pair(sWire.getVoltage(), cOutWire.getVoltage())
    }

    fun simulateWithInitial111(a: Boolean, b: Boolean, cIn: Boolean): Pair<Boolean, Boolean> {
        return this.sim(a, b, cIn, true)
    }
}
