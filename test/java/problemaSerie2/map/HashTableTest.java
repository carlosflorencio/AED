package problemaSerie2.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class HashTableTest {

    /*
    |--------------------------------------------------------------------------
    | Test Put
    |--------------------------------------------------------------------------
    */
    @Test
    public void testPutWithoutColisions() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(2, "dois"));

        assertEquals(1, table.getSize());
        assertEquals("dois", table.get(2));
    }

    @Test
     public void testPutWithColisions() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>(5);
        assertNull(table.put(10, "dez"));
        assertNull(table.put(15, "quinze"));

        assertEquals(2, table.getSize());
        assertEquals("dez", table.get(10));
        assertEquals("quinze", table.get(15));
    }

    @Test
    public void testPutEqualKey() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(1, "um"));
        assertEquals("um", table.put(1, "dois"));

        assertEquals(1, table.getSize());
        assertEquals("dois", table.get(1));
    }

    @Test
    public void testPutNull() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(null, "um"));

        assertEquals(0, table.getSize());
    }

    /*
    |--------------------------------------------------------------------------
    | Test Get
    |--------------------------------------------------------------------------
    */
    @Test
    public void testGetNull() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.get(null));
    }

    @Test
    public void testGetOfAnExistantKey() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(2, "dois"));

        assertEquals(1, table.getSize());
        assertEquals("dois", table.get(2));
    }

    @Test
    public void testGetOfAnInexistantKey() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(2, "dois"));

        assertEquals(1, table.getSize());
        assertEquals(null, table.get(3));
    }

    /*
    |--------------------------------------------------------------------------
    | Test remove
    |--------------------------------------------------------------------------
    */
    @Test
    public void testRemoveWithoutColisions() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(2, "dois"));

        assertEquals(1, table.getSize());
        assertEquals("dois", table.remove(2));
        assertTrue(table.isEmpty());
    }

    @Test
    public void testRemoveWithColisions() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>(5);
        assertNull(table.put(10, "dez"));
        assertNull(table.put(15, "quinze"));

        assertEquals("dez", table.remove(10));
        assertEquals(1, table.getSize());
        assertEquals("quinze", table.get(15));
    }

    @Test
    public void testRemoveWithColisionsFirstItem() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>(5);
        assertNull(table.put(10, "dez"));
        assertNull(table.put(15, "quinze"));

        assertEquals("quinze", table.remove(15));
        assertEquals(1, table.getSize());
        assertEquals("dez", table.get(10));
    }

    @Test
    public void testRemoveOfAnInexistantKey() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(2, "dois"));

        assertEquals(1, table.getSize());
        assertNull(table.remove(3));
    }

    @Test
    public void testRemoveOfANullKey() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();

        assertNull(table.remove(null));
    }

    /*
    |--------------------------------------------------------------------------
    | Test rehash
    |--------------------------------------------------------------------------
    */
    @Test
    public void testRehash() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>(1);

        assertTrue(0 == table.getCurrentFactor());

        assertNull(table.put(1, "um"));

        assertTrue(0.5f == table.getCurrentFactor());
    }

    @Test
    public void testRehashWithCustomLoadFactor() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>(5, 0.5f);
        assertNull(table.put(1, "um"));
        assertNull(table.put(2, "dois"));

        assertTrue(0.4f == table.getCurrentFactor());

        assertNull(table.put(3, "tres"));

        assertTrue(0.3f == table.getCurrentFactor());
    }

    /*
    |--------------------------------------------------------------------------
    | Test iterator
    |--------------------------------------------------------------------------
    */
    @Test
    public void testIteratorWithEmptyIndexes() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();

        Iterator<String> itr = table.iterator();

        assertFalse(itr.hasNext());

        try {
            itr.next();
            fail("should throw exception");
        } catch (NoSuchElementException e) {

        }

    }

    @Test
    public void testIteratorWithValidValues() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();

        assertNull(table.put(1, "um"));
        assertNull(table.put(2, "dois"));
        assertNull(table.put(3, "tres"));

        Iterator<String> itr = table.iterator();

        assertTrue(itr.hasNext());

        assertEquals("um", itr.next());
        assertEquals("dois", itr.next());
        assertEquals("tres", itr.next());
        assertFalse(itr.hasNext());

        try {
            itr.next();
            fail("should throw exception");
        } catch (NoSuchElementException e) {

        }
    }

    @Test
    public void testIteratorWithColisions() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>(5);
        assertNull(table.put(10, "dez"));
        assertNull(table.put(15, "quinze"));

        Iterator<String> itr = table.iterator();

        assertTrue(itr.hasNext());

        assertEquals("quinze", itr.next());
        assertEquals("dez", itr.next());
        assertFalse(itr.hasNext());

        try {
            itr.next();
            fail("should throw exception");
        } catch (NoSuchElementException e) {

        }

    }

    @Test
    public void testIteratorRemove() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();

        assertNull(table.put(1, "um"));
        assertNull(table.put(2, "dois"));
        assertNull(table.put(3, "tres"));

        Iterator<String> itr = table.iterator();

        assertTrue(itr.hasNext());

        assertEquals("um", itr.next());
        assertEquals("dois", itr.next());
        itr.remove();
        assertEquals("tres", itr.next());
        assertFalse(itr.hasNext());

        itr = table.iterator();
        assertEquals("um", itr.next());
        assertEquals("tres", itr.next());
        assertFalse(itr.hasNext());
    }

    @Test
    public void testIteratorFailedRemove() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();

        assertNull(table.put(1, "um"));
        assertNull(table.put(2, "dois"));
        assertNull(table.put(3, "tres"));

        Iterator<String> itr = table.iterator();

        try {
            itr.remove();
            fail("should throw exception");
        } catch (IllegalStateException e) {

        }

        assertEquals("um", itr.next());
        assertEquals("dois", itr.next());
        itr.remove();
        try {
            itr.remove();
            fail("should throw exception");
        } catch (IllegalStateException e) {

        }
        assertEquals("tres", itr.next());
    }

    /*
    |--------------------------------------------------------------------------
    | Test Getters
    |--------------------------------------------------------------------------
    */
    @Test
    public void testGetSize() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();
        assertNull(table.put(2, "dois"));

        assertEquals(1, table.getSize());
    }

    @Test
    public void testIsEmpty() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();

        assertTrue(table.isEmpty());
    }

    @Test
    public void testGetFactor() throws Exception {
        HashTable<Integer, String> table = new HashTable<Integer, String>();

        assertTrue(0.75f == table.getFactor());
    }
}