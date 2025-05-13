package org.example.gates

import org.example.utils.Global
import org.example.utils.Wire

class AND(
    val in1: Wire,
    val in2: Wire,
    val in3: Wire? = Global.trueWire,
    val out: Wire
): Gate() {
    override fun operator() {
        this.out.setVoltage(this.in1.getVoltage() && this.in2.getVoltage() && (this.in3?.getVoltage() ?: true))
    }
}

class OR(
    val in1: Wire,
    val in2: Wire,
    val in3: Wire? = Global.falseWire,
    val out: Wire
): Gate() {
    override fun operator() {
        this.out.setVoltage(this.in1.getVoltage() || this.in2.getVoltage() || (this.in3?.getVoltage() ?: false))
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
        this.out.setVoltage(
            (sendHelp1 || sendHelp2) && !(sendHelp1 && sendHelp2)
        )
    }
}

class NOT(
    val in1: Wire,
    val out: Wire
): Gate() {
    override fun operator() {
        this.out.setVoltage(!this.in1.getVoltage())
    }
}
