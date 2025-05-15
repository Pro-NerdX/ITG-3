package org.example.utils

/**
 * period == 2 causes the clock to change every tick
 */
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
