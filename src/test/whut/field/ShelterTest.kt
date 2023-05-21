package whut.field

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import whut.item.Axe
import whut.item.Cloak
import whut.item.Item

class ShelterTest {
    private lateinit var shelter: Shelter

    @BeforeEach
    fun init(){
        shelter = Shelter()
    }

    @Test
    fun addItemToShelter(){
        shelter.addItem(Item())
        assertEquals(1, shelter.items.size)
    }

    @Test
    fun removeItemFromShelter(){
        val item = Item()
        shelter.addItem(item)
        assertEquals(item, shelter.items[0])
        shelter.removeItem(item)
        assertEquals(0, shelter.items.size)
    }

    @Test
    fun getContainedItemFromShelter(){
        val axe = Axe()
        shelter.addItem(axe)
        assertEquals(axe, shelter.getItem("axe"))
    }

    @Test
    fun getNotContainedItemFromShelter(){
        val cloak = Cloak()
        shelter.addItem(Axe())
        assertNotEquals(cloak, shelter.getItem("cloak"))
        assertEquals(null, shelter.getItem("cloak"))
    }

    @Test
    fun testToString(){
        assertEquals("shelter", shelter.toString())
    }
}