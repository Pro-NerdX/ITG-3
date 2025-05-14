package org.example

import org.example.gates.AND
import org.example.gates.OR
import org.example.gates.XOR
import org.example.utils.Wire

/**
 * @brief here goes the implementation of the full-adder
 */
fun main() {
    /*
    Inputs in the following order:
    a := 1st summand
    b := 2nd summand
    cIn := carry bit

    Outputs:
    s := sum
    cOut := carry bit
     */
    val aWire = Wire(false, mutableListOf())
    val bWire = Wire(false, mutableListOf())
    val cInWire = Wire(false, mutableListOf())

    // put all of them in ternary XOR-gate for output s
    val sWire = Wire(false, mutableListOf())
    val sumXOR = XOR(aWire, bWire, cInWire, sWire)
    aWire.connectedGates.add(sumXOR)
    bWire.connectedGates.add(sumXOR)
    cInWire.connectedGates.add(sumXOR)

    // (a ^ b) v (a ^ cIn) v (b ^ cIn)
    // AND-gates
    val firstAndOutWire = Wire(false, mutableListOf())
    val firstAND = AND(aWire, bWire, null, firstAndOutWire)
    aWire.connectedGates.add(firstAND)
    bWire.connectedGates.add(firstAND)

    val secondAndOutWire = Wire(false, mutableListOf())
    val secondAND = AND(aWire, cInWire, null, secondAndOutWire)
    aWire.connectedGates.add(secondAND)
    cInWire.connectedGates.add(secondAND)

    val thirdAndOutWire = Wire(false, mutableListOf())
    val thirdAND = AND(bWire, cInWire, null, thirdAndOutWire)
    bWire.connectedGates.add(thirdAND)
    cInWire.connectedGates.add(thirdAND)

    // OR-gates
    val cOutWire = Wire(false, mutableListOf())
    val carryOutOR = OR(firstAndOutWire, secondAndOutWire, thirdAndOutWire, cOutWire)
    firstAndOutWire.connectedGates.add(carryOutOR)
    secondAndOutWire.connectedGates.add(carryOutOR)
    thirdAndOutWire.connectedGates.add(carryOutOR)

    // start simulation

    // outputs
    println(" a | b | c_in || sum | c_out ")
    println("---|---|------||-----|-------")
    val sendHelp = listOf(false, true)
    for (aTemp: Boolean in sendHelp) {
        for (bTemp: Boolean in sendHelp) {
            for (cInTemp: Boolean in sendHelp) {
                aWire.setVoltage(aTemp)
                bWire.setVoltage(bTemp)
                cInWire.setVoltage(cInTemp)

                val s = sWire.getVoltage()
                val cOut = cOutWire.getVoltage()
                println(" ${boolToInt(aTemp)} | ${boolToInt(bTemp)} |   ${boolToInt(cInTemp)}  ||  ${boolToInt(s)}  |   ${boolToInt(cOut)}   ")
            }
        }
    }
}

fun boolToInt(bool: Boolean): Int {
    return if (bool) 1 else 0
}
