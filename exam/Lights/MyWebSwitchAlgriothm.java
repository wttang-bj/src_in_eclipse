package exam.Lights;

public class MyWebSwitchAlgriothm implements IAlgriothm{

  @Override
  public int doIt(int total, boolean verbose) {
    int count = 0, index;
    for (int i = 1; ; ++i) {
      index = i * i;
      if (index <= total) {
        if (verbose) {
          System.out.print(index + " ");
        }
        count ++;
      } else {
        break;
      }      
    }
    System.out.println();
    return count;
  }
  
  @Override
  public String getName() {
        return this.getClass().getName();
  }
}
