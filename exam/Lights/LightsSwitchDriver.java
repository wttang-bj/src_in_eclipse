package exam.Lights;

/**
 * Driver, to test algriothm
 * @author wttang
 *
 */
public class LightsSwitchDriver {
  
  private long startTime, endTime;
  private IAlgriothm algriothm;
  private int totalNum;
  boolean verbose;
  
  public LightsSwitchDriver(int totalNum, boolean verbose) {
    this.totalNum = totalNum;
    this.verbose = verbose;
  }
  
  private void start() {
    startTime = System.currentTimeMillis();
    System.out.println(algriothm.getName() + ":");
    
  }
  
  private void end() {
    endTime = System.currentTimeMillis();
    int duration = (int) (endTime - startTime);
    System.out.println("Time passed: " + duration + "\n");
  }
  
  public void setAlgriothm(IAlgriothm algriothm) {
    this.algriothm = algriothm;
  }
    
  public final int run() throws Exception {
    if (algriothm == null) {
      throw new Exception("Algriothm is not set yet");
    }
    start();
    int ret = algriothm.doIt(totalNum, verbose);
    System.out.println("total on lights:" + ret);
    end();
    return ret;
  }
  
  /**
  public final int run(int totalNum) throws Exception {
    return run(totalNum, false);
  }
  */
  
}
