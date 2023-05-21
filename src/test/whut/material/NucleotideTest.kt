package whut.material

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
        assertEquals(true, nucleotide.isSame(nucleotide2))
    }

    @Test
    fun testValue(){
        assertTrue(nucleotide.value <= 50)
        nucleotide.value = 10
        assertEquals(10, nucleotide.value)
    }

    @Test
    fun testCheck(){
        assertEquals(true, nucleotide.check("nukleotid"))
    }

    @Test
    fun testToString(){
        assertEquals("nukleotid", nucleotide.toString())
    }

    @Test
    fun testGetType(){
        assertEquals("Nukleotid", nucleotide.type)
    }
}