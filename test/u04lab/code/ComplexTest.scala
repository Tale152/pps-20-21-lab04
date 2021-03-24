package u04lab.code

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComplexTest {

  val a = Array(Complex(10,20), Complex(1,1), Complex(7,0))

  def complexValuesAssertion(complex: Complex, re: Double, im: Double): Unit ={
    assertEquals(re, complex.re)
    assertEquals(im, complex.im)
  }

  @Test
  def testSum(): Unit ={
    complexValuesAssertion(a(0) + a(1) + a(2), 18, 21)
  }

  @Test
  def testProduct(): Unit ={
    complexValuesAssertion(a(0)*a(1), -10, 30)
  }

  @Test
  def testEquals(): Unit ={
    assertEquals(Complex(10,20), a(0))
  }

  @Test
  def testStringFormatting(): Unit ={
    assertEquals("ComplexImpl(10.0,20.0)", a(0).toString)
  }

}
