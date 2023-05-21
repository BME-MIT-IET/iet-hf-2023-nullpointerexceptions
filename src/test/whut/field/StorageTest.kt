package whut.field

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.Mockito.mock
import whut.material.Packet
import whut.player.Entity
import whut.player.Game
import whut.player.Virologus
import whut.ui.control.MyRunnable

class StorageTest{
    private lateinit var storage: Storage

    companion object {
        private lateinit var myRunnableMock: MockedStatic<MyRunnable>

        @BeforeAll
        @JvmStatic
        fun setup() {
            val game = mock(Game::class.java)
            Mockito.doNothing().`when`(game).bearAll()

            myRunnableMock = Mockito.mockStatic(MyRunnable::class.java)
            Mockito.`when`(MyRunnable.getGame()).thenReturn(game)
        }

        @AfterAll
        @JvmStatic
        fun cleanup(){
            myRunnableMock.close()
        }
    }

    @BeforeEach
    fun init(){
        storage = Storage()
    }

    @Test
    fun acceptEntityToStorage(){
        val entity = Entity()
        val exception = assertThrows<ClassCastException> {
            storage.accept(entity)
        }
        assertEquals("whut.player.Entity cannot be cast to whut.player.Virologus", exception.message)
    }

    @Test
    fun acceptVirologistToStorage(){
        val virologist = Virologus()
        storage.accept(virologist)
        assertEquals(1, storage.virologusok.size)
        assertEquals(virologist, storage.virologusok[0])
        assertEquals(storage, virologist.field)
    }

    @Test
    fun setPacketToStorage(){
        val packet = mock(Packet::class.java)
        storage.packet = packet
        assertEquals(packet, storage.packet)
    }

    @Test
    fun testToString(){
        assertEquals("storage", storage.toString())
    }
}