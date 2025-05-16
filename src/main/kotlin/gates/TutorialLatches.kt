package org.example.gates

import org.example.utils.Global
import org.example.utils.Wire

/**
 * @Robert code during the tutorial on Friday
 */
class DLatch(
    val dWire: Wire,
    val eWire: Wire,
    val qWire: Wire,
    val notQWire: Wire
): Gate() {
    val dSideOut = Wire(false, mutableListOf())
    val dSideNAND = NAND(dWire, eWire, dSideOut)

    val eSideOut = Wire(false, mutableListOf())
    val eSideNAND = NAND(dSideOut, eWire, eSideOut)

    val sSideNAND = NAND(dSideOut, notQWire, qWire)
    val rSideNAND = NAND(qWire, eSideOut, notQWire)

    init {
        Global.allActiveGates.add(this)
    }

    override fun operator() {
        // Nothing?
    }
}

class DFlipFlop(
    val dWire: Wire,
    val clkWire: Wire,
    val qWire: Wire,
    val notQWire: Wire
): Gate() {
    val notClkWire = Wire(false, mutableListOf())
    val firstNOT = NOT(clkWire, notClkWire)

    val firstUpperOutWire = Wire(false, mutableListOf())
    val firstUpperNAND = NAND(dWire, notClkWire, firstUpperOutWire)

    val firstLowerOutWire = Wire(false, mutableListOf())
    val firstLowerNAND = NAND(firstUpperOutWire, notClkWire, firstLowerOutWire)

    val secondUpperOutWire = Wire(false, mutableListOf())
    val secondLowerOutWire = Wire(false, mutableListOf())

    val secondUpperNAND = NAND(firstUpperOutWire, secondLowerOutWire, secondUpperOutWire)
    val secondLowerNAND = NAND(firstLowerOutWire, secondUpperOutWire, secondLowerOutWire)

    val secondDLatchUpperOut = Wire(false, mutableListOf())
    val secondDLatchLowerOut = Wire(false, mutableListOf())

    val secondDLatch = DLatch(
        secondUpperOutWire,
        clkWire,
        secondDLatchUpperOut,
        secondDLatchLowerOut
    )

    val lastUpperNAND = NAND(secondDLatchUpperOut, notQWire, qWire)
    val lastLowerNAND = NAND(secondDLatchLowerOut, qWire, notQWire)

    override fun operator() {
        // Nothing?
    }
}
