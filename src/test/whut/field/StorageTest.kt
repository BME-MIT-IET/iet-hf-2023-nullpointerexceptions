package whut.field

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.Mockito.mock
import whut.material.Packet
import whut.player.Entity
import whut.player.Game
import whut.player.Virologist
import whut.ui.control.MyRunnable

class StorageTest{
    private lateinit var storage: Storage

    companion object {
        private lateinit var myRunnableMock: MockedStatic<MyRunnable>

        @BeforeAll
        @JvmStatic
        fun setup() {
            val game = mock(Game::class.java)
            Mockito.doNothing().`when`(game).applyBearEffectToAll()

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
        assertEquals(Unit, storage.accept(entity))
    }

    @Test
    fun acceptVirologistToStorage(){
        val virologist = Virologist()
        storage.accept(virologist)
        assertEquals(1, storage.virologists.size)
        assertEquals(virologist, storage.virologists[0])
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