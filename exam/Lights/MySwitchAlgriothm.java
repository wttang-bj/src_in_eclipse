package exam.Lights;

public class MySwitchAlgriothm implements IAlgriothm {

  @Override
  public int doIt(int totalNum, boolean verbose) {
    int [] lights = new int[totalNum];//byte is OK
    
    for(int i = 0; i < totalNum; ++i) {
      lights[i] = 1;
    }
    
    for (int i = 2; i <= totalNum; i ++) {
      for (int k = i ; k <= totalNum; k+=i) {
        lights[k-1] ^=1;
      }
    }
    
    int count = 0;
    for(int i = 0; i < totalNum; ++i) {
      if (lights[i] != 0) {
        count ++;
        if (verbose) {
          System.out.print((i+1) + " ");
        }
      }
      
    }   
    System.out.println();
    return count;  
  }

  @Override
  public String getName() {
        return this.getClass().toString();
  }

}
