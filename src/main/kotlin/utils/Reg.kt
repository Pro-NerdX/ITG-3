package org.example.utils

class Reg(
    var content: Boolean
) {
    val id: Int = Global.getRegId()

    override fun equals(other: Any?): Boolean {
        if (other !is Reg) return false
        return this.id == other.id
    }
}
