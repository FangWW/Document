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

import java.util.Iterator;
class Itr implements Iterator {
/**
 * Index of element to be returned by subsequent call to next.
 */
int cursor = 0;

/**
 * Index of element returned by most recent call to next or
 * previous.  Reset to -1 if this element is deleted by a call
 * to remove.
 */
int lastRet = -1;

/**
 * The modCount value that the iterator believes that the backing
 * List should have.  If this expectation is violated, the iterator
 * has detected concurrent modification.
 */
int expectedModCount = modCount;

public boolean hasNext() {
    return cursor != size();
}

public Object next() {
    try {
	Object next = get(cursor);
	checkForComodification();
	lastRet = cursor++;
	return next;
    } catch(IndexOutOfBoundsException e) {
	checkForComodification();
	throw new NoSuchElementException();
    }
}

public void remove() {
    if (lastRet == -1)
	throw new IllegalStateException();
        checkForComodification();

    try {
	AbstractList.this.remove(lastRet);
	if (lastRet < cursor)
	    cursor--;
	lastRet = -1;
	expectedModCount = modCount;
    } catch(IndexOutOfBoundsException e) {
	throw new ConcurrentModificationException();
    }
}

final void checkForComodification() {
    if (modCount != expectedModCount)
	throw new ConcurrentModificationException();
}
}


