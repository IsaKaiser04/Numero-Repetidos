package unl.practica.com.base.controller.DataStruct.list;

public class LinkedList<E> {
    private Node<E> head; // sinn E es un object
    private Node<E> last;
    private Integer lenght;

    public LinkedList() {                                                                       // constructor
        head = null;
        last = null;
        lenght = 0;
    }

    public Integer getLenght() {                                                                // obtiene el tamaño de la lista
        return lenght;
    }

    public Boolean isEmpty() {                                                                  // verifica si la lista esta vacia
        return head == null || lenght == 0;
    }

    private Node<E> getNode(Integer pos) {                                                      // obtiene el nodo en la posicion deseada

        if (isEmpty()) {                                                                        // Verifica si la lista esta vacia
            // System.out.println("La lista esta vacia");
            // return null;
            throw new ArrayIndexOutOfBoundsException("La lista esta vacia");                    // lanza una excepcion

        } else if (pos < 0 || pos >= lenght) {                                                  // verifica si la posicion es menor a 0 o mayor al tamaño de la lista
            throw new ArrayIndexOutOfBoundsException("La lista esta vacia");                    // lanza una excepcion
            // System.out.println("Fuera de Rango");
            // return null;

        } else if (pos == 0) {                                                                  // verifica si la posicion es 0 dentro de la lista
            return head;

        } else if ((lenght.intValue() - 1) == pos.intValue()) {                                 // Verifica si la posicion es igual al tamaño de la lista
                                                               // -1
            return last;

        } else {
            Node<E> search = head;
            Integer count = 0;
            while (count < pos) {                                                               // obtiene el nodo en la posicion deseada
                search = search.getNext();                                                      // obtiene el siguiente nodo
                count++;
            }
            return search;
        }
    }

    public E get(Integer pos) throws Exception {                                                // obtiene el dato en la posicion deseada
        return getNode(pos).getData();
    }

    public void add(E data, Integer pos) throws Exception {                                     // agrega un dato en la posicion deseada
        if(isEmpty()) { // verifica si la lista esta vacia
            addFirst(data);
            return;
        }
        if (pos < 0 || pos > lenght) {
            throw new ArrayIndexOutOfBoundsException("Posición fuera de rango");
        }
        else if(pos == 0) { // verifica si la posicion es 0
            addFirst(data);
        }
        else if (lenght.intValue() == pos.intValue()) {
            addLast(data);
        } else {                                                // verifica si la posicion es igual al tamaño de la lista
            Node<E> search_preview = getNode(pos - 1);          // obtiene el nodo anterior al nodo deseado
            Node<E> search = getNode(pos);                      // obtiene el nodo en la posicion deseada
            Node<E> aux = new Node<>(data, search);             // crea un nuevo nodo con el dato y el siguiente nodo
            search_preview.setNext(aux);                        // establece el siguiente nodo del nodo anterior al nuevo nodo
            lenght++;                                           // incrementa el tamaño de la lista
        }
    }

    private void addFirst(E data) {// agrega el dato al inicio de la lista
        if (isEmpty()) {
            Node<E> aux = new Node<>(data);
            head = aux;
            last = aux;
        } else {
            Node<E> head_old = head;
            Node<E> aux = new Node<>(data, head_old);
            head = aux;

        }
        lenght++;
    }

    private void addLast(E data) {// agrega el dato al final de la lista
        if (isEmpty()) {
            addFirst(data);
        } else {
            Node<E> aux = new Node<>(data);
            last.setNext(aux);
            last = aux;
            
        }
        lenght++;

    }

    private void addMiddle(E data, Integer pos) {// agrega el dato en la posicion deseada
        if (pos == 0) {
            addFirst(data);

        } else if (lenght.intValue() == pos.intValue()) {
            addLast(data);
        } else {
            Node<E> search_preview = getNode(pos - 1);// obtiene el nodo anterior al nodo deseado
            Node<E> search = getNode(pos);// obtiene el nodo en la posicion deseada
            Node<E> aux = new Node<>(data, search);// crea un nuevo nodo con el dato y el siguiente nodo
            search_preview.setNext(aux);// establece el siguiente nodo del nodo anterior al nuevo nodo
            lenght++;// incrementa el tamaño de la lista
        }
    }

    private E getDataFirst() {// obtiene el primer dato de la lista
        if (isEmpty()) {
            // System.out.println("La lista esta vacia");
            throw new ArrayIndexOutOfBoundsException("La lista esta vacia");
        } else {
            return head.getData();
        }
    }

    private E getDataLast() {// obtiene el ultimo dato de la lista
        if (isEmpty()) {
            // System.out.println("La lista esta vacia");
            throw new ArrayIndexOutOfBoundsException("La lista esta vacia");
        } else {
            return last.getData();
        }
    }

    private E getDataMiddle(Integer pos) {// obtiene el dato en la posicion deseada
        if (isEmpty()) {
            // System.out.println("La lista esta vacia");
            throw new ArrayIndexOutOfBoundsException("La lista esta vacia");
        } else if (pos < 0 || pos >= lenght) {
            // System.out.println("Fuera de Rango");
            throw new ArrayIndexOutOfBoundsException("Fuera de Rango");
        } else {
            return getNode(pos).getData();
        }
    }

    public void add(E data) throws Exception {// agrega un dato al final de la lista
        addLast(data);// agrega el dato al final de la lista
    }

    public void update(E data, Integer pos) throws Exception {// actualiza el dato en la posicion deseada
        getNode(pos).setData(data);
    }

    public void clear() {
        head = null;
        last = null;
        lenght = 0;
    }

    public void delete(E data, Integer por) throws Exception {// elimina el dato en la posicion deseada
        if (isEmpty()) {
            // System.out.println("La lista esta vacia");
            throw new ArrayIndexOutOfBoundsException("La lista esta vacia");
        } else if (data == head.getData()) {                        // verifica si el dato es igual al primer dato de la lista
            head = head.getNext();                                  // establece el siguiente nodo como el nuevo primer nodo
            lenght--;                                               // decrementa el tamaño de la lista
        } else if (data == last.getData()) {                        // verifica si el dato es igual al ultimo dato de la lista
            Node<E> search = head;
            while (search.getNext() != last) {
                search = search.getNext();
            }
            search.setNext(null);
            last = search;
            lenght--;
        } else {                                                    // verifica si el dato es igual a un dato en la lista
            Node<E> search = head;                                  // obtiene el primer nodo
            while (search.getNext() != null) {                      // obtiene el siguiente nodo y verifica si es nulo
                if (search.getNext().getData() == data) {           // verifica si el siguiente nodo es igual al dato
                    search.setNext(search.getNext().getNext());     // establece el siguiente nodo del nodo actual al
                                                                    // siguiente nodo del siguiente nodo
                    lenght--;
                    break;
                }
                search = search.getNext();                          // obtiene el siguiente nodo
            }
        }
    }

    /*public String print() {
        if (isEmpty()) {
            return "Ta vacia tu vaina oe";
        } else {
            StringBuilder txt = new StringBuilder();
            Node<E> help = head;
            while (help != null) {
                txt.append(help.getData()).append(" -- ");
                help = help.getNext();
            }
            txt.append("\n");
            return txt.toString();
        }
    }*/
}
