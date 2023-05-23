package whut.material

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NucleotideTest {
    private lateinit var nucleotide: Nucleotide

    @BeforeEach
    fun init(){
        nucleotide = Nucleotide()
    }

    @Test
    fun testIsSame(){
        val nucleotide2 = Nucleotide()
        assertTrue(nucleotide.sameAs(nucleotide2))
    }

    @Test
    fun testValue(){
        assertTrue(nucleotide.amount <= 50)
        nucleotide.amount = 10
        assertEquals(10, nucleotide.amount)
    }

    @Test
    fun testCheck(){
        assertTrue(nucleotide.check("Nucleotide"))
        assertFalse(nucleotide.check("AminoAcid"))
    }

    @Test
    fun testToString(){
        assertEquals("Nucleotide", nucleotide.toString())
        assertNotEquals("AminoAcid", nucleotide.toString())
    }

    @Test
    fun testGetType(){
        assertEquals("Nucleotide", nucleotide.type)
        assertNotEquals("AminoAcid", nucleotide.type)
    }
}