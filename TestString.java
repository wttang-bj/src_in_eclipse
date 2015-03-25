
public class TestString {

  public enum TaskCounter {
    MAP_INPUT_RECORDS, 
    MAP_OUTPUT_RECORDS,
    MAP_SKIPPED_RECORDS,
    MAP_OUTPUT_BYTES,
    SPLIT_RAW_BYTES,
    COMBINE_INPUT_RECORDS,
    COMBINE_OUTPUT_RECORDS,
    REDUCE_INPUT_GROUPS,
    REDUCE_SHUFFLE_BYTES,
    REDUCE_INPUT_RECORDS,
    REDUCE_OUTPUT_RECORDS,
    REDUCE_SKIPPED_GROUPS,
    REDUCE_SKIPPED_RECORDS,
    SPILLED_RECORDS,
    SHUFFLED_MAPS, 
    FAILED_SHUFFLE,
    MERGED_MAP_OUTPUTS,
    GC_TIME_MILLIS
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
//    String counterName = "SPILLED_RECORDS";
//    TaskCounter counterE = TaskCounter.SPILLED_RECORDS;
//    byte[] b= new byte[3];
//    b[5] = 1;
//    System.out.println(args.length + " " + counterE + "  " + counterE.toString().equals(counterName));
     
    if (args.length > 1) {
      for(String s : args) {
        System.out.println(s);
      }
    }
    System.out.println("twt add minServices:" + args[0]);
    System.out.println("twt add maxServices:" + args[1]);
    
    String s = "main";
    //testS(s);
    //System.out.println(s);
    
  }
  
  static void testS(String s) {
    s = "testS";
  }
  
  static void test(StringBuilder s) {
    s.append("dsd");
    
  }

}
