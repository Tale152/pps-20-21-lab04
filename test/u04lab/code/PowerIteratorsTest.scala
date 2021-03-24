package u04lab.code

import Optionals._
import Lists._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._
import u04lab.code.Optionals.Option.{getOrElse, isEmpty}

class PowerIteratorsTest {

  val factory = new PowerIteratorsFactoryImpl()

  @Test
  def testIncremental() {

    val incremental = factory.incremental(5,_+2)
    assertEquals(Option.of(5), incremental.next())
    assertEquals(Option.of(7), incremental.next())

    val incrementalReversed = incremental.reversed()
    assertEquals(Option.of(7), incrementalReversed.next())
    assertEquals(Option.of(5), incrementalReversed.next())
    assertEquals(Option.empty, incrementalReversed.next())
    assertEquals(List.Cons(7, List.Cons(5, List.Nil())), incrementalReversed.allSoFar())

    assertEquals(Option.of(9), incremental.next())
    assertEquals(Option.of(11), incremental.next())
    assertEquals(List.Cons(5, List.Cons(7, List.Cons(9, List.Cons(11,List.Nil())))), incremental.allSoFar())
    for (_ <- 0 until 10) {
      incremental.next()
    }
    assertEquals(Option.of(33), incremental.next())

  }

  @Test
  def testFromList(): Unit ={

    val fromList = factory.fromList(List.Cons(0, List.Cons(1, List.Cons(2, List.Nil()))))
    assertEquals(Option.of(0), fromList.next())
    assertEquals(Option.of(1), fromList.next())
    assertEquals(Option.of(2), fromList.next())
    assertEquals(Option.empty, fromList.next())
    assertEquals(List.Cons(0, List.Cons(1, List.Cons(2, List.Nil()))), fromList.allSoFar())

    val fromListReversed = fromList.reversed()
    assertEquals(Option.of(2), fromListReversed.next())
    assertEquals(Option.of(1), fromListReversed.next())
    assertEquals(Option.of(0), fromListReversed.next())
    assertEquals(Option.empty, fromListReversed.next())
    assertEquals(List.Cons(2, List.Cons(1, List.Cons(0, List.Nil()))), fromListReversed.allSoFar())

  }

  @Test
  def testRandomBooleans(): Unit ={

    val fromBoolean = factory.randomBooleans(2)
    val firstBoolean =  getOrElse(fromBoolean.next(), Option.empty)
    assertFalse(firstBoolean.isInstanceOf[Option.None[Boolean]])
    val secondBoolean =  getOrElse(fromBoolean.next(), Option.empty)
    assertFalse(secondBoolean.isInstanceOf[Option.None[Boolean]])
    assertTrue(isEmpty(fromBoolean.next()))
    assertEquals(List.Cons(firstBoolean, List.Cons(secondBoolean, List.Nil())), fromBoolean.allSoFar())

    val fromBooleanReversed = fromBoolean.reversed()
    assertEquals(Option.of(secondBoolean), fromBooleanReversed.next())
    assertEquals(Option.of(firstBoolean), fromBooleanReversed.next())
    assertTrue(isEmpty(fromBooleanReversed.next()))
    assertEquals(List.Cons(secondBoolean, List.Cons(firstBoolean, List.Nil())), fromBooleanReversed.allSoFar())

  }

}