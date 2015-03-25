
public class TestIpadMortgage {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int times = 12;
    double interests = 0.042;
    double totalMoney = 3088;
    double moneyPerTimes = totalMoney/times;
    double totalInterests = 0;
    double leftMoney = totalMoney;
    for(int i = 1; i <= times; i ++) {
      double thisInterests = (leftMoney * interests / 12);
      leftMoney -= moneyPerTimes;
      totalInterests += thisInterests;
      System.out.println(i + "\t: " + thisInterests + ", total: " + totalInterests);
    }
    
    System.out.println("Total: " + totalMoney + " RMB, with interests rate<" +
    interests + ">, phase <" + times + ">, finally get interests:\n\t" +
        totalInterests);
    
    
  }

}
