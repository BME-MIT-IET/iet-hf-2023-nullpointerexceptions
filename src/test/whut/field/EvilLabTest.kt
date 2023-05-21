package whut.field

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockedStatic
import org.mockito.Mockito.*
import whut.player.Game
import whut.player.Virologist
import whut.ui.control.MyRunnable

class EvilLabTest {
    private lateinit var evilLab: EvilLab

    companion object {
        private lateinit var myRunnableMock: MockedStatic<MyRunnable>

        @BeforeAll
        @JvmStatic
        fun setup() {
            val game = mock(Game::class.java)
            doNothing().`when`(game).bearAll()

            myRunnableMock = mockStatic(MyRunnable::class.java)
            `when`(MyRunnable.getGame()).thenReturn(game)
        }

        @AfterAll
        @JvmStatic
        fun cleanup(){
            myRunnableMock.close()
        }
    }

    @BeforeEach
    fun initTest(){
        evilLab = EvilLab()
    }

    @Test
    fun touchingEvilLab(){
        val virologist = Virologist()
        evilLab.touching(virologist)
        assertEquals(1, virologist.agensOnMe.size)
    }

    @Test
    fun testToString(){
        assertEquals("evillab", evilLab.toString())
    }
}