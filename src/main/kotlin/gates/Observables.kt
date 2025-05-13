package org.example.gates

import org.example.utils.Global
import org.example.utils.Wire

/**
 * In this file are all necessary gates.
 */

class AND(
    val in1: Wire,
    val in2: Wire,
    val in3: Wire? = Global.trueWire,
    val out: Wire
): Gate() {
    override fun operator() {
        val newState: Boolean = this.in1.getVoltage() && this.in2.getVoltage() && (this.in3?.getVoltage() ?: true)
        if (newState == out.getVoltage()) return
        this.out.setVoltage(newState)
    }
}

class OR(
    val in1: Wire,
    val in2: Wire,
    val in3: Wire? = Global.falseWire,
    val out: Wire
): Gate() {
    override fun operator() {
        val newState: Boolean = this.in1.getVoltage() || this.in2.getVoltage() || (this.in3?.getVoltage() ?: false)
        if (newState == out.getVoltage()) return
        this.out.setVoltage(newState)
    }
}

class XOR(
    val in1: Wire,
    val in2: Wire,
    val in3: Wire? = Global.falseWire,
    val out: Wire
): Gate() {
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
    val in1: Wire,
    val out: Wire
): Gate() {
    override fun operator() {
        // Note: Check for change not necessary, as gate won't be notified if [in1] doesn't change.
        this.out.setVoltage(!this.in1.getVoltage())
    }
}
