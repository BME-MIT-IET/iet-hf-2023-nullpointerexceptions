package whut.player

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import whut.field.Field

class EntityTest {
    private lateinit var entity: Entity

    @BeforeEach
    fun init(){
        entity = Entity()
    }

    @Test
    fun test(){
        val from = Field().apply { accept(entity) }
        val to = Field()

        assertEquals(from, entity.field)
        entity.move(to)
        assertEquals(to, entity.field)
    }
}