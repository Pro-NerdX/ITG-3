package org.example.gates

import org.example.utils.Global

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
