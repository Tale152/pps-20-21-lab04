package u04lab.code

import u04lab.code.Lists.List
import u04lab.code.Lists.List.{Cons, filter, length, map}

object SameTeacher {

  def unapply(courses: List[Course]): Option[String] = {
    val teachers = map[Course, String](courses)(_.teacher)
    teachers match {
      case Cons(head,_) if length(filter[String](teachers)(_!=head)) == 0  => Some(head)
      case _ => None
    }
  }

}
