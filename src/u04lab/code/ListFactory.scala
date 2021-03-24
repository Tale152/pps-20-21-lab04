package u04lab.code

import u04lab.code.Lists.List
import u04lab.code.Lists.List._

trait ListFactory {

  def fromVariadicArgs[A](elements: A*): List[A]

}

class ListFactoryImpl extends ListFactory{

  override def fromVariadicArgs[A](elements: A*): List[A] = {
    var head: List[A] = Nil[A]()
    for(element <- elements) head = append(head, Cons(element, Nil()))
    head
  }

}
