package NIO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Car car = (Car) new AllinOneHandler().bind(new Tesla());
    System.out.println((car instanceof Car) + ", " + (car instanceof Proxy));
    car.drive();
    car.beep();
    System.out.println();
    Airplane plane = (Airplane) new AllinOneHandler().bind(new Airbus());
    System.out.println((plane instanceof Car) + ", " + (plane instanceof Proxy));
    plane.fly();
    plane.land();
    System.out.println();
    Object one = new AllinOneHandler().bind(new SuperMechine());
    System.out.println((one instanceof Car) + ", " + (one instanceof Airplane) + ", " + (plane instanceof Proxy));
    if (one instanceof Car) {
      ((Car)one).drive();
      ((Car)one).beep();
    }
    
    if (one instanceof Airplane) {
      ((Airplane)one).fly();
      ((Airplane)one).land();
    }
  }
  
  static class SuperMechine implements Car, Airplane {

    @Override
    public void fly() {
      System.out.println("SuperMechine fly");
    }

    @Override
    public void land() {
      System.out.println("SuperMechine land");      
    }

    @Override
    public void drive() {
      System.out.println("SuperMechine drive");
    }

    @Override
    public void beep() {
      System.out.println("SuperMechine beep");
    }
    
  }
  
  static class AllinOneHandler implements InvocationHandler {
    private Object target;
    
    public Object bind(Object obj){
      this.target=obj;
      return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      System.out.println("AllinOneHandler::invoke");
      return method.invoke(target, args);
    }
    
  }
  
  static class Tesla implements Car {

    @Override
    public void drive() {
      System.out.println("Tesla drive");
    }

    @Override
    public void beep() {
      System.out.println("Tesla beep");
    }    
  }
  
  static class Airbus implements Airplane {

    @Override
    public void fly() {
      System.out.println("Airbus fly");
    }

    @Override
    public void land() {
      System.out.println("Airbus land");
    }    
  }
  
  interface Car {
    public void drive ();
    public void beep ();
  }
  
  interface Airplane {
    public void fly ();
    public void land ();
  }
  
  
}
