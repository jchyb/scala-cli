import $ivy.`com.novocode:junit-interface:0.11`
import org.junit.Test

class MyTests {

  @Test
  def foo(): Unit = {
    assert(2 + 2 == 4)
  }
}
