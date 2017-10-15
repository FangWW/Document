package com.javapatterns.adapter.demos;
/**
* TileMapper.java<p>
* Draws a simple tile based image map on the screen and allows the user
* to scroll the map with the arrow keys.
* @author Eric R. Northam (enorth1@gl.umbc.edu)
* @version 1.0 9 May 1999
*/
public class TileMapper extends Applet
{
  Image screenImage;  // Image used for double buffering and its respective
  Graphics screenGC;  // graphics context
  static final int screenWidth = 400;
  static final int screenHeight = 400;
  Image tileImage; // Use to store the image for the tiles
  static final int numTiles = 6; // The number of tiles
  static final int scrollInc = 8;  // How much pixels to move at each scroll

  int tileWidth;
  int tileImageHeight;
  int tileHeight;
  Tile[] tiles; // An array to hold all of the tiles

  static final byte[][] bgMap = {
			      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 2, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 5, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 2, 0, 0, 0, 0, 3, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 5, 0, 0, 0, 0, 4, 1, 1, 1, 1, 5, 0, 0, 0, 4, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

  static final int bgmapTileWidth = 20;
  static final int bgmapTileHeight = 16;
  int bgWidth;
  int bgHeight;

  int numXtiles;
  int numYtiles;
  int mapX = 0;
  int mapY = 0;


  public void init()
  {
    addKeyListener(new KeyAdapter(){
      public void keyPressed(KeyEvent e)
      {
        int keyCode = e.getKeyCode();

        switch(e.getKeyCode()){

          case KeyEvent.VK_UP:
            scrollUp();
            break;
          case KeyEvent.VK_RIGHT:
            scrollRight();
            break;
          case KeyEvent.VK_DOWN:
            scrollDown();
            break;
          case KeyEvent.VK_LEFT:
            scrollLeft();
            break;
          case KeyEvent.VK_PAGE_UP:
            scrollUpRight();
            break;
          case KeyEvent.VK_PAGE_DOWN:
            scrollDownRight();
            break;
          case KeyEvent.VK_HOME:
            scrollUpLeft();
            break;
          case KeyEvent.VK_END:
            scrollDownLeft();
            break;
        }
        repaint();
      }
    });
  }

  /**
  * Loads an image, splits it into tiles, and then displays each of the tiles
  * diagonally.
  * note: Netscape does not properly load the applet if calls to getImage() are
  * in the init() method, so I decided to just use the start() method
  */
  public void start()
  {
    resize(screenWidth, screenHeight);
    requestFocus();
    tileImage = getImage(getDocumentBase(), "images/road.gif");

    // Wait for the image to load
    MediaTracker tracker = new MediaTracker(this);
    tracker.addImage(tileImage, 0);
    try { tracker.waitForID(0); }
    catch (InterruptedException e) {}

    // Get tile dimensions
    tileImageHeight = tileImage.getHeight(this);
    tileWidth = tileImage.getWidth(this);
    tileHeight = tileImageHeight/numTiles;

    // Get the dimensions of the map
    bgWidth = tileWidth * bgmapTileWidth;
    bgHeight = tileHeight * bgmapTileHeight;

    // Create an offsreen image for double buffering
    // Give it a border with tilesize larger than the screen
    screenImage = createImage(screenWidth + 2*tileWidth,
                              screenHeight + 2*tileHeight);
    screenGC = screenImage.getGraphics();

    // Break image into tiles.
    prepareTiles();

    // Draw the upper left portion of the map
    numXtiles = screenWidth/tileWidth;
    numYtiles = screenHeight/tileHeight;
    drawMap();

  }

  /**
  * Break the tile image into tiles
  */
  public void prepareTiles()
  {
    tiles = new Tile[numTiles];

    // Assume the tile images are arranged vertically
    for(int i = 0; i < numTiles; i++)
      tiles[i] = new Tile(tileImage, tileWidth, tileHeight, i);
  }

  /**
  * Draw a portion of the map
  */
  public void drawMap()
  {
    int curX = 0, curY = 0;
    int xInc, yInc;
    int xTile, yTile;

    // Calculate the starting matrix entry for the tiles
    xTile = mapX / tileWidth;
    yTile = mapY / tileHeight;

    // Calculate the tile increments
    xInc = mapX % tileWidth;
    yInc = mapY % tileHeight;

    // Last column or row in the map is not drawn unless the screen width or
    // height is divisible by the tile dimensions
    for(int i = 0; i <= numYtiles; i++){
      for(int j = 0; j <= numXtiles; j++){
        tiles[bgMap[yTile + i][xTile + j]].paint(screenGC, curX - xInc, curY - yInc);
        curX += tileWidth;
      }
      curY += tileHeight;
      curX = 0;
    }
    repaint();
  }

  /**
  * scroll the map up
  */
  public void scrollUp()
  {
    // First check to see if at the top of the map
    if((mapY - scrollInc) < 0)
      return;

    mapY -= scrollInc;
    drawMap();
  }

  /**
  * scroll the map down
  */
  public void scrollDown()
  {
    // First check to see if at the bottom of the map
    if((mapY + scrollInc + screenHeight) >= bgHeight)
      return;

    mapY += scrollInc;
    drawMap();
  }

  /**
  * scroll the map left
  */
  public void scrollLeft()
  {
    // First check to see if at the left of the map
    if((mapX - scrollInc) < 0)
      return;

    mapX -= scrollInc;
    drawMap();
  }

  /**
  * scroll the map right
  */
  public void scrollRight()
  {
    // First check to see if at the right of the map
    if((mapX + scrollInc + screenWidth) >= bgWidth)
      return;

    mapX += scrollInc;
    drawMap();
  }

  /**
  * scroll the map up and right
  */
  public void scrollUpRight()
  {
    // Check to see if at the top of the map
    if((mapY - scrollInc) >= 0)
      mapY -= scrollInc;

    // Check to see if at the right of the map
    if((mapX + scrollInc + screenWidth) < bgWidth)
      mapX += scrollInc;

    drawMap();
  }

  /**
  * scroll the map up and left
  */
  public void scrollUpLeft()
  {
    // Check to see if at the top of the map
    if((mapY - scrollInc) >= 0)
      mapY -= scrollInc;

    // Check to see if at the left of the map
    if((mapX - scrollInc) >= 0)
      mapX -= scrollInc;

    drawMap();
  }

  /**
  * scroll the map down and right
  */
  public void scrollDownRight()
  {
    // Check to see if at the bottom of the map
    if((mapY + scrollInc + screenHeight) < bgHeight)
      mapY += scrollInc;

    // Check to see if at the right of the map
    if((mapX + scrollInc + screenWidth) < bgWidth)
      mapX += scrollInc;

    drawMap();
  }

  /**
  * scroll the map down and left
  */
  public void scrollDownLeft()
  {
    // Check to see if at the bottom of the map
    if((mapY + scrollInc + screenHeight) < bgHeight)
      mapY += scrollInc;

    // Check to see if at the right of the map
    if((mapX - scrollInc) >= 0)
      mapX -= scrollInc;

    drawMap();
  }



  public void update(Graphics g)
  {
    paint(g);
  }

  public void paint(Graphics g)
  {
    g.drawImage(screenImage, 0, 0, null);
  }

  public void destroy()
  {
    screenGC.dispose();
  }
}
