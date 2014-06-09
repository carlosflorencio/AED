package serie3.structures;

import java.util.Comparator;

public class MyComparator <E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E a, E b) {
		return a.compareTo(b);
	}

}
