package exam.Lights;

public class WebSwitchAlgriothm implements IAlgriothm{

  @Override
  public int doIt(int total, boolean verbose) {

    int  [] bulb = new int[total];
    int i = 0;
    int j = 0;

    for(i = 0; i < total; i++){
      bulb[i] = 1;
    }

    for(i = 1; i < total; i++){
      for(j = 0; j < total; j++){
        if((j + 1) % (i + 1) == 0){
          if(bulb[j] > 0){
            bulb[j] = 0;
          }else{
            bulb[j] = 1;
          }
        }
      }
    }

    int count =0;
    for(i = 0; i < total; i++){
      if(bulb[i] > 0){
        count++;
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
