package edu.upv.poo;

/**
 * Representa el JFrame del menu de las opciones de la aplicación.
 * @author luisroberto
 */
public class MenuFrame extends javax.swing.JFrame {

    private SubjectFrame subjectFrame = new SubjectFrame();
    
    /**
     * Creates new form ManuFrame
     */
    public MenuFrame() {
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

        btnEjemploEventos = new javax.swing.JButton();
        btnPaint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Práctica 02 - Menú Princial");

        btnEjemploEventos.setText("Ejemplo Eventos");
        btnEjemploEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjemploEventosActionPerformed(evt);
            }
        });

        btnPaint.setText("Paint");
        btnPaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEjemploEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(btnPaint, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEjemploEventos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPaint)
                .addContainerGap(491, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEjemploEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjemploEventosActionPerformed

        ObserverFrame frame = new ObserverFrame(subjectFrame);
        frame.setVisible(true);
        
        if (!subjectFrame.isVisible()) subjectFrame.setVisible(true);
        
    }//GEN-LAST:event_btnEjemploEventosActionPerformed

    private void btnPaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaintActionPerformed
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }//GEN-LAST:event_btnPaintActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjemploEventos;
    private javax.swing.JButton btnPaint;
    // End of variables declaration//GEN-END:variables
}