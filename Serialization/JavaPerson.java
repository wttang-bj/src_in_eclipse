package Serialization;

import java.io.Serializable;

public class JavaPerson extends BasePerson implements Serializable{

  /**
   *  either given or not is OK. Compiler will give one automatically, if not
   *  It's used for compatible usage: JVM will compare whether the ID equals.
   *  If not, throw InvalidCastException
   */
  private static final long serialVersionUID = 1L;

  public JavaPerson() {
    super();
  }
  
  public JavaPerson(String name, int age, String sex) {
    super(name, age, sex);
  }
}
