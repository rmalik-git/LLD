package org.example.lruCache;

public class DoublyLinkedList<K,V>{
    Node<K,V> head;
    Node<K,V> tail;

    public DoublyLinkedList(){
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node<K,V> newNode){
        newNode.next = head.next;
        newNode.prev = head;
        head.next.prev = newNode;
        head.next = newNode;
    }

    public void remove(Node<K,V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public Node<K,V> removeLast(){
        if(tail.prev == head){
            return null;
        }
        Node<K,V> lastNode = tail.prev;
        remove(lastNode);
        return lastNode;
    }

    public void moveToFront(Node<K,V> node){
        remove(node);
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

}
