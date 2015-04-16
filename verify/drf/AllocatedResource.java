package verify.drf;
class AllocatedResource {
    public int quotaCpu;
    public int quotaMem;
    public int allocCpu;
    public int allocMem;
    MultiDimensionResource total;
    boolean isAssignable = true;
    String name;
    float cpuShare, memShare;
        
    public AllocatedResource(String name, int quotaCpu, int quotaMem, MultiDimensionResource total) {
      this.name = name;
      this.quotaCpu = quotaCpu;
      this.quotaMem = quotaMem;
         
      this.total  = total;
      cpuShare = quotaCpu/(float)total.totalCpu;
      memShare = quotaMem/(float)total.totalMem;
    }
    
    public double getDRShare() {
      if (!this.isAssignable) {
        return Double.MAX_VALUE;
      }
      return cpuShare > memShare ? cpuShare : memShare;
    }
    
    public double getTotalShare() {
      if (!this.isAssignable) {
        return Double.MAX_VALUE;
      }
      return cpuShare+memShare;
    }
    
    public void assignUnit() {
      System.out.print(name + "\tNow:\t <" + this.allocCpu + ":" + this.allocMem + ">, <"
          + cpuShare + "|" + memShare + ">;\n\t"
          + "Assign:\t <"+ this.quotaCpu + ":" + this.quotaMem + ">;\n");
      this.allocCpu +=this.quotaCpu;
      this.allocMem +=this.quotaMem;
      cpuShare = allocCpu/(float)total.totalCpu;
      memShare = allocMem/(float)total.totalMem;

      System.out.println("\tThen:\t <" + this.allocCpu + ":" + this.allocMem + ">, <"
          + cpuShare + "|" + memShare + ">");
    }
    
    public String toString() {
      return name + " \t quota <" + this.quotaCpu + ":" + this.quotaMem +">, assigned <"
          + this.allocCpu + ":" + this.allocMem + ">, share <"
          + cpuShare + "|" + memShare + ">;";
    }
    
    public void printResult() {
      System.out.println("result: " + this);
    }
}