package problemaSerie2.map;

public class HashDefaultFunction implements IHashFunction<Object> {

    private static IHashFunction<Object> instance = null;

    public static IHashFunction<Object> getDefaultInstance() {
        if (instance == null)
            instance = new HashDefaultFunction();
        return instance;
    }

    public int hashCode(Object o) {
        return o.hashCode();
    }

    public boolean equals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    private HashDefaultFunction() {}
}