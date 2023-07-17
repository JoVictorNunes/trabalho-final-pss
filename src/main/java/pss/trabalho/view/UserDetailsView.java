/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package pss.trabalho.view;

import javax.swing.*;

/**
 *
 * @author joaovictor
 */
public class UserDetailsView extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewUser
     */
    public UserDetailsView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        userNameTxt = new javax.swing.JLabel();
        sendMsgBtn = new javax.swing.JButton();
        authBtn = new javax.swing.JButton();

        deleteBtn.setBackground(new java.awt.Color(102, 102, 255));
        deleteBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Excluir");
        deleteBtn.setBorderPainted(false);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(102, 102, 255));
        editBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Editar");
        editBtn.setBorderPainted(false);
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        closeBtn.setBackground(new java.awt.Color(102, 102, 255));
        closeBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        closeBtn.setForeground(new java.awt.Color(255, 255, 255));
        closeBtn.setText("Fechar");
        closeBtn.setBorderPainted(false);
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel1.setText("Nome");

        userNameTxt.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        userNameTxt.setText("Nome do usuário");

        sendMsgBtn.setBackground(new java.awt.Color(102, 102, 255));
        sendMsgBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        sendMsgBtn.setForeground(new java.awt.Color(255, 255, 255));
        sendMsgBtn.setText("Enviar mensagem");
        sendMsgBtn.setBorderPainted(false);

        authBtn.setBackground(new java.awt.Color(102, 102, 255));
        authBtn.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        authBtn.setForeground(new java.awt.Color(255, 255, 255));
        authBtn.setText("Autorizar");
        authBtn.setBorderPainted(false);
        authBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendMsgBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(authBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(userNameTxt))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userNameTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtn)
                    .addComponent(editBtn)
                    .addComponent(closeBtn)
                    .addComponent(sendMsgBtn)
                    .addComponent(authBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeBtnActionPerformed

    private void authBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_authBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton authBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton sendMsgBtn;
    private javax.swing.JLabel userNameTxt;
    // End of variables declaration//GEN-END:variables


    public JButton getCloseBtn() {
        return closeBtn;
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public JButton getEditBtn() {
        return editBtn;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JLabel getUserNameTxt() {
        return userNameTxt;
    }

    public JButton getSendMsgBtn() {
        return sendMsgBtn;
    }

    public JButton getAuthBtn() {
        return authBtn;
    }
    
    
}
