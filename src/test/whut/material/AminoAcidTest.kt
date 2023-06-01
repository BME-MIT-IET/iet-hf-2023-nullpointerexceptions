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
        Assertions.assertTrue(aminoAcid.check("AminoAcid"))
        Assertions.assertFalse(aminoAcid.check("Nucleotide"))
    }

    @Test
    fun testToString(){
        Assertions.assertEquals("AminoAcid", aminoAcid.toString())
        Assertions.assertNotEquals("Nucleotide", aminoAcid.toString())
    }

    @Test
    fun testGetType(){
        Assertions.assertEquals("AminoAcid", aminoAcid.type)
        Assertions.assertNotEquals("Nucleotide", aminoAcid.type)
    }
}