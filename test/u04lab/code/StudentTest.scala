package u04lab.code

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue, assertFalse}
import org.junit.jupiter.api.Test
import u04lab.code.Lists.List._

class StudentTest {

  val cPPS: Course = Course("PPS","Viroli")
  val cPCD: Course = Course("PCD","Ricci")
  val cSDR: Course = Course("SDR","D'Angelo")
  val sMario: Student = Student("mario",2015)
  val sGino: Student = Student("gino",2016)
  val sRino: Student = Student("rino")
  sMario.enrolling(cPPS, cPCD)
  sGino.enrolling(cPPS)
  sRino.enrolling(cPPS, cPCD, cSDR)

  @Test
  def testCourses(): Unit ={
    assertEquals(Nil(), Student("dummy").courses)
    assertEquals(Cons("PCD",Cons("PPS",Nil())), sMario.courses)
    assertEquals(Cons("PPS",Nil()), sGino.courses)
    assertEquals(Cons("SDR",Cons("PCD",Cons("PPS",Nil()))), sRino.courses)
  }

  @Test
  def testHasTeacher(): Unit ={
    assertTrue(sGino.hasTeacher("Viroli"))
    assertFalse(sGino.hasTeacher("Ricci"))
    assertFalse(Student("dummy").hasTeacher("D'Angelo"))
  }

}
