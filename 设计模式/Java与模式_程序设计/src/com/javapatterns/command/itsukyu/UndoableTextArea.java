package com.javapatterns.command.itsukyu;

import java.util.Hashtable;

/**
 *	UndoableTextArea: An undoable extension of TextArea
 */
class UndoableTextArea extends TextArea implements StateEditable
{
    private final static String KEY_STATE = "UndoableTextAreaKey";  // hash key
    private boolean textChanged = false;
    private UndoManager undoManager;
    private StateEdit currentEdit;

    public UndoableTextArea()
    {
        super();
        initUndoable();
    } 
    public UndoableTextArea(String string)
    {
        super(string);
        initUndoable();
    } 
    public UndoableTextArea(int rows, int columns)
    {
        super(rows, columns);
        initUndoable();
    }
    public UndoableTextArea(String string, int rows, int columns)
    {
        super(string, rows, columns);
        initUndoable();
    } 
    public UndoableTextArea(String string, int rows, int columns, int scrollbars)
    {
        super(string, rows, columns, scrollbars);
        initUndoable();
    } 

    // method to undo last edit 
    public boolean undo()
    {
        try
        {
            undoManager.undo();
            return true;
        } 
        catch (CannotUndoException e)
        {
            System.out.println("Can't undo");
            return false;
        } 
    } 

    // method to redo last edit 
    public boolean redo()
    {
        try
        {
            undoManager.redo();
            return true;
        } 
        catch (CannotRedoException e)
        {
            System.out.println("Can't redo");
            return false;
        } 
    } 


    // Implementation of StateEditable interface
    //
    
    // save and restore data to/from hashtable
    public void storeState(Hashtable state)
    {
        state.put(KEY_STATE, getText());
    } 
    public void restoreState(Hashtable state)
    {
        Object data = state.get(KEY_STATE);
        if (data != null)
        {
            setText((String)data);
        } 
    } 

    // Snapshots current edit state
    private void takeSnapshot()
    {
        // snapshot only if text changed
        if (textChanged)
        {
            // end current edit and notify undo manager
            currentEdit.end();
            undoManager.addEdit(currentEdit);

            // reset text changed semaphore and create a new current edit
            textChanged = false;
            currentEdit = new StateEdit(this);
        } 
    } 

    // Helper method to initialize object to be undoable
    private void initUndoable ()
    {
        // create an undo manager to manage undo operations
        undoManager = new UndoManager();

        // create a StateEdit object to represent the current edit
        currentEdit = new StateEdit(this);

        // add listeners for various edit-related events
        // use these events to determine when to snapshot current edit

        // key listener looks for action keys (non-character keys)
        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent event)
            {
                if (event.isActionKey())
                {
                    // snapshot on any action keys
                    takeSnapshot();
                } 
            } 
        } );

        // focus listener looks for loss of focus
        addFocusListener(new FocusAdapter()
        {
            public void focusLost(FocusEvent event)
            {
                // snapshot when control loses focus
                takeSnapshot();
            } 
        } );

        // text listener looks for text changes
        addTextListener(new TextListener()
        {
            public void textValueChanged(TextEvent event)
            { 
                textChanged = true;
                
                // snapshot on every change to text
                // might be too granular to be practical
                //
                // this will shapshot every keystroke when typing
                takeSnapshot();
            } 
        } );
    } 
}

