package u04lab.code

import u04lab.code.Lists.List
import u04lab.code.Lists.List._
import u04lab.code.Optionals._
import u04lab.code.Streams.Stream
import u04lab.code.Streams.Stream.take

import scala.util.Random

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {

  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A]): PowerIterator[A]
  def randomBooleans(size: Int): PowerIterator[Boolean]

}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  private case class PowerIteratorImpl[A](var stream: Stream[A] = Stream.empty()) extends PowerIterator[A]{

    private var soFarList:List[A] = Nil()

    override def next(): Optionals.Option[A] = stream match {
      case Stream.Cons(h, t) =>
        soFarList = append(soFarList, List.Cons(h(), Nil()))
        stream = t()
        Option.Some(h())
      case _ => Option.empty
    }

    override def allSoFar(): Lists.List[A] = soFarList

    override def reversed(): PowerIterator[A] = fromList(reverse(soFarList))

  }

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] =
    PowerIteratorImpl(Stream.iterate(start)(successive))

  private def listToStream[A](list: List[A]): Stream[A] = list match {
    case Cons(h,t) => Stream.Cons(() => h, () => listToStream(t))
    case _ => Stream.empty()
  }

  override def fromList[A](list: List[A]): PowerIterator[A] =
    PowerIteratorImpl(listToStream(list))

  override def randomBooleans(size: Int): PowerIterator[Boolean] =
    PowerIteratorImpl(take(Stream.generate(Random.nextBoolean()))(size))

}
