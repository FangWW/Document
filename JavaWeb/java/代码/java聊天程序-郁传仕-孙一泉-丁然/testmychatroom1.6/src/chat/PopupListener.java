package chat;

/**
 * �����˵������¼��Ĵ���
 */
class PopupListener extends MouseAdapter {
	JPopupMenu popup;//����һ�������ʾ�����˵�����
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
			//����Ӧ��λ����ʾ�˵����
    }
  }
}
