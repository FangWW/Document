package com.javapatterns.observer.monkeyking;

// You must inherit a new type of Observable:
class BoxObservable extends Observable
{
	public void notifyObservers(Object b)
	{
		// Otherwise it won't propagate changes:
		setChanged();
		super.notifyObservers(b);
	}
}

