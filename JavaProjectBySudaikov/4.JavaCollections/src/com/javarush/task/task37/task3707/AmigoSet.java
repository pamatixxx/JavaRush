package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;
import java.io.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<E, Object>(Math.max(16, (int) Math.ceil(collection.size() / .75f)));
        for (E e : collection)
            map.put(e, PRESENT);
    }

    @Override
    public AmigoSet clone() throws InternalError {
        AmigoSet copy;
        try {
            copy = (AmigoSet) super.clone();
            copy.map = (HashMap) map.clone();
        } catch (Exception e) {
            throw new InternalError(e);
        }
        return copy;
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }


    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        map.remove(o);
        return super.remove(o);
    }


    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        objectOutputStream.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        objectOutputStream.writeInt(map.size());
        for (E e : map.keySet()) {
            objectOutputStream.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int capacity = objectInputStream.readInt();
        float loadFactor = objectInputStream.readFloat();
       int size = objectInputStream.readInt();

       map = new HashMap<>(capacity,loadFactor);
for (int i = 0; i <size;i++){
    E e = (E) objectInputStream.readObject();
    map.put(e,PRESENT);
}
    }


    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }


}

