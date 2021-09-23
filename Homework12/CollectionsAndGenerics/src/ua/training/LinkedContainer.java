package ua.training;

import java.util.*;

public class LinkedContainer<E> extends AbstractList<E> implements Iterable<E>, Iterator<E> {
    private Node<E> fstNode;
    private Node<E> lstNode;
    private Node<E> iterateNode;

    private int size = 0;

    public LinkedContainer(Collection<E> collection){
        Node<E> prevNode;
        Node<E> currentNode = null;

        size = collection.size();

        for (E e: collection){
            if (fstNode==null){
                fstNode=new Node<>(null,e,null);
                currentNode=fstNode;
                continue;
            }

            prevNode = currentNode;

            currentNode = new Node<>(prevNode,e,null);

            prevNode.next = currentNode;
        }

        lstNode = currentNode;

        iterateNode = fstNode;
    }

    public void addFirst(E e){
        fstNode= new Node<>(null ,e, fstNode);
        size++;
    }

    public void addLast(E e){
        Node<E> newlstNode = new Node<>(lstNode,e,null);
        lstNode.next=newlstNode;
        lstNode=newlstNode;
        size++;
    }

    public E getFirst(){
        return fstNode.item;
    }

    public E getLast(){
        return lstNode.item;
    }

    @Override
    public E get(int index){
        if (index<0 || index>=size){
            throw new IndexOutOfBoundsException();
        }

        this.iterateNode = fstNode;
        for (int i=0; i<index; i++){
            this.next();
        }

        E out = this.next();

        this.iterateNode = fstNode;

        return out;
    }

    @Override
    public Iterator<E> iterator() {
        if (!hasNext()) this.iterateNode=fstNode;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean hasNext() {
        return !(iterateNode==null);
    }

    @Override
    public E next() {
        E item = iterateNode.item;

        iterateNode = iterateNode.next;
        return item;
    }

    public static <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        List< T > arrlist = new ArrayList< T >( list );
        Collections.sort(arrlist);
        return arrlist;
    }

    private static class Node<E>{
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next){
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("LinkedContainer: ");
        String bracket1 = "(", bracket2 = ") - ";

        //Node<E> iterateNode = new Node<>(this.iterateNode.prev,
        //        this.iterateNode.item,
        //        this.iterateNode.next);

        //this.iterateNode = fstNode.next;

        for (E e: this){
            output.append(bracket1);
            output.append(e);
            output.append(bracket2);
        }

        //this.iterateNode=iterateNode;

        output.delete(output.length()-3,output.length()-1);

        return output.toString();
    }
}
