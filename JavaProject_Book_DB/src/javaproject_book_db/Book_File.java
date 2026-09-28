/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package javaproject_book_db;

import java.awt.Dimension;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author stephanie
 */
public class Book_File extends javax.swing.JPanel {
        Connection conn;
    /**
     * Creates new form Book_File
     */
    public Book_File() {
        initComponents();
        initConnection();
        showGUI();
    }
    
    private void showGUI() {
        //JOptionPane.showMessageDialog(null, conn);  // to test the connection UPDATE: I didn't include this anymore.
        JFrame jf = new JFrame("Book File"); 
        jf.add(this); 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        jf.pack(); 
        jf.setLocationRelativeTo(null); 
        jf.setVisible(true); 
    }

    private void booklistTABLE1() {

        try { 
                String query = "SELECT * FROM view_all_books"; 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(query);

                DefaultTableModel model = new DefaultTableModel( 
                        new String[]{"Book ID", "Title", "Author", "Description", "Genre", "Price", "Availability"}, 0); 

                while (rs.next()) { 
                        model.addRow(new Object[] { 
                        rs.getString("book_id"), 
                        rs.getString("title"), 
                        rs.getString("author"), 
                        rs.getString("description"),
                        rs.getString("genre"),
                        rs.getDouble("price"),
                        rs.getString("avail_notavail")
                        } ); 
                }   

                JTable table = new JTable(model); 

                JFrame frame = new JFrame("Book List"); 
                frame.add(new JScrollPane(table)); 
                frame.setSize(1000, 800);
                frame.setLocationRelativeTo(null); 
                frame.setVisible(true); 
                rs.close(); 
                stmt.close(); 

        } catch (SQLException e) { 
                JOptionPane.showMessageDialog(null, "Error loading data!"); 
                e.printStackTrace(); 
        }
    }  

    private void booklistTABLE2() {
        try {
            String query = "SELECT * FROM view_books_1500 ORDER BY book_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Book ID", "Title", "Author", "Description", "Price", "Availability", "Genre"}, 0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("avail_notavail"),
                    rs.getString("genre")
                });
            }

            JTable table = new JTable(model);
            JFrame frame = new JFrame("Books > 1500");
            frame.add(new JScrollPane(table));
            frame.setSize(1000, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading Books > 1500: " + e.getMessage());
        }
    }

    private void searchBookByID() {
        String inputID = JOptionPane.showInputDialog("Enter Book ID to search:");

        if (inputID == null || inputID.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Book ID.");
            return;
        }

        try {
            String sql = "SELECT * FROM search_book(?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(inputID));

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JTextArea textArea = new JTextArea(
                    "Book Found!\n\n" +
                    "Title: " + rs.getString("title") +
                    "\nAuthor: " + rs.getString("author") +
                    "\nDescription: " + rs.getString("description") +
                    "\nGenre: " + rs.getString("genre") +
                    "\nPrice: " + rs.getDouble("price") +
                    "\nAvailability: " + rs.getString("avail_notavail") 
                );

                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);

                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(400, 250));

                JOptionPane.showMessageDialog(null, scrollPane, "Book Details", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Sorry! No record found.");
            }

            rs.close();
            pst.close();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Book ID. Please enter a number.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Search failed: " + e.getMessage());
        }
    }    
    
    private void initConnection() {
            try {
                    //String url = ("jdbc:postgresql://localhost:5432/Book_DB; create=true; password=iheartdata1004");
                    //conn = DriverManager.getConnection(url); Note: Alternative

                    conn = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/Book_DB",
                            "postgres",
                            "iheartdata1004"
    );
                    if (conn != null && !conn.isClosed()) {
                            JOptionPane.showMessageDialog(null, "Database Connected Successfully!");
                    }

            } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Database Connection Failed!");
                    e.printStackTrace(System.err);
            }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        bookdb = new javax.swing.JLabel();
        lbltitle = new javax.swing.JLabel();
        lbldesc = new javax.swing.JLabel();
        lblauth = new javax.swing.JLabel();
        lblpri = new javax.swing.JLabel();
        lblbid = new javax.swing.JLabel();
        title = new javax.swing.JTextField();
        description = new javax.swing.JTextField();
        author = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        bookid = new javax.swing.JTextField();
        avail = new javax.swing.JRadioButton();
        notavail = new javax.swing.JRadioButton();
        save = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        krus = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        notall = new javax.swing.JButton();
        allbooks = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        lbltitle1 = new javax.swing.JLabel();
        genre = new javax.swing.JTextField();
        search = new javax.swing.JButton();

        setBackground(new java.awt.Color(229, 218, 235));

        bookdb.setFont(new java.awt.Font("Parchment", 1, 95)); // NOI18N
        bookdb.setLabelFor(bookdb);
        bookdb.setText("Book Database");

        lbltitle.setFont(new java.awt.Font("Blackadder ITC", 0, 30)); // NOI18N
        lbltitle.setLabelFor(lbltitle);
        lbltitle.setText("Title");

        lbldesc.setFont(new java.awt.Font("Blackadder ITC", 0, 30)); // NOI18N
        lbldesc.setLabelFor(lbldesc);
        lbldesc.setText("Description");

        lblauth.setFont(new java.awt.Font("Blackadder ITC", 0, 30)); // NOI18N
        lblauth.setLabelFor(lblauth);
        lblauth.setText("Author");

        lblpri.setFont(new java.awt.Font("Blackadder ITC", 0, 30)); // NOI18N
        lblpri.setLabelFor(lblpri);
        lblpri.setText("Price");

        lblbid.setFont(new java.awt.Font("Blackadder ITC", 0, 30)); // NOI18N
        lblbid.setLabelFor(lblbid);
        lblbid.setText("Book ID");

        title.setBackground(new java.awt.Color(255, 249, 253));
        title.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        title.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 69, 98)));
        title.addActionListener(this::titleActionPerformed);

        description.setBackground(new java.awt.Color(255, 249, 253));
        description.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        description.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 69, 98)));
        description.addActionListener(this::descriptionActionPerformed);

        author.setBackground(new java.awt.Color(255, 249, 253));
        author.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        author.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 69, 98)));

        price.setBackground(new java.awt.Color(255, 249, 253));
        price.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 69, 98)));

        bookid.setBackground(new java.awt.Color(255, 249, 253));
        bookid.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        bookid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 69, 98)));
        bookid.addActionListener(this::bookidActionPerformed);

        avail.setBackground(new java.awt.Color(229, 218, 235));
        buttonGroup1.add(avail);
        avail.setFont(new java.awt.Font("Baskerville Old Face", 0, 15)); // NOI18N
        avail.setText("Available");
        avail.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        notavail.setBackground(new java.awt.Color(229, 218, 235));
        buttonGroup1.add(notavail);
        notavail.setFont(new java.awt.Font("Baskerville Old Face", 0, 15)); // NOI18N
        notavail.setText("Not Available");
        notavail.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        save.setBackground(new java.awt.Color(225, 213, 221));
        save.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        save.setForeground(new java.awt.Color(102, 102, 102));
        save.setText("Save");
        save.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        save.addActionListener(this::saveActionPerformed);

        exit.setBackground(new java.awt.Color(132, 111, 132));
        exit.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("Exit");
        exit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exit.addActionListener(this::exitActionPerformed);

        clear.setBackground(new java.awt.Color(225, 213, 221));
        clear.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        clear.setForeground(new java.awt.Color(102, 102, 102));
        clear.setText("Clear");
        clear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clear.addActionListener(this::clearActionPerformed);

        krus.setIcon(new javax.swing.ImageIcon("C:\\Users\\stephanie\\Downloads\\cgc3.png")); // NOI18N

        update.setBackground(new java.awt.Color(225, 213, 221));
        update.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        update.setForeground(new java.awt.Color(102, 102, 102));
        update.setText("Update");
        update.setToolTipText("");
        update.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        update.addActionListener(this::updateActionPerformed);

        notall.setBackground(new java.awt.Color(225, 213, 221));
        notall.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        notall.setForeground(new java.awt.Color(102, 102, 102));
        notall.setText("> 1500");
        notall.setToolTipText("");
        notall.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        notall.addActionListener(this::notallActionPerformed);

        allbooks.setBackground(new java.awt.Color(225, 213, 221));
        allbooks.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        allbooks.setForeground(new java.awt.Color(102, 102, 102));
        allbooks.setText("All Books");
        allbooks.setToolTipText("");
        allbooks.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        allbooks.addActionListener(this::allbooksActionPerformed);

        delete.setBackground(new java.awt.Color(225, 213, 221));
        delete.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        delete.setForeground(new java.awt.Color(102, 102, 102));
        delete.setText("Delete");
        delete.setToolTipText("");
        delete.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        delete.addActionListener(this::deleteActionPerformed);

        lbltitle1.setFont(new java.awt.Font("Blackadder ITC", 0, 30)); // NOI18N
        lbltitle1.setLabelFor(lbltitle);
        lbltitle1.setText("Genre");

        genre.setBackground(new java.awt.Color(255, 249, 253));
        genre.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        genre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(98, 69, 98)));
        genre.addActionListener(this::genreActionPerformed);

        search.setBackground(new java.awt.Color(225, 213, 221));
        search.setFont(new java.awt.Font("Baskerville Old Face", 0, 18)); // NOI18N
        search.setForeground(new java.awt.Color(153, 153, 153));
        search.setText("Search Book ID");
        search.setToolTipText("");
        search.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        search.addActionListener(this::searchActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lbldesc))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblauth))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblbid))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lbltitle))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblpri))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lbltitle1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(avail)
                                .addGap(103, 103, 103)
                                .addComponent(notavail)
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(description, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(author, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(price, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bookid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(genre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bookdb))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allbooks, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(notall, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(645, 645, 645)
                        .addComponent(krus, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(bookdb)
                .addGap(31, 31, 31)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(genre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(bookid, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(avail)
                    .addComponent(notavail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notall, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allbooks, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(lbltitle)
                        .addGap(18, 18, 18)
                        .addComponent(lblauth)
                        .addGap(18, 18, 18)
                        .addComponent(lbldesc)
                        .addGap(18, 18, 18)
                        .addComponent(lbltitle1)
                        .addGap(18, 18, 18)
                        .addComponent(lblpri)
                        .addGap(18, 18, 18)
                        .addComponent(lblbid))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(krus, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit? :o", "Exit System...",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
                      JOptionPane.showMessageDialog(null, "You selected: Yes... Goodbye!");
                      JOptionPane.showMessageDialog(null, "Thank you for using the system! :*]");
                      System.exit(0);
        } else {
                JOptionPane.showMessageDialog(null, "You selected: No... YAY!");
        }       
    }//GEN-LAST:event_exitActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(
            null,
            "Wait! Are you sure you want to clear all fields? :o",
            "Confirm Clear",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            title.setText("");
            description.setText("");
            author.setText("");
            price.setText("");
            bookid.setText("");
            genre.setText("");
            buttonGroup1.clearSelection();
            JOptionPane.showMessageDialog(null, "Items were cleared!");
        } else {
            JOptionPane.showMessageDialog(null, "Clear action canceled.");
        }
    }//GEN-LAST:event_clearActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        try { 
        String avail_notavail = null;
            if(avail.isSelected())
                avail_notavail = "Available";
            else if(notavail.isSelected())
                avail_notavail = "Not Available";
            
            if ((title.getText().isEmpty()) || (author.getText().isEmpty()) || (description.getText().isEmpty()) || (price.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Oops! All fields should have input!", "Error", JOptionPane.ERROR_MESSAGE);
            } 
            
            if (avail_notavail == null) {
                int choice = JOptionPane.showConfirmDialog(
                    null,
                    "No availability selected.\nDefault will be set to 'Available'. Continue?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
                );

                if (choice != JOptionPane.YES_OPTION) {
                    return; // cancel save
                }

                String sql = "INSERT INTO BOOK_FILE(title,author,description,price,book_id,avail_notavail,genre) VALUES (?,?,?,?,?,?,?)"; 
                //JOptionPane.showMessageDialog(null, avail_notavail); Removed 
                
                PreparedStatement pst = conn.prepareStatement(sql); 
                pst.setString(1, title.getText()); 
                pst.setString(2, author.getText()); 
                pst.setString(3, description.getText()); 
                pst.setFloat(4, Float.parseFloat(price.getText())); 
                pst.setInt(5, Integer.parseInt(bookid.getText())); 
                pst.setString(6,avail_notavail);
                pst.setString(7, genre.getText());
                pst.execute();  //to save in the database 
                    JOptionPane.showMessageDialog(null,"Saved successfully!"); 
                    JOptionPane.showMessageDialog(null,"Thank you! <3");
            }              
            
            title.setText("");
            author.setText("");
            description.setText("");
            price.setText("");
            bookid.setText("");
            genre.setText("");
            buttonGroup1.clearSelection();
           
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null, "Save failed ;3"); 
            e.printStackTrace(System.err); 
        } 
    }//GEN-LAST:event_saveActionPerformed

    private void titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleActionPerformed

    private void descriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionActionPerformed

    private void allbooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allbooksActionPerformed
        // TODO add your handling code here:
        booklistTABLE1();
    }//GEN-LAST:event_allbooksActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
    try {
            String avail_notavail = "";
            if (avail.isSelected())
                avail_notavail = "Available";
            else if (notavail.isSelected())
                avail_notavail = "Not Available";

            if (bookid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Book ID is required to update a record.");
                return;
            }

            String sql = "UPDATE BOOK_FILE SET title=?, author=?, description=?, genre=?, price=?, avail_notavail=? WHERE book_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, title.getText());
                pst.setString(2, author.getText());
                pst.setString(3, description.getText());
                pst.setString(4, genre.getText());
                pst.setFloat(5, Float.parseFloat(price.getText()));
                pst.setString(6, avail_notavail);
                pst.setInt(7, Integer.parseInt(bookid.getText()));

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(null, "Record updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No record found with that Book ID.");
                }
                
                title.setText("");
                author.setText("");
                description.setText("");
                price.setText("");
                bookid.setText("");
                genre.setText("");
                buttonGroup1.clearSelection();
            
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Update failed: " + e.getMessage());
                e.printStackTrace(System.err);
            }    
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(null, "Delete this book?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM BOOK_FILE WHERE book_id=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(bookid.getText()));

                int rowsDeleted = pst.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Book ID not found.");
                }
                
                title.setText("");
                author.setText("");
                description.setText("");
                price.setText("");
                bookid.setText("");
                genre.setText("");
                buttonGroup1.clearSelection();

                pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Delete failed!");
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookidActionPerformed

    private void genreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        searchBookByID();
    }//GEN-LAST:event_searchActionPerformed

    private void notallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notallActionPerformed
        // TODO add your handling code here:
        booklistTABLE2();
    }//GEN-LAST:event_notallActionPerformed
//main method , this should be created as all JAVA programs will look/read first the main method 
    public static void main(String args[]) { 
        /* Create and display the form */ 
        java.awt.EventQueue.invokeLater(new Runnable() { 
            public void run() { 
                new Book_File().setVisible(true);  // Book_File is the class name  
            } 
        }); 
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allbooks;
    private javax.swing.JTextField author;
    private javax.swing.JRadioButton avail;
    private javax.swing.JLabel bookdb;
    private javax.swing.JTextField bookid;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JTextField description;
    private javax.swing.JButton exit;
    private javax.swing.JTextField genre;
    private javax.swing.JLabel krus;
    private javax.swing.JLabel lblauth;
    private javax.swing.JLabel lblbid;
    private javax.swing.JLabel lbldesc;
    private javax.swing.JLabel lblpri;
    private javax.swing.JLabel lbltitle;
    private javax.swing.JLabel lbltitle1;
    private javax.swing.JButton notall;
    private javax.swing.JRadioButton notavail;
    private javax.swing.JTextField price;
    private javax.swing.JButton save;
    private javax.swing.JButton search;
    private javax.swing.JTextField title;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
