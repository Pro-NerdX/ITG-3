package org.example.gates

import org.example.utils.Global
import org.example.utils.Wire

class FullAdder(
    val aWire: Wire,
    val bWire: Wire,
    val cInWire: Wire,
    val sWire: Wire,
    val cOutWire: Wire
): Gate() {
    // wires in between
    private val firstAndOutWire = Wire(false, mutableListOf())
    private val secondAndOutWire = Wire(false, mutableListOf())
    private val thirdAndOutWire = Wire(false, mutableListOf())

    // gates
    // (a XOR b XOR cIn) = s
    private val sumXOR = XOR(aWire, bWire, cInWire, sWire)

    // (a ^ b) v (a ^ cIn) v (b ^ cIn) = cOut
    // AND-gates
    private val firstAND = AND(aWire, bWire, null, firstAndOutWire)
    private val secondAND = AND(aWire, cInWire, null, secondAndOutWire)
    private val thirdAND = AND(bWire, cInWire, null, thirdAndOutWire)
    // OR-gate
    private val carryOutOR = OR(firstAndOutWire, secondAndOutWire, thirdAndOutWire, cOutWire)

    init {
        aWire.connectedGates.add(this)
        bWire.connectedGates.add(this)
        cInWire.connectedGates.add(this)
    }

    /**
     * Inputs in the following order:
     * a := 1st summand
     * b := 2nd summand
     * cIn := carry bit
     *
     * Outputs:
     * s := sum
     * cOut := carry bit
     */
    override fun operator() {
        this.sWire.setVoltage(sumXOR.out.getVoltage())
        this.cOutWire.setVoltage(carryOutOR.out.getVoltage())
    }
}
