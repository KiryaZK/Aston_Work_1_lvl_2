package list;

/**
 * Класс MyArrayList - реализация ArrayList для хранения динамического массива (списка) различных ссылочных типов.
 * @author Zakharov Kirill
*/
public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};
    private Object[] array;
    private int size;
    /**
     * Создает пустой список с начальной емкостью 0.
    */
    public MyArrayList() {
        array = EMPTY_ARRAY;
    }
    /**
     * Создает список с определенной емкостью, переданной в качестве параметра.
     * @param initCapacity - начальная емкость списка
     * @throws IllegalArgumentException - если начальная емкость отрицательная
     */
    public MyArrayList(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("Неправильный размер: " + initCapacity);
        }
        else if (initCapacity == 0) {
            array = EMPTY_ARRAY;
        }
        else {
            array = new Object[initCapacity];
        }
    }
    /**
     * Добавляет элемент в список.
     * @param elem - элемент определенного типа параметра, добавляемый в список
     */
    public boolean add(T elem) {
        if (size + 1 >= array.length) {
            array = grow();
        }
        array[size] = elem;
        ++size;

        return true;
    }
    /**
     * Добавляет элемент в спиоск, на определенную позицию.
     * @param index - индекс, на который буден добавлен элемент
     * @param elem - элемент определенного типа параметра, добавляемый в список
     * @throws IllegalArgumentException - если переданный индекс отрицательный или больше размера списка
     */
    public boolean add(int index, T elem) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Неверный индекс: " + index + ", size = " + size);
        }
        if (index == size) array = grow();

        addElementToArray(index, elem);
        ++size;
        
        return true;
    }
    private void addElementToArray(int index, T elem) {
        if (index == size) {
            array[size] = elem;
            return;
        }
        for (int i = size; i >= index; --i) {
            array[i + 1] = array[i];
        }
        array[index] = elem;
    }
    private Object[] grow() {
        Object[] newArr;

        if (array.length == 0) {
            newArr = new Object[DEFAULT_CAPACITY];
        } else {
            newArr = new Object[array.length + (array.length / 2)];
        }

        for (int i = 0; i < size; i++) {
            newArr[i] = array[i];
        }

        return newArr;
    }
    /**
     * Получаем элемент по переданному индексу.
     * @param index - индекс элемента, который содержится в списке
     * @throws IllegalArgumentException - если переданный индекс попадает в диапазон (index < 0 || index >= size)
     * @see MyArrayList#checkIndex(int)
     */
    public T get(int index) {
        checkIndex(index);

        return (T) array[index];
    }
    /**
     * Удаляет элемент по переданному параметром индексу из списка, затем возвращает его.
     * @param index - индекс элемента, который содержится в списке
     * @throws IllegalArgumentException - если переданный индекс попадает в диапазон (index < 0 || index >= size)
     * @see MyArrayList#checkIndex(int)
     */
    public T remove(int index) {
        checkIndex(index);
        T temp = (T) array[index];

        removeElem(index);

        return temp;
    }
    /**
     * Удаляет элемент, переданный в качестве параметра, из списка, если он в нем содержиться.
     * @param elem - элемент, который может содержаться в списке
     */
    public boolean remove(T elem) {
        if (elem == null)
            return false;
        else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(elem)) {
                    removeElem(i);
                    return true;
                }
            }
        }

        return false;
    }
    private void removeElem(int index) {
        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[size - 1] = null;

        --size;
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Неверный индекс: " + index + ", size = " + size);

    }
    /**
     * Очищает список, удаляя элементы из внутреннего массива.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }

        size = 0;
    }
    /**
     * Возвращает строку, состоящую из элементов списка.
     */
    @Override
    public String toString() {
        StringBuilder arr = new StringBuilder();

        for (int i = 0; i < size; i++)
            arr.append(array[i]).append(" ");


        return arr.toString().trim();
    }
    /**
     * Сортирует элементы списка алгоритмом быстрой сортировки, выбирая последний элемент в качестве опорного
     * и деля по левую и по правую сторону от опорного, элменты меньшие и большие опорного, рекурсивно вызывая метод
     * @see MyArrayList#qSort(Object[], int, int) ()
     * @throws ClassCastException - если элемент массива не может быть приведен к типу интерфейса Comparable
     * @see MyArrayList#partition(Object[], int, int) 
     */
    public void quicksort() {
        qSort(array, 0, size - 1);
    }

    private void qSort(Object[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);


            qSort(arr, low, pi - 1);
            qSort(arr, pi + 1, high);
        }
    }

    private int partition(Object[] arr, int low, int high) {
        Object pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] instanceof Comparable && pivot instanceof Comparable) {
                Comparable obj1 = (Comparable) arr[j];
                Comparable obj2 = (Comparable) pivot;

                if (obj2.compareTo(obj1) > 0) {
                    ++i;

                    Object temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                }
            }
            else {
                throw new ClassCastException("Data in the list is not comparable");
            }

        }

        Object temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public int size() {
        return size;
    }
}
