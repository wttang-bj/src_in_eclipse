import org.apache.hadoop.conf.Configuration;


public class TestEnumInConf {
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Configuration conf  = new Configuration();
    conf.getEnum("PMR_PERFORMANCE_INSTRUMENT_LEVEL", TestDate.LogLevdel.OFF);
  }

}
