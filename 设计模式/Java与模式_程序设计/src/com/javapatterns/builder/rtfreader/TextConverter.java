package com.javapatterns.builder.rtfreader;

abstract public class TextConverter
{
    public void convertCharacter(char c){ }

    public void convertParagraph(){ }

	public abstract ASCIIText getResult() ;
}
