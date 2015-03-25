/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package TestSort;


import org.apache.hadoop.util.Progressable;

/**
 * An implementation of the core algorithm of QuickSort.
 */
public final class QuickSort implements IndexedSorter {

  private static final IndexedSorter alt = new HeapSort();

  public QuickSort() { }

  private static void fix(IndexedSortable s, int p, int r) {
    if (s.compare(p, r) > 0) {
      s.swap(p, r);
    }
  }

  /**
   * Deepest recursion before giving up and doing a heapsort.
   * Returns 2 * ceil(log(n)).
   */
  protected static int getMaxDepth(int x) {
    if (x <= 0)
      throw new IllegalArgumentException("Undefined for " + x);
    return (32 - Integer.numberOfLeadingZeros(x - 1)) << 2;
  }

  /**
   * Sort the given range of items using quick sort.
   * {@inheritDoc} If the recursion depth falls below {@link #getMaxDepth},
   * then switch to {@link HeapSort}.
   */
  public void sort(IndexedSortable s, int p, int r) {
    sort(s, p, r, null);
  }

  /**
   * {@inheritDoc}
   */
  public void sort(final IndexedSortable s, int p, int r,
      final Progressable rep) {
    sortInternal(s, p, r, rep, getMaxDepth(r - p));
  }

  private static void sortInternal(final IndexedSortable s, int p, int r,
      final Progressable rep, int depth) {
    if (null != rep) {
      rep.progress();
    }
    while (true) {
    if (r-p < 5) {// changed to 3 from 13
      System.out.println("In simple sort");
      for (int i = p; i < r; ++i) {
        for (int j = i; j > p; --j) {
          if (s.compare(j-1, j) > 0) {
            s.swap(j, j-1);
          } else if (s.compare(j-1, j) == 0) {
            s.setSame(j-1);// set same with next
          }
        }
      }
      s.print();
      return;
    }
    if (--depth < 0) {
      // give up
      System.out.println("Give up");
      alt.sort(s, p, r, rep);
      return;
    }

    // select, move pivot into first position
    fix(s, (p+r) >>> 1, p);
    fix(s, (p+r) >>> 1, r - 1);
    fix(s, p, r-1);
    
    s.print (p);
    
    System.out.println("Divide");
    // Divide
    int i = p;
    int j = r;
    int ll = p;
    int rr = r;
    int cr;
    while(true) {
      while (++i < j) {
        if ((cr = s.compare(i, p)) > 0) break;
        if (0 == cr && ++ll != i) {
          s.swap(ll, i);
        }
      }
      while (--j > i) {
        if ((cr = s.compare(p, j)) > 0) break;
        if (0 == cr && --rr != j) {
          s.swap(rr, j);
        }
      }
      if (i < j) s.swap(i, j);
      else break;
    }
    s.print();
    j = i;
    // swap pivot- and all eq values- into position
    int sameSize = ll - p;
    int count =0;
    while (ll >= p) {
      s.swap(ll--, --i);
      if (sameSize-- > 0) {
        s.setSame(ll);// the left one should be same with previous
      }
    }
    s.print();
    sameSize = r - rr;
    if (sameSize-- > 0) {
      s.setSame(j - 1);
    }
    while (rr < r) {
      s.swap(rr++, j);

      if (sameSize-- > 0) {
        s.setSame(j);
      }
      j++;
    }

    s.print();
    // Conquer
    // Recurse on smaller interval first to keep stack shallow
    assert i != j;
    if (i - p < r - j) {
      sortInternal(s, p, i, rep, depth);
      p = j;
    } else {
      sortInternal(s, j, r, rep, depth);
      r = i;
    }
    }
  }
  
  public static void main(String[] args) {
    MyTest test =  new QuickSort.MyTest();
    new QuickSort().sort(test, 0, MyTest.size);
    test.print();
  }
  
  static class MyTest implements IndexedSortable {
    public static int size = 9;
    int [] index = {0, 1, 2, 3, 4, 5, 6, 7, 8};//, 9, 10, 11, 12, 13, 14};
    int [] items = {5, 5, 4, 1, 2, 3, 7, 8, 6};
    //int [] items = {5, 1, 2, 1, 7, 1, 3, 1, 5, 6, 11, 5 , 1 , 5, 22};
    int[] sameWithNext = new int[size];
    
    public MyTest () {
      
    }

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
      for (int i : index) {
        System.out.print(items[i] +" ");
      }
      System.out.println("");
      for (int i : index) {
        System.out.print(sameWithNext[i] + " ");
      }
      System.out.println("");
    }
    
  }
}
