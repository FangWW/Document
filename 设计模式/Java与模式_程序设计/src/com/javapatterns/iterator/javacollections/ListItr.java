/*
 * @(#)AbstractList.java	1.31 00/02/02
 *
 * Copyright 1997-2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */

package com.javapatterns.iterator.javacollections;

import java.util.AbstractList;
import java.util.ListIterator;
class ListItr extends AbstractList.Itr implements ListIterator {
ListItr(int index) {
    cursor = index;
}

public boolean hasPrevious() {
    return cursor != 0;
}

public Object previous() {
    try {
	Object previous = get(--cursor);
	checkForComodification();
	lastRet = cursor;
	return previous;
    } catch(IndexOutOfBoundsException e) {
	checkForComodification();
	throw new NoSuchElementException();
    }
}

public int nextIndex() {
    return cursor;
}

public int previousIndex() {
    return cursor-1;
}

public void set(Object o) {
    if (lastRet == -1)
	throw new IllegalStateException();
        checkForComodification();

    try {
	AbstractList.this.set(lastRet, o);
	expectedModCount = modCount;
    } catch(IndexOutOfBoundsException e) {
	throw new ConcurrentModificationException();
    }
}

public void add(Object o) {
        checkForComodification();

    try {
	AbstractList.this.add(cursor++, o);
	lastRet = -1;
	expectedModCount = modCount;
    } catch(IndexOutOfBoundsException e) {
	throw new ConcurrentModificationException();
    }
}
}


