package com.javapatterns.adapter.demos;

/**
* Tile.java<p>
* The tile class.  This can be expanded to contain methods to check the tile
* attributes, such as whether the tile is solid, or if it is ice.
* @author Eric R. Northam (enorth1@gl.umbc.edu)
* @version 1.0 28 March 1999
*/
class Tile
{
  Image _tileImage;
  int _width;
  int _height;
  int tileY;

  /**
  * The Tile constructor
  * @param tileImage a reference to the image of tiles
  * @param width the width of the tile
  * @param height the height of the tile
  * @param tileNum this tiles number
  */
  public Tile(Image tileImage, int width, int height, int tileNum)
  {
    _tileImage = tileImage;
    _width = width;
    _height = height;

    // Get the y position of the tile in the tile image
    tileY = height * tileNum;

  }

  /**
  * Paints the tile to a graphics context with location x, y.
  * @param g graphics context to paint to
  * @param x x location to paint to.
  * @param y y location to paint to.
  */
  public void paint(Graphics g, int x, int y)
  {
    Graphics g2 = g.create(x, y, _width, _height);
    g2.drawImage(_tileImage, 0, -tileY, null);
    g2.dispose();
  }
}

