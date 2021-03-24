package u04lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import u04lab.code.Lists.List.{Cons, Nil}

class ListFactoryTest {

  @Test
  def testFromVariadicArgs(): Unit ={

    val factory = new ListFactoryImpl()
    assertEquals(Cons(0, Cons(1, Cons(2, Nil()))), factory.fromVariadicArgs(0,1,2))
    assertEquals(Nil(), factory.fromVariadicArgs())

  }

}
