package u04lab.code

import u04lab.code.Lists.List
import u04lab.code.Lists.List._
import u04lab.code.Optionals.Option._
import u04lab.code.Optionals._

import scala.util.Random

class PowerIteratorsFactoryByList extends PowerIteratorsFactory {

  private case class PowerIteratorImpl[A](getNext: List[A] => Option[A]) extends PowerIterator[A]{

    private var soFarList: List[A] = Nil()

    override def next(): Option[A] = {
      getNext(soFarList) match {
        case Some(elem) => soFarList = append(soFarList, Cons(elem, Nil())); Some(elem)
        case None() => None()
      }
    }

    override def allSoFar(): List[A] = soFarList

    override def reversed(): PowerIterator[A] = fromList(reverse(soFarList))

  }

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] = PowerIteratorImpl[Int](
    currList => drop(currList, length(currList)-1) match {
      case Cons(head, _) => Some(successive(head))
      case _ => Some(start)
    }
  )

  override def fromList[A](list: List[A]): PowerIterator[A] = PowerIteratorImpl[A](
    currList => drop(list, length(currList)) match {
      case Cons(head, _) => Some[A](head)
      case _ => None[A]()
    }
  )

  override def randomBooleans(size: Int): PowerIterator[Boolean] = PowerIteratorImpl[Boolean](
    currList => if (length(currList) < size) {
      Some(Random.nextBoolean())
    } else {
      None[Boolean]()
    }
  )

}
