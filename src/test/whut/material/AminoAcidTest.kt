package whut.material

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AminoAcidTest {
    private lateinit var aminoAcid: Aminosav

    @BeforeEach
    fun init(){
        aminoAcid = Aminosav()
    }

    @Test
    fun testCheck(){
        Assertions.assertEquals(true, aminoAcid.check("aminosav"))
    }

    @Test
    fun testToString(){
        Assertions.assertEquals("aminosav", aminoAcid.toString())
    }

    @Test
    fun testGetType(){
        Assertions.assertEquals("Aminosav", aminoAcid.type)
    }
}