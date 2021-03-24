package u04lab.code

import Lists._
import u04lab.code.Lists.List._ // import custom List type (not the one in Scala stdlib)

trait Student {
  def name: String
  def year: Int
  def enrolling(courses: Course*): Unit // the student participates to a Course
  def courses: List[String] // names of course the student participates to
  def hasTeacher(teacher: String): Boolean // is the student participating to a course of this teacher?
}

trait Course {
  def name: String
  def teacher: String
}

object Student {

  private case class StudentImpl(override val name: String, override val year: Int) extends Student {

    private var studentCourses: List[Course] = Nil[Course]()

    override def enrolling(courses: Course*): Unit = for(course <- courses) studentCourses = append(Cons(course, Nil()), studentCourses)

    override def courses: List[String] = map(studentCourses)(c => c.name)

    override def hasTeacher(teacher: String): Boolean = contains(map(studentCourses)(_.teacher), teacher)

  }

  def apply(name: String, year: Int = 2017): Student = StudentImpl(name, year)

}

object Course {

  private case class CourseImpl(override val name: String, override val teacher: String) extends Course

  def apply(name: String, teacher: String): Course = CourseImpl(name, teacher)

}
