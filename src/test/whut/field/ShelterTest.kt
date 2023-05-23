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
    fun testAddItemToShelter(){
        shelter.addItem(Item())
        assertEquals(1, shelter.items.size)
    }

    @Test
    fun testRemoveItemFromShelter(){
        val item = Item()
        shelter.addItem(item)
        assertEquals(item, shelter.items[0])
        shelter.removeItem(item)
        assertEquals(0, shelter.items.size)
    }

    @Test
    fun testGetContainedItemFromShelter(){
        val axe = Axe()
        shelter.addItem(axe)
        assertEquals(axe, shelter.getItem("Axe"))
    }

    @Test
    fun testGetNotContainedItemFromShelter(){
        val cloak = Cloak()
        shelter.addItem(Axe())
        assertNotEquals(cloak, shelter.getItem("cloak"))
        assertEquals(null, shelter.getItem("cloak"))
    }

    @Test
    fun testToString(){
        assertEquals("Shelter", shelter.toString())
    }
}