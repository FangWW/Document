package com.javapatterns.observertimer.cursor;

import java.applet.Applet;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class RotatingCursorCompact extends Applet
{
	private Action updateCursorAction = new AbstractAction()
    {
		int ind = 0;

		public void actionPerformed(ActionEvent e)
        {
			if (ind == 0)
            {
				drawCursor(Cursor.NE_RESIZE_CURSOR);
            }
            else if (ind == 1)
            {
				drawCursor(Cursor.N_RESIZE_CURSOR);
            }
            else if (ind == 2)
            {
				drawCursor(Cursor.NW_RESIZE_CURSOR);
            }
            else if (ind == 3)
            {
				drawCursor(Cursor.W_RESIZE_CURSOR);
            }
            else if (ind == 4)
            {
				drawCursor(Cursor.SW_RESIZE_CURSOR);
            }
            else if (ind == 5)
            {
				drawCursor(Cursor.S_RESIZE_CURSOR);
            }
            else if (ind == 6)
            {
				drawCursor(Cursor.SE_RESIZE_CURSOR);
            }
            else if (ind == 7)
            {
				drawCursor(Cursor.E_RESIZE_CURSOR);
                ind = -1;
            }

            ind++;
		}
	};

	public void drawCursor(int cursorType)
    {
		setCursor(Cursor.getPredefinedCursor(cursorType));
    }

    public void init()
    {
		new Timer(300, updateCursorAction).start();
    }
}
