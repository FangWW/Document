package com.javapatterns.prototype.cad;

public abstract class CadSymbol implements CadSymbolIF
{
  // methods for Cloneable interface

  public Object clone()
  {

    try
    {
  		return super.clone();
	}
    catch (CloneNotSupportedException e)
    {
  		// This should never happen because this class implements Cloneable
      throw new InternalError();
    }
  }
  // methods for Serializable interface, if needed
//  private void writeObject(java.io.ObjectOutputStream out) throws IOException
//  private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException;
  // identity methods
  private String name;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  abstract public String getType();

  // some example method needed by the cad program
  protected Dimension extents, origin;
  public Dimension getExtents ()
  {
    return extents;
  }

  public void setExtents (Dimension d)
  {
    extents = d;
  }

  public Dimension getOrigin ()
  {
  	return origin;
  }

  public void setOrigin (Dimension d)
  {
    origin = d;
  }

  abstract public void draw (Graphics g);

}
