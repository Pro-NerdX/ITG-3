package org.example.utils

import org.example.gates.Gate

object Global {
    private var gateIdCounter: Int = 0
    private var wireIdCounter: Int = 0
    private var regIdCounter: Int = 0

    val falseWire = Wire(false, mutableListOf())
    val trueWire = Wire(true, mutableListOf())

    val allActiveGates: MutableList<Gate> = mutableListOf()

    fun getGateId(): Int {
        val sendHelp: Int = this.gateIdCounter
        this.gateIdCounter++
        return sendHelp
    }

    fun getWireId(): Int {
        val sendHelp: Int = this.wireIdCounter
        this.wireIdCounter++
        return sendHelp
    }

    fun getRegId(): Int {
        val sendHelp: Int = this.regIdCounter
        this.regIdCounter++
        return sendHelp
    }

    fun resetFlags() {
        this.allActiveGates.forEach {
            it.flag = false
        }
    }
}
