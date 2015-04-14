package exam.Lights;

public class SwitchLightsTest {
  
  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    int total = 99999;
    System.out.println("Test lights mumber: " + total);
    
    LightsSwitchDriver driver = new LightsSwitchDriver(total, true);
    driver.setAlgriothm(new MySwitchAlgriothm());
    driver.run();    
    
    driver.setAlgriothm(new WebSwitchAlgriothm());
    driver.run();
    
    driver.setAlgriothm(new MyWebSwitchAlgriothm());
    driver.run();
    
    driver.setAlgriothm(new MyCompareSwitchAlgriothm());
    driver.run();
  }

}
