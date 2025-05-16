package org.example

import org.example.gates.FullAdder
import org.example.utils.Global
import org.example.utils.Wire

/**
 * @brief here goes the implementation of the full-adder
 */
fun main() {
    // Wires
    val aWire = Wire(false, mutableListOf())
    val bWire = Wire(false, mutableListOf())
    val cInWire = Wire(false, mutableListOf())
    val sWire = Wire(false, mutableListOf())
    val cOutWire = Wire(false, mutableListOf())

    // Full-adder
    val fullAdder = FullAdder(aWire, bWire, cInWire, sWire, cOutWire)
    Global.allActiveGates.add(fullAdder)

    // outputs
    println(" a | b | c_in || sum | c_out ")
    println("---|---|------||-----|-------")
    val sendHelp = listOf(false, true)
    for (aTemp: Boolean in sendHelp) {
        for (bTemp: Boolean in sendHelp) {
            for (cInTemp: Boolean in sendHelp) {
                fullAdder.aWire.setVoltage(aTemp)
                fullAdder.bWire.setVoltage(bTemp)
                fullAdder.cInWire.setVoltage(cInTemp)

                val s = fullAdder.sWire.getVoltage()
                val cOut = fullAdder.cOutWire.getVoltage()
                println(" ${boolToInt(aTemp)} | ${boolToInt(bTemp)} |   ${boolToInt(cInTemp)}  ||  ${boolToInt(s)}  |   ${boolToInt(cOut)}   ")

                Global.resetFlags()
            }
        }
    }
}

fun boolToInt(bool: Boolean): Int {
    return if (bool) 1 else 0
}
