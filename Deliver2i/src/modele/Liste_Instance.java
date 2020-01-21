/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import deliver2i.Instance;
import io.InstanceReader;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author PORTABLE
 */
public class Liste_Instance extends javax.swing.JFrame {

    /**
     * Creates new form Liste_Instance
     */
    private List<Instance> maListeInstance;
    private DefaultListModel model;
    private EntityManager em;
    private EntityManagerFactory emf;

    public Liste_Instance() throws SQLException {
        inititalisationFenetre();

        initComponents();

        maListeInstance = new ArrayList<>();

        this.emf = Persistence.createEntityManagerFactory("Deliver2iPU");
        this.em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            // creation d’une entite persistante
            InstanceReader instread7 = new InstanceReader("instance_6.csv");
            InstanceReader instread8 = new InstanceReader("instance_7.csv");
      //      InstanceReader instread9 = new InstanceReader("instance_8.csv");
      //      InstanceReader instread10 = new InstanceReader("instance_9.csv");
      //      InstanceReader instread11 = new InstanceReader("instance_10.csv");

            instread7.readInstance(em);
            instread8.readInstance(em);
      //      instread9.readInstance(em);
      /*      instread10.readInstance(em);
            instread11.readInstance(em);*/
            et.commit();
            remplirListeInstance();

        } catch (Exception ex) {
            et.rollback();
        }

    }

    private void inititalisationFenetre() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Fenêtre d'instance");
        this.getContentPane().setBackground(new Color(0, 0, 26));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser3 = new javax.swing.JFileChooser();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton4 = new javax.swing.JButton();
        jFileChooser4 = new javax.swing.JFileChooser();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(770, 650));
        setResizable(false);

        jButton2.setText("Supprimer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Liste des instances");

        jScrollPane1.setViewportView(jList1);

        jButton4.setText("Voir Tournée");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jFileChooser4.setApproveButtonToolTipText("");
        jFileChooser4.setCurrentDirectory(new java.io.File("/home/guigeek/dir"));
        jFileChooser4.setToolTipText("");
        jFileChooser4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jFileChooser4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser4ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ou utiliser ce panneau pour ajouter une  Instance :");

        jButton1.setText("Solution");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Veuillez sélectionner une Instance puis cliquer sur  un bouton d'action ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jFileChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFileChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = jList1.getSelectedIndex() + 1;
        System.out.println("J 'ai sélectionné l'instance " + index);
        em.getTransaction().begin();

        // On commence par supprimer les tournées qui appartiennent à l'instance
        Query query = this.em.createQuery("Delete  from  Tournee t WHERE t.monInstance.id = :id");
        query.setParameter("id", index);
        query.executeUpdate();
        
        //et les solutions
        Query query3 = this.em.createQuery("Delete  from  Solution s WHERE s.monInstance.id = :id");
        query3.setParameter("id", index);
        query3.executeUpdate();
        
        Query query4 = this.em.createQuery("Delete  from  Shift s WHERE s.solution.id = :id");
        query4.setParameter("id", index);
        query4.executeUpdate();
        
        // Puis on exécute une nouvelle requête pour supprimer l'instance
        Query query2 = this.em.createQuery("Delete from Instance i WHERE i.id = :id");
        query2.setParameter("id", index);
        query2.executeUpdate();
        em.getTransaction().commit();
        remplirListeInstance();
        //System.out.println("J'ai supprimé l'instance " + index);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int index = jList1.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Aucune instance n'a été sélectionnée!!",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            JFrame f = new Liste_Tournee(index + 1, emf); // +1 car la bdd n'a pas d'id=0 contrairement au tableau
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jFileChooser4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser4ActionPerformed
        String file = jFileChooser4.getSelectedFile().getName();
        this.em = emf.createEntityManager();
        final EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            InstanceReader instread = new InstanceReader(file);
            instread.readInstance(em);
            et.commit();
            remplirListeInstance();

        } catch (Exception ex) {
            et.rollback();
        }
    }//GEN-LAST:event_jFileChooser4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int index = jList1.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(rootPane, "Aucune instance n'a été sélectionnée!!",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JFrame f2 = new Liste_Solution(index + 1, emf);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Liste_Instance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Liste_Instance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Liste_Instance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Liste_Instance.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Liste_Instance().setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(Liste_Instance.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser3;
    private javax.swing.JFileChooser jFileChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void remplirListeInstance() {
        em.getTransaction().begin();
        Query query = this.em.createQuery("select i from Instance AS i", Instance.class);
        List<Instance> maListeInstance = query.getResultList();
        DefaultListModel defaut = new DefaultListModel();

        maListeInstance.forEach(
                (Instance) -> {
                    defaut.addElement(Instance);
                }
        );
        jList1.setModel(defaut);

        em.getTransaction()
                .commit();

    }

}
