package org.example.utils

import org.example.gates.Gate

class Wire(
    private var voltage: Boolean,
    val connectedGates: MutableList<Gate>
) {
    val id: Int = Global.getWireId()

    fun setVoltage(volt: Boolean) {
        if (this.voltage == volt) return
        this.voltage = volt
        this.connectedGates.forEach {
            it.operator()
        }
    }

    fun getVoltage(): Boolean {
        return this.voltage
    }
}
