package whut.material

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AminoAcidTest {
    private lateinit var aminoAcid: AminoAcid

    @BeforeEach
    fun init(){
        aminoAcid = AminoAcid()
    }

    @Test
    fun testCheck(){
        Assertions.assertEquals(true, aminoAcid.check("AminoAcid"))
    }

    @Test
    fun testToString(){
        Assertions.assertEquals("AminoAcid", aminoAcid.toString())
    }

    @Test
    fun testGetType(){
        Assertions.assertEquals("AminoAcid", aminoAcid.type)
    }
}