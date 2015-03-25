package TestSort;

import org.apache.hadoop.util.Progressable;

public final class HeapSort implements IndexedSorter {

  public HeapSort() { }

  private static void downHeap(final IndexedSortable s, final int b,
      int i, final int N) {
    for (int idx = i << 1; idx < N; idx = i << 1) {
      if (idx + 1 < N && s.compare(b + idx, b + idx + 1) < 0) {
        if (s.compare(b + i, b + idx + 1) < 0) {
          s.swap(b + i, b + idx + 1);
        } else return;
        i = idx + 1;
      } else if (s.compare(b + i, b + idx) < 0) {
        s.swap(b + i, b + idx);
        i = idx;
      } else return;
    }
  }

  /**
   * Sort the given range of items using heap sort.
   * {@inheritDoc}
   */
  public void sort(IndexedSortable s, int p, int r) {
    sort(s, p, r, null);
  }

  /**
   * {@inheritDoc}
   */
  public void sort(final IndexedSortable s, final int p, final int r,
      final Progressable rep) {
    final int N = r - p;
    // build heap w/ reverse comparator, then write in-place from end
    final int t = Integer.highestOneBit(N);
    for (int i = t; i > 1; i >>>= 1) {
      for (int j = i >>> 1; j < i; ++j) {
        downHeap(s, p-1, j, N + 1);
      }
      if (null != rep) {
        rep.progress();
      }
    }
    int i = r - 1;
    if (i > p) {
      s.swap(p, i);
      downHeap(s, p - 1, 1, i - p + 1);
      --i;
      for (; i > p; --i) {        
        s.swap(p, i);
        if (s.compare(i, i + 1) == 0) {
          s.setSame(i);
        }
        downHeap(s, p - 1, 1, i - p + 1);
      }
    }
    
    if (N > 1 && s.compare(p, p+1) == 0) {
      System.out.println("first two items are same");
      s.setSame(p);
    }
  }
  
  public static void main(String [] args) {
    MyTestIndex myTest = new HeapSort.MyTestIndex();
    new HeapSort().sort(myTest, 0, myTest.size);
    myTest.print();
  }
  
  static class MyTest implements IndexedSortable {
    public static int size = 5;
    int [] items = {5, 4, 2, 1, 7};
    //int [] items = {5, 1, 2, 1, 7, 1, 3, 1, 5, 6, 11, 5 , 1 , 5, 22};
    int[] sameWithNext = new int[size];
    
    public MyTest () {
      
    }

    @Override
    public int compare(int i, int j) {
      return items[i] - items[j];
    }

    @Override
    public void swap(int i, int j) {
      int tmp = items[i];
      items[i] = items[j];
      items[j] = tmp;
      
    }
    
    public void setSame(int i) {
      this.sameWithNext[i] = 1;
    }
    
    public void print(int i) {
      System.out.println(i + ": " + items[i]);
    }
    
    public void print() {
      System.out.println("--- Print ---");
      for (int i : items) {
        System.out.print(i +" ");
      }
      System.out.println("");
    }
    
  }
  
  static class MyTestIndex implements IndexedSortable {
    public static int size = 2;
    int [] index = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    int [] items = {5, 5};
    //int [] items = {5, 1, 2, 1, 22, 1, 3, 1, 5, 6, 11, 5 , 1 , 5, 22};
    int[] sameWithNext = new int[size];
    

    @Override
    public int compare(int i, int j) {
      return items[index[i]] - items[index[j]];
    }

    @Override
    public void swap(int i, int j) {
      int tmp = index[i];
      index[i] = index[j];
      index[j] = tmp;
      
    }
    
    public void setSame(int i) {
      this.sameWithNext[index[i]] = 1;
    }
    
    public void print(int i) {
      System.out.println(i + ": " + items[index[i]]);
    }
    
    public void print() {
      System.out.println("--- Print ---");
      for (int i = 0; i < size; i++) {
        System.out.print(items[index[i]] +" ");
      }
      System.out.println("");
      for (int i = 0; i < size; i++) {
        System.out.print(sameWithNext[index[i]] + " ");
      }
      System.out.println("");
    }
    
  }
  
  
}
