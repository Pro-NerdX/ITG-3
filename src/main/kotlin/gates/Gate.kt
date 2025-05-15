package org.example.gates

import org.example.utils.Global
import org.example.utils.Wire

abstract class Gate {
    val id: Int = Global.getGateId()

    var flag = false

    /**
     * @brief see "Übung 8-133"
     */
    abstract fun operator()

    override fun hashCode(): Int {
        return this.id
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Gate) return false
        return this.id == other.id
    }
}

open class TernaryGate(
    val in1: Wire,
    val in2: Wire,
    val in3: Wire?,
    val out: Wire
): Gate() {
    init {
        this.in1.connectedGates.add(this)
        this.in2.connectedGates.add(this)
        this.in3?.connectedGates?.add(this)
    }

    override fun operator() {
        error("TernaryGate: shouldn't be called")
    }
}

open class UnaryGate(
    val in1: Wire,
    val out: Wire
): Gate() {
    init {
        this.in1.connectedGates.add(this)
    }

    override fun operator() {
        error("UnaryGate: shouldn't be called")
    }
}
