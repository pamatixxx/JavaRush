package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList implements Cloneable, Serializable {

    Entry<String> root;

    public CustomTree() {
        root = new Entry<>("0");
    }

    public String getParent(String s) {
        String nameParent = null;
        ArrayList<Entry<String>> intQueue = new ArrayList<Entry<String>>();
        intQueue.add(root);

        while (!intQueue.isEmpty()) {
            Entry<String> current = intQueue.remove(0);
            if (current.leftChild != null) {
                if (s.equals(current.leftChild.elementName)) return current.elementName;
                intQueue.add(current.leftChild);
            }

            if (current.rightChild != null) {
                if (s.equals(current.rightChild.elementName)) return current.elementName;
                intQueue.add(current.rightChild);
            }
        }
        return nameParent;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof String) {
            boolean deleteElement = false;
            ArrayList<Entry<String>> intQueue = new ArrayList<Entry<String>>();
            intQueue.add(root);

            while (!intQueue.isEmpty()) {
                Entry<String> current = intQueue.remove(0);
                if (current.leftChild != null) {
                    if (o.equals(current.leftChild.elementName)) {
                        current.leftChild = null;
                        current.availableToAddLeftChildren = true;
                        deleteElement = true;
                        break;
                    }
                    intQueue.add(current.leftChild);
                }

                if (current.rightChild != null) {
                    if (o.equals(current.rightChild.elementName)) {
                        current.rightChild = null;
                        current.availableToAddRightChildren = true;
                        deleteElement = true;
                        break;
                    }
                    intQueue.add(current.rightChild);
                }

            }


            return deleteElement;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }


    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Object o) {
        boolean addElement = false;
        ArrayList<Entry<String>> intQueueNull = new ArrayList<Entry<String>>();
        ArrayList<Entry<String>> intQueue = new ArrayList<Entry<String>>();
        intQueue.add(root);

        while (!intQueue.isEmpty()) {
            Entry<String> current = intQueue.remove(0);

            if (current.availableToAddLeftChildren) {
                current.leftChild = new Entry<String>((String) o);
                current.checkChildren();
                addElement = true;
                break;
            } else if (current.leftChild != null) {
                intQueue.add(current.leftChild);
            }

            if (current.availableToAddRightChildren) {
                current.rightChild = new Entry<String>((String) o);
                current.checkChildren();
                addElement = true;
                break;
            } else if (current.rightChild != null) {
                intQueue.add(current.rightChild);
            }
            if (current.rightChild == null && current.leftChild == null)
                intQueueNull.add(current);
        }

        if (!addElement) {
            Entry<String> newElement = new Entry<String>((String) o);

            while (!intQueueNull.isEmpty()) {
                Entry<String> current = intQueueNull.remove(0);

                if (current.leftChild == null) {
                    if (newElement == null) current.availableToAddLeftChildren = true;
                    current.leftChild = newElement;
                    newElement = null;
                }

                if (current.rightChild == null) {
                    if (newElement == null) current.availableToAddRightChildren = true;
                    current.rightChild = newElement;
                    newElement = null;
                }


            }
        }

        return true;
    }


    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int size = -1;
        ArrayList<Entry<String>> intQueue = new ArrayList<Entry<String>>();
        intQueue.add(root);

        while (!intQueue.isEmpty()) {
            Entry<String> current = intQueue.remove(0);
            size++;
            if (current.leftChild != null) {
                intQueue.add(current.leftChild);
            }

            if (current.rightChild != null) {
                intQueue.add(current.rightChild);
            }
        }
        return size;
    }

    static class Entry<T> implements Serializable {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren() {
            if (leftChild != null) availableToAddLeftChildren = false;
            if (rightChild != null) availableToAddRightChildren = false;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }

}
