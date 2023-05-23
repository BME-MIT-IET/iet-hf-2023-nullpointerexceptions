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
    fun testAcceptEntityToStorage(){
        val entity = Entity()
        assertEquals(Unit, storage.accept(entity))
    }

    @Test
    fun testAcceptVirologistToStorage(){
        val virologist = Virologist()
        storage.accept(virologist)
        assertEquals(1, storage.agentUsableList.size)
        assertEquals(virologist, storage.agentUsableList[0])
        assertEquals(storage, virologist.field)
    }

    @Test
    fun testSetPacketToStorage(){
        val packet = mock(Packet::class.java)
        storage.packet = packet
        assertEquals(packet, storage.packet)
    }

    @Test
    fun testToString(){
        assertEquals("Storage", storage.toString())
    }
}