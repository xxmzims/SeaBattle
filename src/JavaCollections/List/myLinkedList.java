package JavaCollections.List;

public class myLinkedList<E> {
    private Node<E> head;
    private int size = 0;
    // возвращаем размер списка
    public int getSize() {
        return size;
    }
    // Узел представляет вложенный класс Node. Node next хранит ссылку на следующий узел, val это значение текущего узла
    private static class Node<E>{
        private Node<E> next;
        private E val;
        Node(E val) {
            this.val = val;
        }
        Node() {}
    }
    // добавление элемента в конец
    public void add(E val){
        // если первое добавление, создаем голову, создаем первый элемент, передаем ссылку на первый элемент в head.next
        if (head == null){
            head = new Node<>(val);
        }else{
            // если нет, то создаем переменную temp для перехода по узлам, доходим до последнего и создаем новый узел
            Node<E> temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node<>(val);
        }
        //  инкриминируем размер на 1
        size++;
    } // получения элемента по индексу
    public E get(int index){
        // проверка на выход за пределы массива
        checkOutOfBound(index);
        // создаем временную переменную для обхода по узлам
        Node<E> temp = head;
        // как только достигаем нужного индекса, возвращаем значение узла
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }
    public void remove(int index){
        // проверка на выход за пределы массива
        checkOutOfBound(index);
        // создаем временную переменную для обхода по узлам
        Node<E> temp = head;
        // если индекс равен нулю, то сразу передаем в head.next ссылку на
        // элемент, который находится следующим, тем самым "перепрыгиваем через узел"
        // и теперь head ссылается на узел, который находится после узла, который мы удаляем
        if(index == 0){
            head.next = head.next.next;
            size--;
            return;
        }
        // доходим до узла, предшествующего узлу, который нам нужно удалить.
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        // передаем ссылку в следующий элемент, "перепрыгивая" через узел,
        // который нам нужно удалить, тем самым связываем узел,
        // который находится до удаляемого узла, с узлом, который находится после удаляемого узла
        temp.next = temp.next.next;
        size--;
    }
    @Override
    public String toString(){
        // создаем объект StringBuilder и добавляем "["
        StringBuilder stringRes = new StringBuilder();
        stringRes.append("[");
        // создаем временную переменную для обхода по узлам
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            // если узел последний, то не добавляем в конец запятую с пробелом
            if (i == size - 1){
                stringRes.append(temp.val);
            }else
                // все остальные элементы добавляются через запятую с пробелом
                stringRes.append(temp.val).append(", ");
            temp = temp.next;
        }
        // в конец добавляем "]" и возвращаем преобразованную в тип String строку
        stringRes.append("]");
        return stringRes.toString();
    }
    private void checkOutOfBound(int index){
        // если индекс больше размера списка, то выбрасываем исключение
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }
    }
}
