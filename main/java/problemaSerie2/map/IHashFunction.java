package problemaSerie2.map;

public interface IHashFunction<E> {
    int hashCode(E e);
    boolean equals(E e1, E e2);
}
