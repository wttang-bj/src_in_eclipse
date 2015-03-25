import org.apache.hadoop.util.PriorityQueue;



class IntPQ extends PriorityQueue<Integer>{

  @Override
  protected boolean lessThan(Object a, Object b) {
    return ((Integer)a < (Integer)b);
  }
  
}

public class TestIterator {

}
