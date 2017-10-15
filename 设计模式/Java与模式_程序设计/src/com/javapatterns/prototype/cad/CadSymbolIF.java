// CadSymbolIF - symbol library interface

package com.javapatterns.prototype.cad;

public interface CadSymbolIF extends Cloneable, Serializable
{
  public String getName();
  public void setName(String name);
  public String getType();

  // some example method needed by the cad program

  public Dimension getExtents ();

  public void setExtents (Dimension d);

  public Dimension getOrigin ();

  public void setOrigin (Dimension d);

  public void draw (Graphics g);

}
