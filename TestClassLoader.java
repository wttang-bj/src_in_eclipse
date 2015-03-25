import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


public class TestClassLoader {

  /**
   * @param args
   * @throws NoSuchMethodException 
   * @throws SecurityException 
   * @throws InvocationTargetException 
   * @throws IllegalAccessException 
   * @throws IllegalArgumentException 
   * @throws MalformedURLException 
   * @throws ClassNotFoundException 
   */
  public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, MalformedURLException, ClassNotFoundException {
    // TODO Auto-generated method stub
    {
      
      ClassLoader ccl = (ClassLoader) invoke(Thread.class, Thread.currentThread(),
          "getContextClassLoader", null, null );

      System.out.println("before: " + ccl);
    }
    
    System.out.println("ClassLoader.getCallerClassLoader(): "
    + invoke(ClassLoader.class, ClassLoader.class, "getCallerClassLoader", null, null));
    
    ClassLoader myLoader = new URLClassLoader(new URL[] {new File("D:\\Code\\5.1libs\\commons-ego.jar").toURI().toURL()});
    invoke(Thread.class, Thread.currentThread(),
        "setContextClassLoader", new Class[] {ClassLoader.class}, new Object[] {myLoader});
    
    Class<?> c = Class.forName("com.platform.common.gui.cache.CacheInfo");
    System.out.println("the ClassLoader of MimeTypeFile: " + c.getClassLoader());
    
    new Thread () {
      @Override
      public void run(){
        ClassLoader ccl;
        try {
          ccl = (ClassLoader) invoke(Thread.class, Thread.currentThread(),
              "getContextClassLoader", null, null );

          System.out.println("new Thread: " + ccl);
        } catch (SecurityException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalArgumentException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }.start();
    /**
    Method getContextClassLoaderMethod = Thread.class.getDeclaredMethod("getContextClassLoader", null);
    getContextClassLoaderMethod.setAccessible(true);
    ClassLoader ccl = (ClassLoader)getContextClassLoaderMethod.invoke(Thread.currentThread(), null);
    */
    ClassLoader ccl = (ClassLoader) invoke(Thread.class, Thread.currentThread(),
        "getContextClassLoader", null, null );
    System.out.println(ccl);
  } 

  class Test {
    
  }
  
  public static Object invoke(Class<?> T, Object object, String methodS, 
      Class<?>[] paramsT, Object[] params) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    Method method = T.getDeclaredMethod(methodS, paramsT);
    method.setAccessible(true);
    return method.invoke(object, params);
  }

}
