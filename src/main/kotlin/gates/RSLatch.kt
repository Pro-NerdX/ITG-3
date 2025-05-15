package org.example.gates

import org.example.utils.Global
import org.example.utils.Wire

class RSLatch(
    val r: Wire,
    val s: Wire,
    val q: Wire,
    val notQ: Wire
): Gate() {
    val sSideNAND = NAND(s, q, notQ)
    val rSideNAND = NAND(r, notQ, q)

    init {
        Global.allActiveGates.add(this)

        s.connectedGates.add(this)
        r.connectedGates.add(this)
    }

    override fun operator() {
        if (flag) return
        this.flag = true

        q.setVoltage(rSideNAND.out.getVoltage())
        notQ.setVoltage(sSideNAND.out.getVoltage())
    }
}
