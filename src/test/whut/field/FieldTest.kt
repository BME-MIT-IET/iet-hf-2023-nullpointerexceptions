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
        assertEquals(0, field.neighbourhood.size)
        assertEquals(0, field.virologusok.size)
    }

    @Test
    fun setNeighborToField() {
        val neighborField = Field()
        field.setNeighbour(neighborField)
        assertEquals(field.neighbourhood.size, 1)
        assertEquals(neighborField.neighbourhood.size, 1)
    }

    @Test
    fun addItemToField(){
        field.addItem(Item())
        assertEquals(0, field.items.size)
        assertEquals(null, field.getItem(""))
    }

    @Test
    fun removeItemFromField(){
        val item = Item()
        field.addItem(item)
        field.removeItem(item)
        assertEquals(0, field.items.size)
        assertEquals(null, field.getItem(""))
    }

    @Test
    fun setGeneticCodeToField(){
        field.setGeneticCode(ForgetCode())
        assertEquals(null, field.codeHere())
    }

    @Test
    fun acceptVirologistToField(){
        val virologist = Virologist()
        field.accept(virologist)
        assertEquals(1, field.au.size)
        assertEquals(virologist, field.au[0])
        assertEquals(virologist.field, field)
        assertEquals(true, field.au.size == field.virologusok.size)
    }

    @Test
    fun acceptEntityToField(){
        val entity = Entity()
        assertEquals(Unit, field.accept(entity))
    }

    @Test
    fun removeVirologistFromField(){
        val virologist = Virologist()
        field.accept(virologist)
        assertEquals(1, field.au.size)
        field.remove(virologist)
        assertEquals(0, field.au.size)
    }

    @Test
    fun removeEntityFromField(){
        assertEquals(0, field.au.size)
        field.remove(Entity())
        assertEquals(0, field.au.size)
    }

    @Test
    fun getPacketFromField(){
        assertEquals(null, field.packet)
    }

    @Test
    fun testToString(){
        assertEquals("whut/field", field.toString())
    }
}