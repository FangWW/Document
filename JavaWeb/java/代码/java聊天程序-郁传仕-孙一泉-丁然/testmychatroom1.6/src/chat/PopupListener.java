package chat;

import javax.swing.*;
import java.awt.event.*;
/**
 * 弹出菜单触发事件的处理
 */
class PopupListener extends MouseAdapter {
	JPopupMenu popup;//如果右击监听显示下拉菜单内容
	PopupListener(JPopupMenu popupMenu) {
		popup = popupMenu;
	}
	public void mousePressed(MouseEvent e) {
		maybeShowPopup(e);
	}
	public void mouseReleased(MouseEvent e) {
		maybeShowPopup(e);
	}
	private void maybeShowPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popup.show(e.getComponent(), e.getX(), e.getY());
			//在相应的位置显示菜单组件
    }
  }
}
