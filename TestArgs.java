import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class TestArgs {

  /**
   * @param args
   */
  public static  String SLEEP = "sleep=";
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    long sleepTime = 0;
    Map<String, String> tagMap = new HashMap<String, String>();
    for (int i = 0; i < args.length; i++) {
      if (args[i].startsWith(SLEEP)) {
        System.out.println(Long.parseLong(args[i].substring(SLEEP.length())));
        sleepTime = Long.parseLong(args[i].substring(SLEEP.length()));
      } else {
        String [] pair = args[i].split("=");
        if (pair.length != 2) {
          System.out.println("<" + args[i] + "> is not valid, ignored ");
          continue;
        }
        tagMap.put(pair[0], pair[1]);
      }
    }
    
    Set<Entry<String, String>> tagSets = tagMap.entrySet();
    for (Entry tag : tagSets) {
      System.out.println(tag.getKey() + " : " + tag.getValue());
    }

  }

}
