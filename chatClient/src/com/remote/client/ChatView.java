package com.remote.client;

import com.remote.server.InterfaceServer;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;


public class ChatView extends javax.swing.JFrame implements Runnable{
    private ChatClient client;
    private InterfaceServer server;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private Vector<String> listClients;
    private String name;
    private GroupLayout groupLayout;
    
    public ChatView(String name,String autorization,InterfaceServer server) {
        initComponents();
        
        this.server = server;
        this.name = name;

        if(autorization.equals("Admin")){
            System.out.print(autorization);
            listConnect.setComponentPopupMenu(jPopupMenu1);
        }
        
        this.setLocationRelativeTo(null);
        this.setTitle("ChatRMI - " + name);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("chat.jpg")));
        
        groupLayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(new GridLayout(100,1));
        jPanel1.setBorder(new EmptyBorder(5, 10, 10, 10));

        this.addWindowListener(new java.awt.event.WindowAdapter() {    
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(new JFrame(), 
                    "Are you want to close chat window?", "Close", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    try {
                        server.removeClient(name);
                    } catch (RemoteException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    System.exit(0);
                }else{
                   
                }
            }
        });

        inputMsg.setForeground(Color.GRAY);
        inputMsg.setText("Enter your Message ...");
        inputMsg.addFocusListener(new FocusListener() {
        @Override
         public void focusGained(FocusEvent e) {
            if (inputMsg.getText().equals("Enter your Message ...")) {
                inputMsg.setText("");
                inputMsg.setForeground(Color.BLACK);
            }
        }
        @Override
         public void focusLost(FocusEvent e) {
            if (inputMsg.getText().isEmpty()) {
                inputMsg.setForeground(Color.GRAY);
                inputMsg.setText("Enter your Message ...");
            }
        }
        });

        listClients = new Vector<>();
        listConnect.setListData(listClients);
        
        try{
            client = new ChatClient(name,server,inputMsg,listMessage,jPanel1);
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        Timer minuteur = new Timer();
        TimerTask tache = new TimerTask() {
            @Override
            public void run() {
                try {
                    int[] indices = listConnect.getSelectedIndices();
                    model.clear();
                    listClients = server.getListClientByName(name);
                    int i=0;
                    while(i<listClients.size()){
                        model.addElement(listClients.get(i));
                        i++;
                    }
                    listConnect.setModel(model);
                    listConnect.setSelectedIndices(indices);
                } catch (RemoteException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        };
        minuteur.schedule(tache,0,20000);
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listMessage = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listConnect = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputMsg = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jMenuItem1.setText("Remove Users");
        jMenuItem1.setActionCommand("");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Block Users");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Reactive Users");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        listMessage.setEditable(false);
        listMessage.setColumns(20);
        listMessage.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        listMessage.setRows(5);
        listMessage.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(listMessage);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("List Users");

        listConnect.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        listConnect.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listConnect.setToolTipText("");
        jScrollPane1.setViewportView(listConnect);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Refresh");
        jButton1.setActionCommand("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        inputMsg.setColumns(20);
        inputMsg.setRows(5);
        inputMsg.setToolTipText("Enter your Message ...");
        inputMsg.setMargin(new java.awt.Insets(6, 0, 0, 16));
        jScrollPane2.setViewportView(inputMsg);
        inputMsg.getAccessibleContext().setAccessibleName("Enter your Message ...");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/remote/client/uploadfile.png"))); // NOI18N
        jButton3.setToolTipText("upload File");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setDefaultCapable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSend.setBackground(new java.awt.Color(255, 255, 255));
        btnSend.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnSend.setText("Send");
        btnSend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("File Choosed");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 113, Short.MAX_VALUE)
        );

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                        .addComponent(btnSend, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        if(!inputMsg.getText().equals("")){
            if(!inputMsg.getText().equals("Enter you Message ...")){
                client.sendMessage(listConnect.getSelectedValuesList());
                inputMsg.setText("");
            }else{
            JOptionPane.showMessageDialog(this,"Please insert something to set your message","Notify",JOptionPane.WARNING_MESSAGE);
        }
        }else{
            JOptionPane.showMessageDialog(this,"Please insert something to send your message","Notify",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSendActionPerformed


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Thread thread = new Thread(this);
        thread.start();
    }//GEN-LAST:event_jButton1ActionPerformed


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        try {
            server.removeClient(listConnect.getSelectedValuesList());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            server.blockClient(listConnect.getSelectedValuesList());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed


    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            server.reactiveClient(listConnect.getSelectedValuesList());
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            String[] extension = file.getName().split("\\.");
            System.out.println(extension.length);
            if(extension[extension.length - 1].equals("txt")||
                extension[extension.length - 1].equals("java")||
                extension[extension.length - 1].equals("php")||
                extension[extension.length - 1].equals("c")||
                extension[extension.length - 1].equals("cpp")||
                extension[extension.length - 1].equals("xml")||
                extension[extension.length - 1].equals("exe")||
                extension[extension.length - 1].equals("png")||
                extension[extension.length - 1].equals("jpg")||
                extension[extension.length - 1].equals("jpeg")||
                extension[extension.length - 1].equals("pdf")||
                extension[extension.length - 1].equals("jar")||
                extension[extension.length - 1].equals("rar")||
                extension[extension.length - 1].equals("zip")
            ){
                try {
                    ArrayList<Integer> inc;
                    try (FileInputStream in = new FileInputStream(file)) {
                        inc = new ArrayList<>();
                        int c=0;
                        while((c=in.read()) != -1) {
                            inc.add(c);
                        }
                        in.close();
                    }
                    server.broadcastMessage(inc, listClients,file.getName());
                } catch (FileNotFoundException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (RemoteException ex) {
                    System.out.println("Error: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

                JLabel jfile = new JLabel(file.getName() + " Uploaded ...");
                jPanel1.add(jfile);
                jPanel1.repaint();
                jPanel1.revalidate();
            }else{
                JOptionPane.showMessageDialog(this,"File type can upload: xml,exe,jpg,png,jpeg,pdf,c,cpp,jar,java,txt,php ","Notice",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JTextArea inputMsg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listConnect;
    private javax.swing.JTextArea listMessage;
    // End of variables declaration//GEN-END:variables


    @Override
    public void run() {
        try {
           
            model.clear();
            listClients = server.getListClientByName(name);
            int i=0;
            while(i<listClients.size()){
                model.addElement(listClients.get(i));
                i++;
            }
            listConnect.setModel(model);
        } catch (RemoteException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}