package JavaCollections.List;

import java.util.Arrays;

public class myArrayList<E> {
    // Переменная THRESHOLD это предел, если элементов становиться больше
    // чем THRESHOLD, тогда она увеличивается в 1,5 раза.
    // Массив array копируется в новый массив, с увеличенным размером.
    private int THRESHOLD = 10;
    // Под капотом нашего ArrayList как и в оригинальном ArrayList
    // используем массив, делая его динамическим, путем постоянного
    // расширения массива с копированием элементов с одного массива в другой
    private Object[] array = new Object[THRESHOLD];
    private int size = 0;


    public void add(E val) {
        // Если размер становится больше предела, то вызывается метод grow()
        if (size == THRESHOLD) {
            grow();
        }
        array[size] = val;
        size++;
    }

    // Возвращает размер списка
    public int getSize() {
        return size;
    }

    public void add(int index, E val) {
        // Проверка на выход за границы массива
        checkOutOfBound(index);
        // Если размер становится больше предела, то вызывается метод grow()
        if (size == THRESHOLD) {
            grow();
        }
        Object[] array = this.array;
        // Копируем наш массив в наш же массив с добавлением элемента по индексу.
        System.arraycopy(array, index,
                array, index + 1,
                size - index);
        array[index] = val;
        size++;
    }

    // удаляет элемент по индексу
    public void remove(int index) {
        // Проверка на выход за границы массива
        checkOutOfBound(index);
        Object[] array = this.array;
        size--;
        // копируем элементы, в этот же массив, но исключая элемент с переданным индексом
        System.arraycopy(array, index + 1, array, index, size - index);
    }

    @Override
    public String toString() {
        // обрезаем все элементы, которые не были проинициализированы и возвращаем строку
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    // метод увеличивает THRESHOLD в 1,5 раза, и создает новый массив с размером равным THRESHOLD
    private void grow() {
        THRESHOLD = (int) (THRESHOLD * 1.5);
        array = Arrays.copyOf(array, THRESHOLD);

    }

    // Проверка на выход за границы массива
    public void checkOutOfBound(int index) {
        // Если индекс больше или равен размеру массиву, выбрасываем исключение
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
