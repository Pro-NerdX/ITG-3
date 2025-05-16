package org.example.utils

import org.example.gates.Gate

class Wire(
    private var voltage: Boolean,
    val connectedGates: MutableList<Gate>
) {
    val id: Int = Global.getWireId()

    fun setVoltage(volt: Boolean) {
        this.voltage = volt
        this.connectedGates.forEach {
            it.operator()
        }
        Global.resetFlags()
    }

    fun getVoltage(): Boolean {
        return this.voltage
    }

    override fun hashCode(): Int {
        return this.id
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Wire) return false
        return this.id == other.id
    }
}
