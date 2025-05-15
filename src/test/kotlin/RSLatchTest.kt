import org.example.gates.RSLatch
import org.example.utils.Global
import org.example.utils.Wire
import kotlin.random.Random
import kotlin.test.Test

class RSLatchTest {
    val rWire = Wire(false, mutableListOf())
    val sWire = Wire(false, mutableListOf())
    val qWire = Wire(false, mutableListOf())
    val notQWire = Wire(false, mutableListOf())

    val rsLatch = RSLatch(rWire, sWire, qWire, notQWire)

    @Test
    fun testForThousandTicks() {
        var q = Random.nextBoolean()
        Global.resetFlags()
        qWire.setVoltage(q)

        var oldQ = q
        for (i: Int in 0..999) {
            val r = Random.nextBoolean()
            val s = Random.nextBoolean()

            Global.resetFlags()
            rWire.setVoltage(r)
            Global.resetFlags()
            sWire.setVoltage(s)

            q = qWire.getVoltage()
            val notQ = notQWire.getVoltage()
            val actual = Pair(q, notQ)

            if (s && !r) println("s ^ !r := q was $q (expected: true)")
            else if (!s && r) println("!s ^ r := q was $q (expected: false)")
            else if (!r && !s) println("!r ^ !s := q was $q (expected: $oldQ)")
            else println("s ^ r")

            assert(
                if (s && !r) q
                else if (!s && r) !q
                else if (!r && !s) q == oldQ
                else true
            )

            oldQ = qWire.getVoltage()
        }
    }
}
