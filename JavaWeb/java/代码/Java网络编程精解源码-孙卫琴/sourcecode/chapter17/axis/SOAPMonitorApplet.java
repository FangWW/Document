/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.apache.axis.monitor.SOAPMonitorConstants;

/**
 * This is a SOAP Mointor Applet class.  This class provides
 * the user interface for displaying data from the SOAP
 * monitor service.
 *
 * @author Brian Price (pricebe@us.ibm.com)
 *
 */
public class SOAPMonitorApplet extends JApplet {

    /**
     * Private data
     */
    private JPanel      main_panel = null;
    private JTabbedPane tabbed_pane = null;
    private int         port = 0;
    private Vector      pages = null;

    /**
     * Constructor
     */
    public SOAPMonitorApplet() {
    }

    /**
     * Applet initialization
     */
    public void init() {
        // Get the port to be used
        String port_str = getParameter("port");
        if (port_str != null) {
            port = Integer.parseInt(port_str);
        }
        // Try to use the system look and feel
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
        }
        // Create main panel to hold notebook
        main_panel = new JPanel();
        main_panel.setBackground(Color.white);
        main_panel.setLayout(new BorderLayout());
        setContentPane(main_panel);
        // Create the notebook
        tabbed_pane = new JTabbedPane(JTabbedPane.TOP);
        main_panel.add(tabbed_pane,BorderLayout.CENTER);
        // Add notebook page for default host connection
        pages = new Vector();
        addPage(new SOAPMonitorPage(getCodeBase().getHost()));
    }

    /**
     * Add a page to the notebook
     */
    private void addPage(SOAPMonitorPage pg) {
        tabbed_pane.addTab("  "+pg.getHost()+"  ", pg);
        pages.addElement(pg);
    }

    /** 
     * Applet is being displayed 
     */
    public void start() {
        // Tell all pages to start talking to the server
        Enumeration e = pages.elements();
        while (e.hasMoreElements()) {
            SOAPMonitorPage pg = (SOAPMonitorPage) e.nextElement();
            if (pg != null) {
                pg.start();
            }
        }
    }

    /*
     * Applet is no longer displayed
     */
    public void stop() {
        // Tell all pages to stop talking to the server
        Enumeration e = pages.elements();
        while (e.hasMoreElements()) {
            SOAPMonitorPage pg = (SOAPMonitorPage) e.nextElement();
            if (pg != null) {
                pg.stop();
            }
        }
    }

    /**
     * Applet cleanup
     */
    public void destroy() {
        tabbed_pane = null;
        main_panel = null;
    }

    /**
     * This class provides the contents of a notebook page
     * representing a server connection.
     */
    class SOAPMonitorPage extends JPanel 
                          implements Runnable,
                                     ListSelectionListener,
                                     ActionListener {

        /**
         * Status Strings
         */
        private final String STATUS_ACTIVE    = "The SOAP Monitor is started.";
        private final String STATUS_STOPPED   = "The SOAP Monitor is stopped.";
        private final String STATUS_CLOSED    = "The server communication has been terminated.";
        private final String STATUS_NOCONNECT = "The SOAP Monitor is unable to communcate with the server.";

        /**
         * Private data
         */
        private String                host = null;
        private Socket                socket = null;
        private ObjectInputStream     in = null;
        private ObjectOutputStream    out = null;
        private SOAPMonitorTableModel model = null;
        private JTable                table = null;
        private JScrollPane           scroll = null;
        private JPanel                list_panel = null;
        private JPanel                list_buttons = null;
        private JButton               remove_button = null;
        private JButton               remove_all_button = null;
        private JButton               filter_button = null;
        private JPanel                details_panel = null;
        private JPanel                details_header = null;
        private JSplitPane            details_soap = null;
        private JPanel                details_buttons = null;
        private JLabel                details_time = null;
        private JLabel                details_target = null;
        private JLabel                details_status = null;
        private JLabel                details_time_value = null;
        private JLabel                details_target_value = null;
        private JLabel                details_status_value = null;
        private EmptyBorder           empty_border = null;
        private EtchedBorder          etched_border = null;
        private JPanel                request_panel = null;
        private JPanel                response_panel = null;
        private JLabel                request_label = null;
        private JLabel                response_label = null;
        private SOAPMonitorTextArea   request_text = null;
        private SOAPMonitorTextArea   response_text = null;
        private JScrollPane           request_scroll = null;
        private JScrollPane           response_scroll = null;
        private JButton               layout_button = null;
        private JSplitPane            split = null;
        private JPanel                status_area = null;
        private JPanel                status_buttons = null;
        private JButton               start_button = null;
        private JButton               stop_button = null;
        private JLabel                status_text = null;
        private JPanel                status_text_panel = null;
        private SOAPMonitorFilter     filter = null;
        private GridBagLayout         details_header_layout = null;
        private GridBagConstraints    details_header_constraints = null;
        private JCheckBox             reflow_xml = null;

        /**
         * Constructor (create and layout page)
         */
        public SOAPMonitorPage(String host_name) {
            host = host_name;
            // Set up default filter (show all messages)
            filter = new SOAPMonitorFilter();
            // Use borders to help improve appearance
            etched_border = new EtchedBorder();
            // Build top portion of split (list panel) 
            model = new SOAPMonitorTableModel();
            table = new JTable(model);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setRowSelectionInterval(0,0);
            table.setPreferredScrollableViewportSize(new Dimension(600, 96));
            table.getSelectionModel().addListSelectionListener(this);
            scroll = new JScrollPane(table);
            remove_button = new JButton("Remove");
            remove_button.addActionListener(this);
            remove_button.setEnabled(false);
            remove_all_button = new JButton("Remove All");
            remove_all_button.addActionListener(this);
            filter_button = new JButton("Filter ...");
            filter_button.addActionListener(this);
            list_buttons = new JPanel();
            list_buttons.setLayout(new FlowLayout());
            list_buttons.add(remove_button);
            list_buttons.add(remove_all_button);
            list_buttons.add(filter_button);
            list_panel = new JPanel();
            list_panel.setLayout(new BorderLayout());
            list_panel.add(scroll,BorderLayout.CENTER);
            list_panel.add(list_buttons, BorderLayout.SOUTH);
            list_panel.setBorder(empty_border);
            // Build bottom portion of split (message details) 
            details_time = new JLabel("Time: ", SwingConstants.RIGHT);
            details_target = new JLabel("Target Service: ", SwingConstants.RIGHT);
            details_status = new JLabel("Status: ", SwingConstants.RIGHT);
            details_time_value = new JLabel();
            details_target_value = new JLabel();
            details_status_value = new JLabel();
            Dimension preferred_size = details_time.getPreferredSize();
            preferred_size.width = 1;
            details_time.setPreferredSize(preferred_size); 
            details_target.setPreferredSize(preferred_size); 
            details_status.setPreferredSize(preferred_size); 
            details_time_value.setPreferredSize(preferred_size); 
            details_target_value.setPreferredSize(preferred_size); 
            details_status_value.setPreferredSize(preferred_size);
            details_header = new JPanel();
            details_header_layout = new GridBagLayout();
            details_header.setLayout(details_header_layout);
            details_header_constraints = new GridBagConstraints();
            details_header_constraints.fill=GridBagConstraints.BOTH;
            details_header_constraints.weightx=0.5;
            details_header_layout.setConstraints(details_time,details_header_constraints);
            details_header.add(details_time);
            details_header_layout.setConstraints(details_time_value,details_header_constraints);
            details_header.add(details_time_value);
            details_header_layout.setConstraints(details_target,details_header_constraints);
            details_header.add(details_target);
            details_header_constraints.weightx=1.0;
            details_header_layout.setConstraints(details_target_value,details_header_constraints);
            details_header.add(details_target_value);
            details_header_constraints.weightx=.5;
            details_header_layout.setConstraints(details_status,details_header_constraints);
            details_header.add(details_status);
            details_header_layout.setConstraints(details_status_value,details_header_constraints);
            details_header.add(details_status_value);
            details_header.setBorder(etched_border);
            request_label = new JLabel("SOAP Request", SwingConstants.CENTER);
            request_text = new SOAPMonitorTextArea();
            request_text.setEditable(false);
            request_scroll = new JScrollPane(request_text);
            request_panel = new JPanel();
            request_panel.setLayout(new BorderLayout());
            request_panel.add(request_label, BorderLayout.NORTH);
            request_panel.add(request_scroll, BorderLayout.CENTER);
            response_label = new JLabel("SOAP Response", SwingConstants.CENTER);
            response_text = new SOAPMonitorTextArea();
            response_text.setEditable(false);
            response_scroll = new JScrollPane(response_text);
            response_panel = new JPanel();
            response_panel.setLayout(new BorderLayout());
            response_panel.add(response_label, BorderLayout.NORTH);
            response_panel.add(response_scroll, BorderLayout.CENTER);
            details_soap = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
            details_soap.setTopComponent(request_panel);
            details_soap.setRightComponent(response_panel);
            details_soap.setResizeWeight(.5);
            details_panel = new JPanel();
            layout_button = new JButton("Switch Layout");
            layout_button.addActionListener(this);
            reflow_xml = new JCheckBox("Reflow XML text");
            reflow_xml.addActionListener(this);
            details_buttons = new JPanel();
            details_buttons.setLayout(new FlowLayout());
            details_buttons.add(reflow_xml);
            details_buttons.add(layout_button);
            details_panel.setLayout(new BorderLayout());
            details_panel.add(details_header,BorderLayout.NORTH);
            details_panel.add(details_soap,BorderLayout.CENTER);
            details_panel.add(details_buttons,BorderLayout.SOUTH);
            details_panel.setBorder(empty_border);
            // Add the two parts to the age split pane
            split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            split.setTopComponent(list_panel);
            split.setRightComponent(details_panel);
            // Build status area
            start_button = new JButton("Start");
            start_button.addActionListener(this);
            stop_button = new JButton("Stop");
            stop_button.addActionListener(this);
            status_buttons = new JPanel();
            status_buttons.setLayout(new FlowLayout());
            status_buttons.add(start_button);
            status_buttons.add(stop_button);
            status_text = new JLabel();
            status_text.setBorder(new BevelBorder(BevelBorder.LOWERED));
            status_text_panel = new JPanel();
            status_text_panel.setLayout(new BorderLayout());
            status_text_panel.add(status_text, BorderLayout.CENTER);
            status_text_panel.setBorder(empty_border);
            status_area = new JPanel();
            status_area.setLayout(new BorderLayout());
            status_area.add(status_buttons, BorderLayout.WEST);
            status_area.add(status_text_panel, BorderLayout.CENTER);
            status_area.setBorder(etched_border);
            // Add the split and status area to page
            setLayout(new BorderLayout());
            add(split, BorderLayout.CENTER);
            add(status_area, BorderLayout.SOUTH);
        }

        /**
         * Get the name of the host we are displaying
         */
        public String getHost() {
            return host;
        }

        /**
         * Set the status text
         */
        public void setStatus(String txt) {
            status_text.setForeground(Color.black);
            status_text.setText("  "+txt);
        }

        /**
         * Set the status text to an error
         */
        public void setErrorStatus(String txt) {
            status_text.setForeground(Color.red);
            status_text.setText("  "+txt);
        }

        /**
         * Start talking to the server
         */
        public void start() {
            String codehost = getCodeBase().getHost();
            if (socket == null) {
                try {
                    // Open the socket to the server
                    socket = new Socket(codehost, port);
                    // Create output stream
                    out = new ObjectOutputStream(socket.getOutputStream());
                    out.flush();
                    // Create input stream and start background
                    // thread to read data from the server
                    in = new ObjectInputStream(socket.getInputStream());
                    new Thread(this).start();
                } catch (Exception e) {
                    // Exceptions here are unexpected, but we can't
                    // really do anything (so just write it to stdout
                    // in case someone cares and then ignore it)
                    System.out.println("Exception! "+e.toString());
                    e.printStackTrace();
                    setErrorStatus(STATUS_NOCONNECT);
                    socket = null;
                }
            } else {
                // Already started
            }
            if (socket != null) {
                // Make sure the right buttons are enabled
                start_button.setEnabled(false);
                stop_button.setEnabled(true);
                setStatus(STATUS_ACTIVE);
            }
        }

        /**
         * Stop talking to the server
         */
        public void stop() {
            if (socket != null) {
                // Close all the streams and socket
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ioe) {
                    }
                    out = null;
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ioe) {
                    }
                    in = null;
                }
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ioe) {
                    }
                    socket = null;
                }
            } else {
                // Already stopped
            }
            // Make sure the right buttons are enabled
            start_button.setEnabled(true);
            stop_button.setEnabled(false);
            setStatus(STATUS_STOPPED);
        }

        /**
         * Background thread used to receive data from
         * the server.
         */
        public void run() {
            Long            id;
            Integer         message_type;
            String          target;
            String          soap;
            SOAPMonitorData data;
            int             selected;
            int             row;
            boolean         update_needed;
            while (socket != null) {
                try {
                    // Get the data from the server
                    message_type = (Integer) in.readObject();
                    // Process the data depending on its type
                    switch (message_type.intValue()) {
                        case SOAPMonitorConstants.SOAP_MONITOR_REQUEST:
                            // Get the id, target and soap info
                            id = (Long) in.readObject();
                            target = (String) in.readObject();
                            soap = (String) in.readObject();
                            // Add new request data to the table
                            data = new SOAPMonitorData(id,target,soap);
                            model.addData(data);
                            // If "most recent" selected then update
                            // the details area if needed
                            selected = table.getSelectedRow();
                            if ((selected == 0) && model.filterMatch(data)) {
                                valueChanged(null);
                            }
                            break;
                        case SOAPMonitorConstants.SOAP_MONITOR_RESPONSE:
                            // Get the id and soap info
                            id = (Long) in.readObject();
                            soap = (String) in.readObject();
                            data = model.findData(id);
                            if (data != null) {
                                update_needed = false;
                                // Get the selected row
                                selected = table.getSelectedRow();
                                // If "most recent", then always
                                // update details area
                                if (selected == 0) {
                                    update_needed = true;
                                }
                                // If the data being updated is
                                // selected then update details
                                row = model.findRow(data);
                                if ((row != -1) && (row == selected)) {
                                    update_needed = true;
                                }
                                // Set the response and update table
                                data.setSOAPResponse(soap);
                                model.updateData(data);
                                // Refresh details area (if needed)
                                if (update_needed) {
                                    valueChanged(null);
                                }
                            }
                            break;
                    }

                } catch (Exception e) {
                    // Exceptions are expected here when the
                    // server communication has been terminated.
                    if (stop_button.isEnabled()) {
                        stop();
                        setErrorStatus(STATUS_CLOSED);
                    }
                }
            }
        }

        /**
         * Listener to handle table selection changes
         */
        public void valueChanged(ListSelectionEvent e) {
            int row = table.getSelectedRow();
            // Check if they selected a specific row
            if (row > 0) {
                remove_button.setEnabled(true);
            } else {
                remove_button.setEnabled(false);
            }
            // Check for "most recent" selection
            if (row == 0) {
                row = model.getRowCount() - 1;
                if (row == 0) {
                    row = -1;
                }
            }
            if (row == -1) {
                // Clear the details panel
                details_time_value.setText("");
                details_target_value.setText("");
                details_status_value.setText("");
                request_text.setText("");
                response_text.setText("");
            } else {
                // Show the details for the row
                SOAPMonitorData soap = model.getData(row);
                details_time_value.setText(soap.getTime());
                details_target_value.setText(soap.getTargetService());
                details_status_value.setText(soap.getStatus());
                if (soap.getSOAPRequest() == null) {
                    request_text.setText("");
                } else {
                    request_text.setText(soap.getSOAPRequest());
                    request_text.setCaretPosition(0);
                }
                if (soap.getSOAPResponse() == null) {
                    response_text.setText("");
                } else {
                    response_text.setText(soap.getSOAPResponse());
                    response_text.setCaretPosition(0);
                }
            }
        }

        /**
         * Listener to handle button actions
         */
        public void actionPerformed(ActionEvent e) {
            // Check if the user pressed the remove button
            if (e.getSource() == remove_button) {
                int row = table.getSelectedRow();
                model.removeRow(row);
                table.clearSelection();
                table.repaint();
                valueChanged(null);
            }
            // Check if the user pressed the remove all button
            if (e.getSource() == remove_all_button) {
                model.clearAll();
                table.setRowSelectionInterval(0,0);
                table.repaint();
                valueChanged(null);
            }
            // Check if the user pressed the filter button
            if (e.getSource() == filter_button) {
                filter.showDialog();
                if (filter.okPressed()) {
                    // Update the display with new filter
                    model.setFilter(filter);
                    table.repaint();
                }
            }
            // Check if the user pressed the start button
            if (e.getSource() == start_button) {
                start();
            }
            // Check if the user pressed the stop button
            if (e.getSource() == stop_button) {
                stop();
            }
            // Check if the user wants to switch layout
            if (e.getSource() == layout_button) {
                details_panel.remove(details_soap);
                details_soap.removeAll();
                if (details_soap.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                    details_soap = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
                } else {
                    details_soap = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
                }
                details_soap.setTopComponent(request_panel);
                details_soap.setRightComponent(response_panel);
                details_soap.setResizeWeight(.5);
                details_panel.add(details_soap, BorderLayout.CENTER);
                details_panel.validate();
                details_panel.repaint();
            }
            // Check if the user is changing the reflow option
            if (e.getSource() == reflow_xml) {
                request_text.setReflowXML(reflow_xml.isSelected());
                response_text.setReflowXML(reflow_xml.isSelected());
            }
        }
    }

    /**
     * This class represend the data for a SOAP request/response pair
     */
    class SOAPMonitorData {

        /**
         * Private data
         */
        private Long    id;
        private String  time;
        private String  target;
        private String  soap_request;
        private String  soap_response;

        /**
         * Constructor
         */
        public SOAPMonitorData(Long id, String target, String soap_request) {
            this.id = id;
            // A null id is used to signal that the "most recent" entry
            // is being created.
            if (id == null) {
                this.time = "Most Recent";
                this.target = "---";
                this.soap_request = null;
                this.soap_response = null;
            } else {
                this.time = DateFormat.getTimeInstance().format(new Date());
                this.target = target;
                this.soap_request = soap_request;
                this.soap_response = null;
            }
        }

        /**
         * Get the id for the SOAP message
         */
        public Long getId() {
            return id;
        }

        /**
         * Get the time the SOAP request was received by the applet
         */
        public String getTime() {
            return time;
        }

        /**
         * Get the SOAP request target service name
         */
        public String getTargetService() {
            return target;
        }

        /**
         * Get the status of the request
         */
        public String getStatus() {
            String status = "---";
            if (id != null) {
                status = "Complete";
                if (soap_response == null) {
                    status = "Active";
                }
            }
            return status;
        }

        /**
         * Get the request SOAP contents
         */
        public String getSOAPRequest() {
            return soap_request;
        }

        /**
         * Set the resposne SOAP contents
         */
        public void setSOAPResponse(String response) {
            soap_response = response;
        }

        /**
         * Get the response SOAP contents
         */
        public String getSOAPResponse() {
            return soap_response;
        }
    }

    /**
     * This table model is used to manage the table displayed
     * at the top of the page to show all the SOAP messages
     * we have received and to control which message details are
     * to be displayed on the bottom of the page.
     */
    class SOAPMonitorTableModel extends AbstractTableModel {

        /**
         * Column titles
         */
        private final String[] column_names = { "Time",
                                                "Target Service",
                                                "Status" };
        /**                                        
         * Private data
         */
        private Vector  data;
        private Vector  filter_include;
        private Vector  filter_exclude;
        private boolean filter_active;
        private boolean filter_complete;
        private Vector  filter_data;

        /**
         * Constructor
         */
        public SOAPMonitorTableModel() {
            data = new Vector();
            // Add "most recent" entry to top of table
            SOAPMonitorData soap = new SOAPMonitorData(null,null,null);
            data.addElement(soap);
            filter_include = null;
            filter_exclude = null;
            filter_active = false;
            filter_complete = false;
            filter_data = null;
            // By default, exclude NotificationService and
            // EventViewerService messages
            filter_exclude = new Vector();
            filter_exclude.addElement("NotificationService");
            filter_exclude.addElement("EventViewerService");
            filter_data = new Vector();
            filter_data.addElement(soap);
        }

        /**
         * Get column count (part of table model interface)
         */
        public int getColumnCount() {
            return column_names.length;
        }
        
        /**
         * Get row count (part of table model interface)
         */
        public int getRowCount() {
            int count = data.size();
            if (filter_data != null) {
                count = filter_data.size();
            }
            return count;
        }

        /**
         * Get column name (part of table model interface)
         */
        public String getColumnName(int col) {
            return column_names[col];
        }

        /**
         * Get value at (part of table model interface)
         */
        public Object getValueAt(int row, int col) {
            SOAPMonitorData soap;
            String          value = null;
            soap = (SOAPMonitorData) data.elementAt(row);
            if (filter_data != null) {
                soap = (SOAPMonitorData) filter_data.elementAt(row);
            }
            switch (col) {
                case 0:
                    value = soap.getTime();
                    break;
                case 1:
                    value = soap.getTargetService();
                    break;
                case 2:
                    value = soap.getStatus();
                    break;
            }
            return value;
        }

        /**
         * Check if soap data matches filter 
         */
        public boolean filterMatch(SOAPMonitorData soap) {
            boolean match = true;
            if (filter_include != null) {
                // Check for service match
                Enumeration e = filter_include.elements();
                match = false;
                while (e.hasMoreElements() && !match) {
                    String service = (String) e.nextElement();
                    if (service.equals(soap.getTargetService())) {
                        match = true;
                    }
                }
            }
            if (filter_exclude != null) {
                // Check for service match
                Enumeration e = filter_exclude.elements();
                while (e.hasMoreElements() && match) {
                    String service = (String) e.nextElement();
                    if (service.equals(soap.getTargetService())) {
                        match = false;
                    }
                }
            }
            if (filter_active) {
                // Check for active status match
                if (soap.getSOAPResponse() != null) {
                    match = false;
                }
            }
            if (filter_complete) {
                // Check for complete status match
                if (soap.getSOAPResponse() == null) {
                    match = false;
                }
            }
            // The "most recent" is always a match
            if (soap.getId() == null) {
                match = true;
            }
            return match;
        }

        /**
         * Add data to the table as a new row
         */
        public void addData(SOAPMonitorData soap) {
            int row = data.size();
            data.addElement(soap);
            if (filter_data != null) {
                if (filterMatch(soap)) {
                    row = filter_data.size();
                    filter_data.addElement(soap);
                    fireTableRowsInserted(row,row);
                }
            } else {
                fireTableRowsInserted(row,row);
            }
        }

        /**
         * Find the data for a given id
         */
        public SOAPMonitorData findData(Long id) {
            SOAPMonitorData soap = null;
            for (int row=data.size(); (row > 0) && (soap == null); row--) {
                soap = (SOAPMonitorData) data.elementAt(row-1);
                if (soap.getId().longValue() != id.longValue()) {
                    soap = null;
                }
            }
            return soap;
        }

        /**
         * Find the row in the table for a given message id
         */
        public int findRow(SOAPMonitorData soap) {
            int row = -1;
            if (filter_data != null) {
                row = filter_data.indexOf(soap);
            } else {
                row = data.indexOf(soap);
            }
            return row;
        }

        /**
         * Remove all messages from the table (but leave "most recent")
         */
        public void clearAll() {
            int last_row = data.size() - 1;
            if (last_row > 0) {
                data.removeAllElements();
                SOAPMonitorData soap = new SOAPMonitorData(null,null,null);
                data.addElement(soap);
                if (filter_data != null) {
                    filter_data.removeAllElements();
                    filter_data.addElement(soap);
                }
                fireTableDataChanged();
            }
        }

        /**
         * Remove a message from the table
         */
        public void removeRow(int row) {
            SOAPMonitorData soap = null;
            if (filter_data == null) {
                soap = (SOAPMonitorData) data.elementAt(row);
                data.remove(soap);
            } else {
                soap = (SOAPMonitorData) filter_data.elementAt(row);
                filter_data.remove(soap);
                data.remove(soap);
            }         
            fireTableRowsDeleted(row,row);
        }

        /**
         * Set a new filter
         */
        public void setFilter(SOAPMonitorFilter filter) {
            // Save new filter criteria
            filter_include = filter.getFilterIncludeList();
            filter_exclude = filter.getFilterExcludeList();
            filter_active = filter.getFilterActive();
            filter_complete = filter.getFilterComplete();
            applyFilter();
        }

        /**
         * Refilter the list of messages
         */
        public void applyFilter() {
            // Re-filter using new criteria
            filter_data = null;
            if ((filter_include != null) || 
                (filter_exclude != null) ||
                 filter_active || filter_complete ) {
                filter_data = new Vector();
                Enumeration e = data.elements();
                SOAPMonitorData soap;
                while (e.hasMoreElements()) {
                    soap = (SOAPMonitorData) e.nextElement();
                    if (filterMatch(soap)) {
                        filter_data.addElement(soap);
                    }
                }
            }
            fireTableDataChanged();
        }

        /**
         * Get the data for a row
         */
        public SOAPMonitorData getData(int row) {
            SOAPMonitorData soap = null;
            if (filter_data == null) {
                soap = (SOAPMonitorData) data.elementAt(row);
            } else {
                soap = (SOAPMonitorData) filter_data.elementAt(row);
            }
            return soap;
        }

        /**
         * Update a message
         */
        public void updateData (SOAPMonitorData soap) {
           int row;
           if (filter_data == null) {
               // No filter, so just fire table updated
               row = data.indexOf(soap);
               if (row != -1) {
                   fireTableRowsUpdated(row,row);
               }
           } else {
               // Check if the row was being displayed
               row = filter_data.indexOf(soap);
               if (row == -1) {
                   // Row was not displayed, so check for if it
                   // now needs to be displayed
                   if (filterMatch(soap)) {
                       int index = -1;
                       row = data.indexOf(soap) + 1;
                       while ((row < data.size()) && (index == -1)) {
                           index = filter_data.indexOf(data.elementAt(row));
                           if (index != -1) {                   
                               // Insert at this location
                               filter_data.add(index,soap);
                           }
                           row++;
                       }
                       if (index == -1) {
                           // Insert at end
                           index = filter_data.size();
                           filter_data.addElement(soap);
                       }
                       fireTableRowsInserted(index,index);
                   }
               } else {
                   // Row was displayed, so check if it needs to
                   // be updated or removed
                   if (filterMatch(soap)) {
                       fireTableRowsUpdated(row,row);
                   } else {
                       filter_data.remove(soap);
                       fireTableRowsDeleted(row,row);
                   }
               }
           }
        }

    }

    /**
     * Panel with checkbox and list
     */
    class ServiceFilterPanel extends JPanel 
                             implements ActionListener,
                                        ListSelectionListener,
                                        DocumentListener {

        private JCheckBox    service_box = null;
        private Vector       filter_list = null;
        private Vector       service_data = null;
        private JList        service_list = null;
        private JScrollPane  service_scroll = null;
        private JButton      remove_service_button = null;
        private JPanel       remove_service_panel = null;
        private EmptyBorder  indent_border = null;
        private EmptyBorder  empty_border = null;
        private JPanel       service_area = null;
        private JPanel       add_service_area = null;
        private JTextField   add_service_field = null;
        private JButton      add_service_button = null;
        private JPanel       add_service_panel = null;

        /**
         * Constructor
         */
        public ServiceFilterPanel(String text, Vector list) {
            empty_border = new EmptyBorder(5,5,0,5);
            indent_border = new EmptyBorder(5,25,5,5);
            service_box = new JCheckBox(text);
            service_box.addActionListener(this);
            service_data = new Vector();
            if (list != null) {
                service_box.setSelected(true);
                service_data = (Vector) list.clone();
            }
            service_list = new JList(service_data);
            service_list.setBorder(new EtchedBorder());
            service_list.setVisibleRowCount(5);
            service_list.addListSelectionListener(this);
            service_list.setEnabled(service_box.isSelected());
            service_scroll = new JScrollPane(service_list);
            service_scroll.setBorder(new EtchedBorder());
            remove_service_button = new JButton("Remove");
            remove_service_button.addActionListener(this);
            remove_service_button.setEnabled(false);
            remove_service_panel = new JPanel();
            remove_service_panel.setLayout(new FlowLayout());
            remove_service_panel.add(remove_service_button);
            service_area = new JPanel();
            service_area.setLayout(new BorderLayout());
            service_area.add(service_scroll, BorderLayout.CENTER);
            service_area.add(remove_service_panel, BorderLayout.EAST);
            service_area.setBorder(indent_border);
            add_service_field = new JTextField();
            add_service_field.addActionListener(this);
            add_service_field.getDocument().addDocumentListener(this);
            add_service_field.setEnabled(service_box.isSelected());
            add_service_button = new JButton("Add");
            add_service_button.addActionListener(this);
            add_service_button.setEnabled(false);
            add_service_panel = new JPanel();
            add_service_panel.setLayout(new BorderLayout());
            JPanel dummy = new JPanel();
            dummy.setBorder(empty_border);
            add_service_panel.add(dummy, BorderLayout.WEST);
            add_service_panel.add(add_service_button, BorderLayout.EAST);
            add_service_area = new JPanel();
            add_service_area.setLayout(new BorderLayout());
            add_service_area.add(add_service_field, BorderLayout.CENTER);
            add_service_area.add(add_service_panel, BorderLayout.EAST);
            add_service_area.setBorder(indent_border);
            setLayout(new BorderLayout());
            add(service_box, BorderLayout.NORTH);
            add(service_area, BorderLayout.CENTER);
            add(add_service_area, BorderLayout.SOUTH);
            setBorder(empty_border);
        }

        /**
         * Get the current list of services
         */
        public Vector getServiceList() {
            Vector list = null;
            if (service_box.isSelected()) {
                list = service_data;
            }
            return list;
        }

        /**
         * Listener to handle button actions
         */
        public void actionPerformed(ActionEvent e) {
            // Check if the user changed the service filter option
            if (e.getSource() == service_box) {
                service_list.setEnabled(service_box.isSelected());
                service_list.clearSelection();
                remove_service_button.setEnabled(false);
                add_service_field.setEnabled(service_box.isSelected());
                add_service_field.setText("");
                add_service_button.setEnabled(false);
            }
            // Check if the user pressed the add service button
            if ((e.getSource() == add_service_button) ||
                (e.getSource() == add_service_field)) {
                String text = add_service_field.getText();
                if ((text != null) && (text.length() > 0)) {
                    service_data.addElement(text);
                    service_list.setListData(service_data);
                }
                add_service_field.setText("");
                add_service_field.requestFocus();
            }
            // Check if the user pressed the remove service button
            if (e.getSource() == remove_service_button) {
                Object[] sels = service_list.getSelectedValues();
                for (int i=0; i<sels.length; i++) {
                    service_data.removeElement(sels[i]);
                }
                service_list.setListData(service_data);
                service_list.clearSelection();
            }
        }

        /**
         * Handle changes to the text field
         */
        public void changedUpdate(DocumentEvent e) {
            String text = add_service_field.getText();
            if ((text != null) && (text.length() > 0)) {
                add_service_button.setEnabled(true);
            } else {
                add_service_button.setEnabled(false);
            }
        }

        /**
         * Handle changes to the text field
         */
        public void insertUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        /**
         * Handle changes to the text field
         */
        public void removeUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        /**
         * Listener to handle service list selection changes
         */
        public void valueChanged(ListSelectionEvent e) {
            if (service_list.getSelectedIndex() == -1) {
                remove_service_button.setEnabled(false);
            } else {
                remove_service_button.setEnabled(true);
            }
        }
    }

    /**
     * Class for showing the filter dialog
     */
    class SOAPMonitorFilter implements ActionListener {

        /**
         * Private data
         */
        private JDialog            dialog = null;
        private JPanel             panel = null;
        private JPanel             buttons = null;
        private JButton            ok_button = null;
        private JButton            cancel_button = null;
        private ServiceFilterPanel include_panel = null;
        private ServiceFilterPanel exclude_panel = null;
        private JPanel             status_panel = null;
        private JCheckBox          status_box = null;
        private EmptyBorder  empty_border = null;
        private EmptyBorder  indent_border = null;
        private JPanel             status_options = null;
        private ButtonGroup        status_group = null;
        private JRadioButton       status_active = null;
        private JRadioButton       status_complete = null;
        private Vector             filter_include_list = null;
        private Vector             filter_exclude_list = null;
        private boolean            filter_active = false;
        private boolean            filter_complete = false;
        private boolean            ok_pressed = false;

        /**
         * Constructor
         */
        public SOAPMonitorFilter() {
            // By default, exclude NotificationService and
            // EventViewerService messages
            filter_exclude_list = new Vector();
            filter_exclude_list.addElement("NotificationService");
            filter_exclude_list.addElement("EventViewerService");
        }

        /**
         * Get list of services to be included
         */
        public Vector getFilterIncludeList() {
            return filter_include_list;
        }

        /**
         * Get list of services to be excluded
         */
        public Vector getFilterExcludeList() {
            return filter_exclude_list;
        }

        /**
         * Check if filter active messages
         */
        public boolean getFilterActive() {
            return filter_active;
        }

        /**
         * Check if filter complete messages
         */
        public boolean getFilterComplete() {
            return filter_complete;
        }

        /**
         * Show the filter dialog
         */
        public void showDialog() {
            empty_border = new EmptyBorder(5,5,0,5);
            indent_border = new EmptyBorder(5,25,5,5);
            include_panel = new ServiceFilterPanel("Include messages based on target service:",
                                                   filter_include_list);
            exclude_panel = new ServiceFilterPanel("Exclude messages based on target service:",
                                                   filter_exclude_list);
            status_box = new JCheckBox("Filter messages based on status:");
            status_box.addActionListener(this);
            status_active = new JRadioButton("Active messages only");
            status_active.setSelected(true);
            status_active.setEnabled(false);
            status_complete = new JRadioButton("Complete messages only");
            status_complete.setEnabled(false);
            status_group = new ButtonGroup();
            status_group.add(status_active);
            status_group.add(status_complete);
            if (filter_active || filter_complete) {
                status_box.setSelected(true);
                status_active.setEnabled(true);
                status_complete.setEnabled(true);
                if (filter_complete) {
                    status_complete.setSelected(true);
                }
            }
            status_options = new JPanel();
            status_options.setLayout(new BoxLayout(status_options, BoxLayout.Y_AXIS));
            status_options.add(status_active);
            status_options.add(status_complete);
            status_options.setBorder(indent_border);
            status_panel = new JPanel();
            status_panel.setLayout(new BorderLayout());
            status_panel.add(status_box, BorderLayout.NORTH);
            status_panel.add(status_options, BorderLayout.CENTER);
            status_panel.setBorder(empty_border);
            ok_button = new JButton("Ok");
            ok_button.addActionListener(this);
            cancel_button = new JButton("Cancel");
            cancel_button.addActionListener(this);
            buttons = new JPanel();
            buttons.setLayout(new FlowLayout());
            buttons.add(ok_button);
            buttons.add(cancel_button);
            panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(include_panel);
            panel.add(exclude_panel);
            panel.add(status_panel);
            panel.add(buttons);
            dialog = new JDialog();
            dialog.setTitle("SOAP Monitor Filter");
            dialog.setContentPane(panel);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setModal(true);
            dialog.pack();
            Dimension d = dialog.getToolkit().getScreenSize();
            dialog.setLocation((d.width-dialog.getWidth())/2,
                               (d.height-dialog.getHeight())/2);
            ok_pressed = false;
            dialog.show();
        }

        /**
         * Listener to handle button actions
         */
        public void actionPerformed(ActionEvent e) {
            // Check if the user pressed the ok button
            if (e.getSource() == ok_button) {
                filter_include_list = include_panel.getServiceList();
                filter_exclude_list = exclude_panel.getServiceList();
                if (status_box.isSelected()) {
                    filter_active = status_active.isSelected();
                    filter_complete = status_complete.isSelected();
                } else {
                    filter_active = false;
                    filter_complete = false;
                }
                ok_pressed = true;
                dialog.dispose();
            }
            // Check if the user pressed the cancel button
            if (e.getSource() == cancel_button) {
                dialog.dispose();
            }
            // Check if the user changed the status filter option
            if (e.getSource() == status_box) {
                status_active.setEnabled(status_box.isSelected());
                status_complete.setEnabled(status_box.isSelected());
            }
        }

        /**
         * Check if the user pressed the ok button
         */
        public boolean okPressed() {
            return ok_pressed;
        }
    }

    /**
     * Text panel class that supports XML reflow
     */
    class SOAPMonitorTextArea extends JTextArea {

        /**
         * Private data
         */
        private boolean format = false;
        private String  original = "";
        private String  formatted = null; 

        /**
         * Constructor
         */
        public SOAPMonitorTextArea() {
        }

        /** 
         * Override setText to do formatting
         */
        public void setText(String text) {
            original = text;
            formatted = null;
            if (format) {
                doFormat();
                super.setText(formatted);
            } else {
                super.setText(original);
            }
        }

        /**
         * Turn reflow on or off
         */
        public void setReflowXML(boolean reflow) {
            format = reflow;
            if (format) {
                if (formatted == null) {
                    doFormat();
                }
                super.setText(formatted);
            } else {
                super.setText(original);
            }
        }

        /**
         * Reflow XML
         */
        public void doFormat() {
            Vector       parts = new Vector();
            char[]       chars = original.toCharArray();
            int          index = 0;
            int          first = 0;
            String       part = null;
            while (index < chars.length) {
                // Check for start of tag
                if (chars[index] == '<') {
                    // Did we have data before this tag?
                    if (first < index) {
                        part = new String(chars,first,index-first);
                        part = part.trim();
                        // Save non-whitespace data
                        if (part.length() > 0) {
                            parts.addElement(part);
                        }
                    }
                    // Save the start of tag
                    first = index;
                }
                // Check for end of tag
                if (chars[index] == '>') {
                    // Save the tag
                    part = new String(chars,first,index-first+1);
                    parts.addElement(part);
                    first = index+1;
                }
                // Check for end of line
                if ((chars[index] == '\n') || (chars[index] == '\r')) {
                    // Was there data on this line?
                    if (first < index) {
                        part = new String(chars,first,index-first);
                        part = part.trim();
                        // Save non-whitespace data
                        if (part.length() > 0) {
                            parts.addElement(part);
                        }
                    }
                    first = index+1;
                }
                index++;
            }
            // Reflow as XML
            StringBuffer buf = new StringBuffer();
            Object[] list = parts.toArray();
            int indent = 0;
            int pad = 0;
            index = 0;
            while (index < list.length) {
                part = (String) list[index];
                if (buf.length() == 0) {
                    // Just add first tag (should be XML header)
                    buf.append(part);
                } else {
                    // All other parts need to start on a new line
                    buf.append('\n');
                    // If we're at an end tag then decrease indent
                    if (part.startsWith("</")) {
                        indent--;
                    }            
                    // Add any indent
                    for (pad = 0; pad < indent; pad++) {
                        buf.append("  ");
                    }
                    // Add the tag or data
                    buf.append(part);
                    // If this is a start tag then increase indent
                    if (part.startsWith("<") &&
                        !part.startsWith("</") &&
                        !part.endsWith("/>")) {
                        indent++;
                        // Check for special <tag>data</tag> case
                        if ((index + 2) < list.length) {
                            part = (String) list[index+2];
                            if (part.startsWith("</")) {
                                part = (String) list[index+1];
                                if (!part.startsWith("<")) {
                                    buf.append(part);
                                    part = (String) list[index+2];
                                    buf.append(part);
                                    index = index + 2;
                                    indent--;
                                }
                            }
                        }
                    }
                }
                index++;
            }
            formatted = new String(buf);
        }
    }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
