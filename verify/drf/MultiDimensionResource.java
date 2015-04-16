package verify.drf;

public class MultiDimensionResource {
  public int totalCpu;
  public int totalMem;
  public int freeCpu;
  public int freeMem;
  public int round;
    
  public MultiDimensionResource(int cpu, int mem) {
    this.totalCpu = this.freeCpu = cpu;
    this.totalMem = this.freeMem = mem;
  }
  
  public boolean allocateRes(AllocatedResource reqUnit) {
    boolean ret  = false;
    int leftCpu = freeCpu - reqUnit.quotaCpu;
    int leftMem = freeMem - reqUnit.quotaMem;
    System.out.println("---------- Round#" + ++round + "----------");
    if (leftCpu < 0 || leftMem < 0) {
      System.out.println(reqUnit.name + ": no enought resource for it. Need <"
          + reqUnit.quotaCpu + ":" + reqUnit.quotaMem +">, left <" 
          + freeCpu + ":" + freeMem + ">");
      reqUnit.isAssignable = false;
      ret = false ;
    } else {
      this.freeCpu = leftCpu;
      this.freeMem = leftMem;
      reqUnit.assignUnit();
      ret = true;
    }
    System.out.println("====================\n\n");
    return ret;
  }
  public String toString() {
    return "MultiDimensionResource state: " +
        totalCpu +":"+ totalMem +"; free " + freeCpu + ":" + freeMem;
  }
}
