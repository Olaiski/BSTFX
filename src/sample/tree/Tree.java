package sample.tree;

import java.util.Collection;

public interface Tree<E> extends Collection<E> {

    boolean search(E e);

    boolean insert(E e);

    boolean delete(E e);

    int getSize();

    default void inOrder(){}

    default void postOrder(){}

    default void preOrder(){}

    @Override
    default boolean isEmpty(){
        return size() == 0;
    }

    @Override
    default boolean contains(Object e){
        return search( (E)e );
    }


    @Override
    default boolean add(E e){
        return insert(e);
    }

    @Override
    default boolean remove(Object e) {
        return delete((E)e);
    }

    @Override
    default int size() {
        return getSize();
    }

    @Override
    default boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    default boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    default boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    default boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    default Object[] toArray() {
        return null;
    }

    @Override
    default <T> T[] toArray(T[] ts) {
        return null;
    }
}

