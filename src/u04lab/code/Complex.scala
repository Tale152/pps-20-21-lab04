package u04lab.code

trait Complex {
  def re: Double
  def im: Double
  def +(c: Complex): Complex
  def *(c: Complex): Complex
}

object Complex {

  private case class ComplexImpl(override val re: Double, override val im: Double) extends Complex {

    override def +(c: Complex): Complex = ComplexImpl(this.re + c.re, this.im + c.im)

    override def *(c: Complex): Complex = ComplexImpl(this.re * c.re - this.im * c.im, this.re * c.im + this.im * c.re)

  }

  def apply(re:Double, im:Double):Complex = ComplexImpl(re, im)

}
