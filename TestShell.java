import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.util.Shell;

import com.platform.mapreduce.work.internal.SessionData;


public class TestShell {

  /**
   * @param args
   * @throws IOException 
   */
  private List<String> getUnixGroups(final String user) throws IOException {
    String result = "";
    try {
      Method getUserGroupMethod = null;
      getUserGroupMethod = Shell.class.getMethod("getGroupsForUsesrCommand", new Class[] {java.lang.String.class});
      if (getUserGroupMethod != null) {
        getUserGroupMethod.setAccessible(true);
        result = Shell.execCommand((String[]) getUserGroupMethod.invoke(null, new Object[] {user}));
      }
    } catch (Exception e) {
      // if we didn't get the group - just return empty list;
      System.out.println("got exception trying to get groups for user " + user + e);
    }
    
    StringTokenizer tokenizer = new StringTokenizer(result);
    List<String> groups = new LinkedList<String>();
    while (tokenizer.hasMoreTokens()) {
      groups.add(tokenizer.nextToken());
    }
    return groups;
  }
  public static void main(String[] args) throws IOException {
    
    List<String> ugs = new TestShell().getUnixGroups("wttang");
    for(String ug : ugs) {
      System.out.println(ug);
    }
  }
}
//    String user = "wttang";
//    Method getUserGroupMethod = null;
//    try {
//      getUserGroupMethod = Shell.class.getMethod("getGroupsForUserCommand", new Class[] {java.lang.String.class});
//    } catch (NoSuchMethodException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    
//    if (getUserGroupMethod != null) {
//      getUserGroupMethod.setAccessible(true);
//
//      String[] cmd = {""};
//      try {
//        cmd = (String[]) getUserGroupMethod.invoke(null, new Object[] {user});
//      } catch (IllegalArgumentException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      } catch (IllegalAccessException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      } catch (InvocationTargetException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//
//      System.out.println(Shell.execCommand(cmd));
//    }
//    
//  }

