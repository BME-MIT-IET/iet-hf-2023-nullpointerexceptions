package whut.field

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import whut.genetic_code.GeneticCode

class LabTest {
    private lateinit var lab: Lab

    @BeforeEach
    fun init(){
        lab = Lab()
    }

    @Test
    fun setGeneticCodeToLab(){
        val geneticCode = mock(GeneticCode::class.java)
        lab.setGeneticCode(geneticCode)
        assertEquals(geneticCode, lab.codeHere())
    }

    @Test
    fun testToString(){
        assertEquals("lab", lab.toString())
    }
}