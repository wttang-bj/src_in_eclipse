package verify.drf;

public class DemoAllocation {

  /**
   * @param args
   */
  public static void main(String[] args) {
    //initialize
    //Total
    int totalCpu = 30000, totalMem = 45000;
    //For A
    int reqCpuByA = 5, reqMemByA = 8;
    //For B
    int reqCpuByB = 1, reqMemByB = 10;
    
    MultiDimensionResource totalRes = new MultiDimensionResource(totalCpu, totalMem);
    AllocatedResource res4A = new AllocatedResource("A", reqCpuByA, reqMemByA, totalRes),
        res4B = new AllocatedResource("B", reqCpuByB, reqMemByB, totalRes);
    res4A.printResult();
    res4B.printResult();
    System.out.println(totalRes.toString());
    System.out.println("\n\n========== start ==========\n");
    double drShare4A = 0.0, drShare4B = 0.0;
    boolean isAllocated = false;
    while (res4A.isAssignable || res4B.isAssignable) {
      drShare4A = res4A.getDRShare();
      drShare4B = res4B.getDRShare();
      drShare4A = res4A.getTotalShare();
      drShare4B = res4B.getTotalShare();
      
      if (drShare4A < drShare4B) {
        //assign to A
        isAllocated = totalRes.allocateRes(res4A);
      } else {
        //assign to B
        isAllocated = totalRes.allocateRes(res4B);
      }
    }
    res4A.printResult();
    res4B.printResult();
    System.out.println(totalRes.toString());
  }
}
