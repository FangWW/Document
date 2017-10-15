package com.javapatterns.prototype.cad;
// CadSymbolLoader
public class CadSymbolLoader
{
  private CadSymbolManager mgr;
  // constructor
  CadSymbolLoader (CadSymbolManager csm)
  {
    mgr = csm;
  }

  // load symbol libary
  int loadCadSymbols (String fname)
  {
    int symbolCount = 0;
    try
    {
      InputStream in;

      in = new FileInputStream(fname);

      in = new BufferedInputStream(in);

      ObjectInputStream oIn = new ObjectInputStream(in);

      while (true)
      { // loop until end of file

        Object c = oIn.readObject();

        if (c instanceof CadSymbol)
        {
          mgr.addSymbol((CadSymbol)c);
          symbolCount++;
        }
      }

    }
    catch (Exception e)
    {
  		// do nothing
    }
    return symbolCount;
  }
}
