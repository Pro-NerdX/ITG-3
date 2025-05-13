package org.example.utils

class Clock(
    val period: Int
) {
    /**
     * @brief simulates a clock
     */
    fun getClk(t: Int): Boolean {
        return (t / (period / 2)) % 2 != 0
    }
}
