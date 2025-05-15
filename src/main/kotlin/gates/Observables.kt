package org.example.gates

import org.example.utils.Global
import org.example.utils.Wire

/**
 * In this file are all necessary gates.
 */

class AND(
    in1: Wire,
    in2: Wire,
    in3: Wire? = Global.trueWire,
    out: Wire
): TernaryGate(in1, in2, in3, out) {

    override fun operator() {
        val newState: Boolean = this.in1.getVoltage() && this.in2.getVoltage() && (this.in3?.getVoltage() ?: true)
        if (newState == out.getVoltage()) return
        this.out.setVoltage(newState)
    }
}

class OR(
    in1: Wire,
    in2: Wire,
    in3: Wire? = Global.falseWire,
    out: Wire
): TernaryGate(in1, in2, in3, out) {
    override fun operator() {
        val newState: Boolean = this.in1.getVoltage() || this.in2.getVoltage() || (this.in3?.getVoltage() ?: false)
        if (newState == out.getVoltage()) return
        this.out.setVoltage(newState)
    }
}

class XOR(
    in1: Wire,
    in2: Wire,
    in3: Wire? = Global.falseWire,
    out: Wire
): TernaryGate(in1, in2, in3, out) {
    override fun operator() {
        val sendHelp1: Boolean = (this.in1.getVoltage() || this.in2.getVoltage()) &&
                !(this.in1.getVoltage() && this.in2.getVoltage())
        val sendHelp2: Boolean = in3?.getVoltage() ?: false
        val newState: Boolean = (sendHelp1 || sendHelp2) && !(sendHelp1 && sendHelp2)
        if (newState == out.getVoltage()) return
        this.out.setVoltage(newState)
    }
}

class NOT(
    in1: Wire,
    out: Wire
): UnaryGate(in1, out) {
    override fun operator() {
        val newState: Boolean = !this.out.getVoltage()
        if (newState == out.getVoltage()) return
        this.out.setVoltage(!this.out.getVoltage())
    }
}

class NAND(
    val in1: Wire,
    val in2: Wire,
    val out: Wire
): Gate() {
    init {
        in1.connectedGates.add(this)
        in2.connectedGates.add(this)
    }

    override fun operator() {
        val newState: Boolean = !(in1.getVoltage() && in2.getVoltage())
        if (newState == out.getVoltage()) return
        this.out.setVoltage(!this.out.getVoltage())
    }
}
