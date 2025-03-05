
package Views;


public class ProductLitView extends javax.swing.JPanel {

    public ProductLitView() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnExcel = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtCod = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnPdf = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa.png"))); // NOI18N

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/excel.png"))); // NOI18N

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));

        jLabel11.setText("Nombre del producto");

        jLabel12.setText("Codigo");

        jLabel13.setText("Stock");

        jLabel14.setText("Precio");

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nuevo.png"))); // NOI18N

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Actualizar (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(txtCod, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addComponent(txtName)
                            .addComponent(txtStock)
                            .addComponent(txtPrice))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnDelete)
                                .addGap(31, 31, 31)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(122, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addGap(8, 8, 8)
                .addComponent(txtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(4, 4, 4)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(1, 1, 1)
                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(109, 109, 109))
        );

        btnPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pdf.png"))); // NOI18N

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnPdf)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(28, 28, 28))))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(txtSearch)
                            .addComponent(btnPdf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1360, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnDelete;
    public javax.swing.JButton btnExcel;
    public javax.swing.JButton btnPdf;
    public javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    public javax.swing.JTextField txtCod;
    public javax.swing.JTextField txtName;
    public javax.swing.JTextField txtPrice;
    public javax.swing.JTextField txtSearch;
    public javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
