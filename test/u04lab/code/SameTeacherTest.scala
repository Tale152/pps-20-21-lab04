package u04lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import u04lab.code.Lists.List.{Cons, Nil}

class SameTeacherTest {

  @Test
  def testSameTeacher(): Unit ={

    val listSameTeacher = Cons(Course("PPS","Viroli"), Cons(Course("OOP", "Viroli"), Nil()))
    val listDifferentTeachers = Cons(Course("PPS","Viroli"), Cons(Course("PCD","Ricci"), Nil()))
    val listEmpty = Nil[Course]()

    assertEquals(Some("Viroli"), SameTeacher.unapply(listSameTeacher))
    assertEquals(None, SameTeacher.unapply(listDifferentTeachers))
    assertEquals(None, SameTeacher.unapply(listEmpty))

  }

}
