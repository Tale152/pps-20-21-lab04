package u04lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import u04lab.code.Lists.List.Nil

class SameTeacherTest {

  @Test
  def testSameTeacher(): Unit ={

    val listFactory = new ListFactoryImpl()

    val listSameTeacher = listFactory.fromVariadicArgs(Course("PPS","Viroli"), Course("OOP", "Viroli"))
    val listDifferentTeachers = listFactory.fromVariadicArgs(Course("PPS","Viroli"), Course("PCD","Ricci"))
    val listEmpty = Nil[Course]()

    assertEquals(Some("Viroli"), SameTeacher.unapply(listSameTeacher))
    assertEquals(None, SameTeacher.unapply(listDifferentTeachers))
    assertEquals(None, SameTeacher.unapply(listEmpty))

  }

}
