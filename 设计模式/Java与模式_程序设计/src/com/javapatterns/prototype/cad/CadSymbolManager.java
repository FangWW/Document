package com.javapatterns.prototype.cad;
// CadSymbolManager

public class CadSymbolManager
{
  private Hashtable symbols = new Hashtable();
  // add symbol to collection

  void addSymbol(CadSymbol sym)
  {
    symbols.put(sym.getType(), sym);
  }

  // retrieve symbol by type
  CadSymbol getSymbol (String symbolType)
  {
    return (CadSymbol) ((CadSymbol)symbols.get(symbolType)).clone();
  }
}

