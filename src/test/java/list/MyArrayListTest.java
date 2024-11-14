package list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {
    private MyArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    public void testAddElement() {
        assertTrue(list.add(1));
        assertEquals(1, list.size());
    }

    @Test
    public void testAddMultipleElements() {
        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        assertEquals(15, list.size());
    }

    @Test
    public void testAddAtIndex() {
        list.add(10);
        list.add(0, 2);

        assertEquals(2, list.size());
        assertEquals(2, list.get(0).intValue());
        assertEquals(10, list.get(1).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAtInvalidIndex() {
        list.add(0);
        list.add(2, 5); //Должно выбросить исключение
    }

    @Test
    public void testAddAtEnd() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        list.add(3, 4);
        assertEquals(4, list.size());
        assertEquals(4, list.get(3).intValue());
    }

    @Test
    public void testGetMethod() {
        list.add(100);
        assertEquals(100, list.get(0).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalidIndexMethod() {
        list.get(1);
    }

    @Test
    public void removeElemMethod() {
        list.add(1);
        assertEquals(1, list.remove(0).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeInvalidIndexMethod() {
        list.add(1);
        list.remove(2);
    }

    @Test
    public void removeMultipleElements() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        assertEquals(4, list.remove(4).intValue());
        assertEquals(9, list.size());

    }

    @Test
    public void removeLastElem() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        assertEquals(9, list.remove(list.size() - 1).intValue());
        assertEquals(9, list.size());

    }

    @Test
    public void testRemoveNull() {
        list.add(10);
        assertFalse(list.remove(null)); // Ожидаем false при удалении null
    }

    @Test
    public void testRemoveNonExistentElement() {
        list.add(10);
        list.add(20);
        assertFalse(list.remove(new Integer(30))); // Ожидаем false, поскольку 30 не в списке
    }

    @Test
    public void testRemoveType() {
        list.add(20);
        list.add(10);

        assertTrue(list.remove(new Integer(20)));
        assertFalse(list.remove(new Integer(20))); // Ожидаем false, так как 10 был удалён
        assertEquals(1, list.size());
    }

    @Test
    public void clear() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }
        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    public void clearZeroElements() {
        list.clear();

        assertEquals(0, list.size());
    }

    @Test
    public void testToString() {
        for (int i = 0; i < 3; i++) {
            list.add(i);
        }

        assertEquals("0 1 2", list.toString());
    }

    @Test
    public void testToStringEmptyStr() {
        assertEquals(0, list.size());
        assertEquals("", list.toString());
    }

    @Test
    public void quicksort() {
        list.add(-6);
        list.add(10);
        list.add(-2);
        list.add(5);

        list.quicksort();

        // Ожидаемый отсортированный результат
        String expected = "-6 -2 5 10";
        // Сравнение результата с ожидаемым
        assertEquals(expected, list.toString());
    }

    @Test
    public void quicksortWithString() {
        MyArrayList<String> listString = new MyArrayList<>();

        listString.add("b");
        listString.add("a");
        listString.add("d");
        listString.add("c");

        listString.quicksort();

        // Ожидаемый отсортированный результат
        String expected = "a b c d";
        // Сравнение результата с ожидаемым
        assertEquals(expected, listString.toString());
    }

    @Test(expected = ClassCastException.class)
    public void quicksortNotComparableElements() {
        MyArrayList<MyClassWithoutComparable> test = new MyArrayList<>() {{
            add(new MyClassWithoutComparable("Leha", 2));
            add(new MyClassWithoutComparable("Vasya", 10));
            add(new MyClassWithoutComparable("Bob", 4));
        }};

        test.quicksort();

        String expected = "Leha Vasya Bob";
        assertEquals(expected, test.toString());
    }

    @Test
    public void size() {
        assertEquals(0, list.size());
    }

    @Test
    public void sizeMultipleElements() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        assertEquals(10, list.size());
    }
}