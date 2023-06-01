package whut.player

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockedStatic
import org.mockito.Mockito.*
import whut.agent.Protection
import whut.agent.Stun
import whut.genetic_code.ChoreaCode
import whut.genetic_code.ForgetCode
import whut.genetic_code.ProtectionCode
import whut.genetic_code.StunCode
import whut.material.AminoAcid
import whut.material.Nucleotide
import whut.material.Packet
import whut.ui.control.MyRunnable

class AgentUsableTest {
    private lateinit var agentUsable: AgentUsable
    private var packet: Packet = Packet()

    companion object {
        private lateinit var myRunnableMock: MockedStatic<MyRunnable>

        @BeforeAll
        @JvmStatic
        fun setup() {
            val game = mock(Game::class.java)
            doNothing().`when`(game).applyBearEffectToAll()
            `when`(game.isRunning).thenReturn(true)

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
        agentUsable = AgentUsable()

        packet.materials.add(AminoAcid().apply { amount = 50 })
        packet.materials.add(Nucleotide().apply { amount = 50 })
    }

    @Test
    fun testGotAttacked(){
       val virologist = Virologist()
       virologist.addAgent(Stun())
       assertEquals(1, virologist.getAgents().size)
       agentUsable.gotAttacked(virologist.getAgent("Stun"), virologist)
       assertEquals(1, agentUsable.getAppliedAgents().size)
       assertEquals(0, virologist.agents.size)
       assertEquals("Stun", agentUsable.appliedAgents[0].toString())
    }

    @Test
    fun testGotAttackedWithProtection(){
        val virologist = Virologist()
        virologist.addAgent(Stun())
        agentUsable.applyAgent(Protection())
        agentUsable.gotAttacked(virologist.getAgent("Stun"), virologist)
        assertEquals(1, agentUsable.appliedAgents.size)
        assertEquals(0, agentUsable.agents.size)
    }

    @Test
    fun testForgetGeneticCodes(){
        agentUsable.learnGeneticCode(ForgetCode())
        agentUsable.learnGeneticCode(ProtectionCode())
        assertEquals(2, agentUsable.getGeneticCodes().size)
        agentUsable.forgetAllGeneticCodes()
        assertEquals(0, agentUsable.geneticCodes.size)
    }

    @Test
    fun testRemoveAppliedAgent(){
        agentUsable.applyAgent(Protection())
        agentUsable.applyAgent(Stun())
        assertEquals(2, agentUsable.appliedAgents.size)
        agentUsable.removeAppliedAgent(agentUsable.appliedAgents[0])
        assertEquals(1, agentUsable.appliedAgents.size)
    }

    @Test
    fun testIncreaseMaterial(){
        agentUsable.increaseMaterial(packet, packet.materials[0])
        agentUsable.increaseMaterial(packet, packet.materials[1])

        assertEquals(50, agentUsable.packet.materials[0].amount)
        assertEquals(50, agentUsable.packet.materials[1].amount)
    }

    @Test
    fun testCreateAgentStun(){
        assertEquals(0, agentUsable.agents.size)

        agentUsable.increaseMaterial(packet, packet.materials[0])
        agentUsable.increaseMaterial(packet, packet.materials[1])
        agentUsable.learnGeneticCode(StunCode())
        agentUsable.createAgent("StunCode")

        assertEquals("Stun", agentUsable.agents[0].toString())
        assertEquals(1, agentUsable.geneticCodes.size)
    }

    @Test
    fun testCreateAgentChorea(){
        assertEquals(0, agentUsable.agents.size)

        agentUsable.increaseMaterial(packet, packet.materials[0])
        agentUsable.increaseMaterial(packet, packet.materials[1])
        agentUsable.learnGeneticCode(ChoreaCode())
        agentUsable.createAgent("ChoreaCode")

        assertEquals("Chorea", agentUsable.agents[0].toString())
        assertEquals(1, agentUsable.geneticCodes.size)
    }

    @Test
    fun testCreateAgentForget(){
        assertEquals(0, agentUsable.agents.size)

        agentUsable.increaseMaterial(packet, packet.materials[0])
        agentUsable.increaseMaterial(packet, packet.materials[1])
        agentUsable.learnGeneticCode(ForgetCode())
        agentUsable.createAgent("ForgetCode")

        assertEquals("Forget", agentUsable.agents[0].toString())
        assertEquals(1, agentUsable.geneticCodes.size)
    }

    @Test
    fun testCreateAgentProtection(){
        assertEquals(0, agentUsable.agents.size)

        agentUsable.increaseMaterial(packet, packet.materials[0])
        agentUsable.increaseMaterial(packet, packet.materials[1])
        agentUsable.learnGeneticCode(ProtectionCode())
        agentUsable.createAgent("ProtectionCode")

        assertEquals("Protection", agentUsable.agents[0].toString())
        assertEquals(1, agentUsable.geneticCodes.size)
    }

    @Test
    fun testCreateAgentFailure(){
        assertEquals(0, agentUsable.agents.size)
        agentUsable.createAgent("StunCode")
        assertEquals(0, agentUsable.agents.size)
        assertEquals(0, agentUsable.geneticCodes.size)
    }

    @Test
    fun testUseAgent(){
        val virologist = Virologist()
        agentUsable.addAgent(Stun())

        agentUsable.useAgent(virologist, agentUsable.agents[0])

        assertEquals(0, agentUsable.agents.size)
        assertEquals(1, virologist.appliedAgents.size)
    }

    @Test
    fun testGetAgentNotFound(){
        assertNull(agentUsable.getAgent("Stun"))
    }

    @Test
    fun testDecreaseRoundTrue(){
        assertTrue(agentUsable.decreaseRound())
    }

    @Test
    fun testDecreaseRoundFalse(){
        val stun: Stun = mock(Stun::class.java)
        `when`(stun.startTurnEffect(agentUsable)).thenReturn(false)
        agentUsable.applyAgent(stun)
        assertFalse(agentUsable.decreaseRound())
    }
}