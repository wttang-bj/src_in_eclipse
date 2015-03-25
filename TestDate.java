import java.text.ParseException;
import java.util.Date;


public class TestDate {
  public static enum LogLevel {
    OFF (0),
      JOB (1),
      TASK (2),
      PHASE (3),
      VERBOSE (4);

      private final int instrumentLevel;

      LogLevel(int level) {
        instrumentLevel = level;
      }

      int logLevel() {
        return instrumentLevel;
      }
  };
  /**
   * @param args
   * @throws ParseException 
   */
  public static void main(String[] args) throws ParseException {
    // TODO Auto-generated method stub
    String s = "Tue Jan 31 19:10:12 CST 2012";
    String sc = "Tue Jan 31 19:10:15 CST 2012";
    String s2 = "Sat, 12 Aug 1995 13:30:00 HKT";
    String s3 = "Tue Jan 31 19:10:12  2012";
    //System.out.println(new Date(13333333));
   // System.out.println(DateFormat.getInstance().parse(s));
    //DateFormat.getDateInstance(DateFormat.LONG, Locale.HKT);
   //System.out.println(DateFormat.getInstance().format(new Date(s)));
    System.out.println(new Date(s).getTime() < new Date(sc).getTime());
    Date date = (Date) null;
    System.out.println(date);
  }

}
