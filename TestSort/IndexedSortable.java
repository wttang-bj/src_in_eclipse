package TestSort;
public interface IndexedSortable {

  /**
   * Compare items at the given addresses consistent with the semantics of
   * {@link java.util.Comparator#compare(Object, Object)}.
   */
  int compare(int i, int j);

  /**
   * Swap items at the given addresses.
   */
  void swap(int i, int j);
  
  void setSame(int i);
  void print(int i);
  void print();
}