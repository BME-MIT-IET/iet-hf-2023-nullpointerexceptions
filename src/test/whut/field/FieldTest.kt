package whut.field

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import whut.genetic_code.ForgetCode
import whut.item.Item
import whut.player.Entity
import whut.player.Virologist

class FieldTest {
    private lateinit var field: Field

    @BeforeEach
    fun init(){
        field = Field()
    }

    @Test
    fun testConstructor(){
        assertEquals(0, field.neighborhood.size)
        assertEquals(0, field.agentUsableList.size)
    }

    @Test
    fun testSetNeighborToField() {
        val neighborField = Field()
        field.setNeighbor(neighborField)
        assertEquals(field.neighborhood.size, 1)
        assertEquals(neighborField.neighborhood.size, 1)
    }

    @Test
    fun testAddItemToField(){
        field.addItem(Item())
        assertEquals(0, field.items.size)
        assertEquals(null, field.getItem(""))
    }

    @Test
    fun testRemoveItemFromField(){
        val item = Item()
        field.addItem(item)
        field.removeItem(item)
        assertEquals(0, field.items.size)
        assertEquals(null, field.getItem(""))
    }

    @Test
    fun testSetGeneticCodeToField(){
        field.setGeneticCode(ForgetCode())
        assertEquals(null, field.getGeneticCode())
    }

    @Test
    fun testAcceptVirologistToField(){
        val virologist = Virologist()
        field.accept(virologist)
        assertEquals(1, field.agentUsableList.size)
        assertEquals(virologist, field.agentUsableList[0])
        assertEquals(virologist.field, field)
        assertEquals(true, field.agentUsableList.size == field.agentUsableList.size)
    }

    @Test
    fun testAcceptEntityToField(){
        val entity = Entity()
        assertEquals(Unit, field.accept(entity))
    }

    @Test
    fun testTemoveVirologistFromField(){
        val virologist = Virologist()
        field.accept(virologist)
        assertEquals(1, field.agentUsableList.size)
        field.remove(virologist)
        assertEquals(0, field.agentUsableList.size)
    }

    @Test
    fun testRemoveEntityFromField(){
        assertEquals(0, field.agentUsableList.size)
        field.remove(Entity())
        assertEquals(0, field.agentUsableList.size)
    }

    @Test
    fun testGetPacketFromField(){
        assertEquals(null, field.packet)
    }

    @Test
    fun testToString(){
        assertEquals("Field", field.toString())
    }
}