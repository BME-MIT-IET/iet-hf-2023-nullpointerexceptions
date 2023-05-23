package whut.player

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockedStatic
import org.mockito.Mockito.*
import whut.agent.BearDance
import whut.agent.Protection
import whut.agent.Stun
import whut.field.Field
import whut.field.Shelter
import whut.item.Axe
import whut.item.Item
import whut.material.AminoAcid
import whut.ui.control.MyRunnable

class VirologistTest {
    private lateinit var virologist: Virologist
    private lateinit var attacker: Virologist
    private lateinit var field: Field

    companion object {
        private lateinit var myRunnableMock: MockedStatic<MyRunnable>

        @BeforeAll
        @JvmStatic
        fun setup() {
            val game = mock(Game::class.java)
            doNothing().`when`(game).applyBearEffectToAll()

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
    fun init(){
        virologist = Virologist()
        attacker = Virologist()
        field = Shelter().apply {
            addVirologist(virologist)
            addVirologist(attacker)
        }
        virologist.field = field
        attacker.field = field
    }

    @Test
    fun testGetItem(){
        assertNull(virologist.getItem("Axe"))

        val axe = Axe()
        virologist.addItem(axe)
        assertEquals(axe, virologist.getItem("Axe"))
    }

    @Test
    fun testCanNotStealItemFromVirologist(){
        virologist.addItem(Item())

        virologist.stealItemAttempt(attacker, virologist.items[0])

        assertEquals(1, virologist.items.size)
        assertEquals(0, attacker.items.size)
    }

    @Test
    fun testCanStealItemFromVirologistButFull(){
        repeat(3){
            attacker.addItem(Item())
        }
        virologist.addItem(Item())
        virologist.applyAgent(Stun())

        virologist.stealItemAttempt(attacker, virologist.items[0])

        assertEquals(0, virologist.items.size)
        assertEquals(3, attacker.items.size)
    }

    @Test
    fun testCanStealItemFromVirologist(){
        virologist.addItem(Item())
        virologist.applyAgent(Stun())

        virologist.stealItemAttempt(attacker, virologist.items[0])

        assertEquals(0, virologist.items.size)
        assertEquals(1, attacker.items.size)
    }

    @Test
    fun testCanNotStealMaterialFromVirologist(){
        virologist.materialPacket.addMaterial(AminoAcid().apply { amount = 30 })

        virologist.stealMaterialAttempt(attacker, virologist.materialPacket.materials[0])

        assertEquals(30, virologist.materialPacket.materials[0].amount)
        assertEquals(0, attacker.materialPacket.materials.size)
    }

    @Test
    fun testCanStealMaterialFromVirologistButFull(){
        virologist.materialPacket.addMaterial(AminoAcid().apply { amount = 30 })
        virologist.applyAgent(Stun())
        attacker.materialPacket.addMaterial(AminoAcid().apply { amount = 50 })

        virologist.stealMaterialAttempt(attacker, virologist.materialPacket.materials[0])

        assertEquals(0, virologist.materialPacket.materials[0].amount)
        assertEquals(50, attacker.materialPacket.materials[0].amount)
    }

    @Test
    fun testCanStealMaterialFromVirologist(){
        virologist.materialPacket.addMaterial(AminoAcid().apply { amount = 30 })
        virologist.applyAgent(Stun())

        virologist.stealMaterialAttempt(attacker, virologist.materialPacket.materials[0])

        assertEquals(0, virologist.materialPacket.materials[0].amount)
        assertEquals(30, attacker.materialPacket.materials[0].amount)
    }

    @Test
    fun testPickupItem(){
        val item = Item()
        field.items.add(item)

        virologist.pickUpItem(item)

        assertEquals(item, virologist.items[0])
    }

    @Test
    fun testLeaveItem(){
        val item = Item()
        virologist.addItem(item)

        assertEquals(1, virologist.items.size)
        assertEquals(0, field.items.size)
        virologist.leaveItem(virologist.items[0])
        assertEquals(0, virologist.items.size)
        assertEquals(1, field.items.size)
    }

    @Test
    fun testChangeItem(){
        val oldItem = Item()
        val newItem = Item()

        virologist.addItem(oldItem)
        virologist.changeItem(virologist.items[0], newItem)
        assertEquals(newItem, virologist.items[0])
        assertEquals(1, virologist.items.size)
    }

    @Test
    fun testPickupItemButFull(){
        repeat(3){
            virologist.addItem(Axe())
        }
        val item = Item()
        virologist.pickUpItem(item)

        assertEquals(3, virologist.items.size)
        assertEquals(item, virologist.items[2])
    }

    @Test
    fun testDie(){
        virologist.die()
        assertEquals(1, field.agentUsableList.size)
        assertNull(field.agentUsableList.find { it == virologist })
    }

    @Test
    fun testSelfAttack(){
        virologist.gotAttacked(Protection(), virologist)

        assertEquals("Protection", virologist.appliedAgents[0].toString())
    }

    @Test
    fun testTrySelfKill(){
        assertFalse(virologist.kill(virologist))
    }

    @Test
    fun testKillWithoutAxe(){
        assertFalse(attacker.kill(virologist))
    }

    @Test
    fun testKillWithAxeThenBrokenAxe(){

        val thirdVirologist = Virologist().apply { field = this@VirologistTest.field }
        field.apply {
            addVirologist(thirdVirologist)
        }
        attacker.addItem(Axe())

        assertTrue(attacker.kill(virologist))
        assertEquals(2, field.agentUsableList.size)

        assertFalse(attacker.kill(thirdVirologist))
        assertEquals(2, field.agentUsableList.size)
    }

    @Test
    fun testIsBear(){
        assertFalse(virologist.isBear)
        virologist.applyAgent(BearDance())
        assertTrue(virologist.isBear)
    }
}



















