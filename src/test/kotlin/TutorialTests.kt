import org.example.gates.DLatch
import org.example.utils.Wire
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class TutorialTests {
    @Test
    fun dLatchTest() {
        val d = Wire(false, mutableListOf())
        val e = Wire(false, mutableListOf())
        val q = Wire(false, mutableListOf())
        val notQ = Wire(false, mutableListOf())
        val dLatch = DLatch(d, e, q, notQ)

        q.setVoltage(true)
        d.setVoltage(true)
        e.setVoltage(true)
        assert(dLatch.qWire.getVoltage())

        e.setVoltage(false)
        assert(dLatch.qWire.getVoltage())

        d.setVoltage(false)
        e.setVoltage(true)
        assertFalse(dLatch.qWire.getVoltage())
    }
}
