/**
 * Copyright (c) 2013, Robert Cherry
 * Modified 2015, Hoang Tran
 * All rights reserved.
 *
 * This file is part of the Faust Editor.
 *
 * The Faust Editor is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * The Faust Editor is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * The Faust Editor. If not, see <http://www.gnu.org/licenses/>.
 */
package Editor.form;

import Editor.FaustEditor;
import core.world.World;
import core.world.WorldCell;
import io.resource.ResourceDelegate;
import io.resource.ResourceWriter;
import java.awt.Dimension;
import Editor.listener.ManifestBinder;

/**
 *
 * @author Robert A. Cherry, Hoang Tran
 */
public class WorldCellEditor extends javax.swing.JDialog {

    // Variable Declaration
    // Project Classes
    private FaustEditor editor;
    private WorldCell resource;
    private ResourceDelegate delegate;
    private ManifestBinder binder;
    private DelegateCheckBox box_delegate;
    // Data Types
    private boolean edit;
    // End of Variable Declaration

    public WorldCellEditor(FaustEditor editor, ResourceDelegate delegate, WorldCell resource, boolean modal) {

        // Call to super
        super(editor, modal);
        initComponents();

        // Set values equal
        this.editor = editor;
        this.delegate = delegate;
        this.resource = resource;

        // Initialize my commands
        init();
    }

     private void init() {

        // Check to see if the world even exists
        setTitle("World Cell Editor: " + resource.getDisplayName());

        // Displays default cell size only if the world cell doesn't exist
        if (resource.getDisplayName() != null){
            widthJField.setValue(resource.getWidth());
            heightJField.setValue(resource.getHeight());
        }
        else{
            widthJField.setValue(640);
            heightJField.setValue(480);
        }

        //
        setupManifestBinder();
    }

    private void setupManifestBinder() {

        //
        box_delegate = new DelegateCheckBox(delegate);
        buttonJPanel.add(box_delegate, 0);

        //
        final World world = resource.getWorld();
        field_plugin.setText(world.getDisplayName());

        // Testing it out.
        binder = new ManifestBinder(delegate, resource);

        // Binding stuff manually.
        binder.bind(ManifestBinder.BOX_DELEGATE, box_delegate);
        binder.bind(ManifestBinder.BUTTON_GENERATE, button_generate);

        // Fields
        binder.bind(ManifestBinder.FIELD_DISPLAY, field_display);
        binder.bind(ManifestBinder.FIELD_REFERENCE, field_reference);
        binder.bind(ManifestBinder.FIELD_NAME, field_name);
        binder.bind(ManifestBinder.FIELD_WIDTH, field_width);
        binder.bind(ManifestBinder.FIELD_HEIGHT, field_height);

        // Invoke the wrath of the Manifest Binder. >:[
        binder.invoke();

        //
        box_delegate.setText("Valid");

        // We are in fact editting an existing resource.
        //binder.setEdit(edit);

        // Either or method above.
        binder.setEdit(delegate.getInstanceCount(resource) >= 1);
        button_generate.setEnabled(!binder.isEditting());
    }

    private void finish() {

        //
        if (box_delegate.isSelected()) {

            //
            final World world = resource.getWorld();
            
            // Fix applied: now allows creation of non-default cell sizes
            resource.setWidth(((Number) widthJField.getValue()).intValue());
            resource.setHeight(((Number) heightJField.getValue()).intValue());

            // Apply these changes to the fMap
            resource.setReferenceID(field_reference.getText());
            resource.setReferenceName(field_name.getText());
            resource.setDisplayName(field_display.getText());

            // Add to world
            world.addCell(resource);

            // Reverse
            resource.setWorld(world);

            // Ensure attributes map is up to date
            resource.updateAttributes();

            //
            resource.validate();

            //
            ResourceWriter.write(delegate, resource);

            // Rewrite the world as well since it now includes this cell.
            ResourceWriter.write(delegate, world);

            //
            delegate.addResource(resource);

            // Close this
            setVisible(false);
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
        java.awt.GridBagConstraints gridBagConstraints;

        field_width = new javax.swing.JTextField();
        field_height = new javax.swing.JTextField();
        buttonJPanel = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        finishJButton = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        cancelJButton = new javax.swing.JButton();
        mainJTabbedPane = new javax.swing.JTabbedPane();
        dummyJPanel = new javax.swing.JPanel();
        settingJPanel = new javax.swing.JPanel();
        pluginJLabel = new javax.swing.JLabel();
        field_plugin = new javax.swing.JTextField();
        widthJLabel1 = new javax.swing.JLabel();
        heightJLabel1 = new javax.swing.JLabel();
        referenceJLabel = new javax.swing.JLabel();
        nameJLabel = new javax.swing.JLabel();
        field_reference = new javax.swing.JTextField();
        field_name = new javax.swing.JTextField();
        field_display = new javax.swing.JTextField();
        displayJLabel = new javax.swing.JLabel();
        widthJField = new javax.swing.JFormattedTextField();
        heightJField = new javax.swing.JFormattedTextField();
        button_generate = new javax.swing.JButton();

        field_width.setColumns(20);
        field_width.setMaximumSize(new java.awt.Dimension(134, 22));
        field_width.setMinimumSize(new java.awt.Dimension(134, 22));
        field_width.setPreferredSize(new java.awt.Dimension(134, 22));

        field_height.setColumns(20);
        field_height.setMaximumSize(new java.awt.Dimension(134, 22));
        field_height.setMinimumSize(new java.awt.Dimension(134, 22));
        field_height.setPreferredSize(new java.awt.Dimension(134, 22));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("World Cell Editing");
        setMinimumSize(new java.awt.Dimension(274, 196));
        setResizable(false);

        buttonJPanel.setMinimumSize(new java.awt.Dimension(271, 26));
        buttonJPanel.setPreferredSize(new java.awt.Dimension(271, 26));
        buttonJPanel.setLayout(new javax.swing.BoxLayout(buttonJPanel, javax.swing.BoxLayout.LINE_AXIS));
        buttonJPanel.add(filler1);

        finishJButton.setText("Finish");
        finishJButton.setMaximumSize(new java.awt.Dimension(88, 26));
        finishJButton.setMinimumSize(new java.awt.Dimension(88, 26));
        finishJButton.setPreferredSize(new java.awt.Dimension(88, 26));
        finishJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishJButtonActionPerformed(evt);
            }
        });
        buttonJPanel.add(finishJButton);
        buttonJPanel.add(filler2);

        cancelJButton.setText("Cancel");
        cancelJButton.setMaximumSize(new java.awt.Dimension(88, 26));
        cancelJButton.setMinimumSize(new java.awt.Dimension(88, 26));
        cancelJButton.setPreferredSize(new java.awt.Dimension(88, 26));
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });
        buttonJPanel.add(cancelJButton);

        settingJPanel.setMaximumSize(new java.awt.Dimension(247, 160));
        settingJPanel.setMinimumSize(new java.awt.Dimension(247, 160));
        settingJPanel.setPreferredSize(new java.awt.Dimension(247, 160));
        java.awt.GridBagLayout settingJPanelLayout = new java.awt.GridBagLayout();
        settingJPanelLayout.columnWidths = new int[] {0, 5, 0};
        settingJPanelLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        settingJPanel.setLayout(settingJPanelLayout);

        pluginJLabel.setText("Part of World:");
        pluginJLabel.setEnabled(false);
        pluginJLabel.setMaximumSize(new java.awt.Dimension(104, 22));
        pluginJLabel.setMinimumSize(new java.awt.Dimension(104, 22));
        pluginJLabel.setPreferredSize(new java.awt.Dimension(104, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        settingJPanel.add(pluginJLabel, gridBagConstraints);

        field_plugin.setColumns(20);
        field_plugin.setEnabled(false);
        field_plugin.setMaximumSize(new java.awt.Dimension(134, 22));
        field_plugin.setMinimumSize(new java.awt.Dimension(134, 22));
        field_plugin.setPreferredSize(new java.awt.Dimension(134, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        settingJPanel.add(field_plugin, gridBagConstraints);

        widthJLabel1.setForeground(new java.awt.Color(51, 51, 51));
        widthJLabel1.setText("Cell Width:");
        widthJLabel1.setMaximumSize(new java.awt.Dimension(104, 22));
        widthJLabel1.setMinimumSize(new java.awt.Dimension(104, 22));
        widthJLabel1.setPreferredSize(new java.awt.Dimension(104, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        settingJPanel.add(widthJLabel1, gridBagConstraints);

        heightJLabel1.setForeground(new java.awt.Color(51, 51, 51));
        heightJLabel1.setText("Cell Height:");
        heightJLabel1.setMaximumSize(new java.awt.Dimension(104, 22));
        heightJLabel1.setMinimumSize(new java.awt.Dimension(104, 22));
        heightJLabel1.setPreferredSize(new java.awt.Dimension(104, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        settingJPanel.add(heightJLabel1, gridBagConstraints);

        referenceJLabel.setForeground(new java.awt.Color(51, 51, 51));
        referenceJLabel.setText("Reference ID:");
        referenceJLabel.setMaximumSize(new java.awt.Dimension(104, 22));
        referenceJLabel.setMinimumSize(new java.awt.Dimension(104, 22));
        referenceJLabel.setPreferredSize(new java.awt.Dimension(104, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        settingJPanel.add(referenceJLabel, gridBagConstraints);

        nameJLabel.setForeground(new java.awt.Color(51, 51, 51));
        nameJLabel.setText("Reference Name:");
        nameJLabel.setMaximumSize(new java.awt.Dimension(104, 22));
        nameJLabel.setMinimumSize(new java.awt.Dimension(104, 22));
        nameJLabel.setPreferredSize(new java.awt.Dimension(104, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        settingJPanel.add(nameJLabel, gridBagConstraints);

        field_reference.setColumns(20);
        field_reference.setToolTipText("");
        field_reference.setMaximumSize(new java.awt.Dimension(134, 22));
        field_reference.setMinimumSize(new java.awt.Dimension(134, 22));
        field_reference.setPreferredSize(new java.awt.Dimension(134, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        settingJPanel.add(field_reference, gridBagConstraints);

        field_name.setColumns(20);
        field_name.setMaximumSize(new java.awt.Dimension(134, 22));
        field_name.setMinimumSize(new java.awt.Dimension(134, 22));
        field_name.setPreferredSize(new java.awt.Dimension(134, 22));
        field_name.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                field_nameComponentResized(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        settingJPanel.add(field_name, gridBagConstraints);

        field_display.setColumns(20);
        field_display.setMaximumSize(new java.awt.Dimension(134, 22));
        field_display.setMinimumSize(new java.awt.Dimension(134, 22));
        field_display.setPreferredSize(new java.awt.Dimension(134, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        settingJPanel.add(field_display, gridBagConstraints);

        displayJLabel.setForeground(new java.awt.Color(51, 51, 51));
        displayJLabel.setText("Display Name:");
        displayJLabel.setMaximumSize(new java.awt.Dimension(104, 22));
        displayJLabel.setMinimumSize(new java.awt.Dimension(104, 22));
        displayJLabel.setPreferredSize(new java.awt.Dimension(104, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        settingJPanel.add(displayJLabel, gridBagConstraints);

        widthJField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        widthJField.setMaximumSize(new java.awt.Dimension(132, 22));
        widthJField.setMinimumSize(new java.awt.Dimension(132, 22));
        widthJField.setPreferredSize(new java.awt.Dimension(132, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        settingJPanel.add(widthJField, gridBagConstraints);

        heightJField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        heightJField.setMaximumSize(new java.awt.Dimension(132, 22));
        heightJField.setMinimumSize(new java.awt.Dimension(132, 22));
        heightJField.setPreferredSize(new java.awt.Dimension(132, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        settingJPanel.add(heightJField, gridBagConstraints);

        button_generate.setText("Generate ID's");
        button_generate.setMaximumSize(new java.awt.Dimension(104, 26));
        button_generate.setMinimumSize(new java.awt.Dimension(104, 26));
        button_generate.setPreferredSize(new java.awt.Dimension(104, 26));
        button_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_generateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dummyJPanelLayout = new javax.swing.GroupLayout(dummyJPanel);
        dummyJPanel.setLayout(dummyJPanelLayout);
        dummyJPanelLayout.setHorizontalGroup(
            dummyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dummyJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dummyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_generate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dummyJPanelLayout.setVerticalGroup(
            dummyJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dummyJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(settingJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_generate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainJTabbedPane.addTab("Editor Settings", dummyJPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainJTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainJTabbedPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void finishJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishJButtonActionPerformed

        // Apply changes to the map
        finish();
    }//GEN-LAST:event_finishJButtonActionPerformed

    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelJButtonActionPerformed

        // Cancel changes
        setVisible(false);
    }//GEN-LAST:event_cancelJButtonActionPerformed

    private void field_nameComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_field_nameComponentResized

        // TODO add your handling code here:
        final Dimension dimension = new Dimension(134, 22);

        // Destroy the layout manager to cool the auto resizing "feature"
        settingJPanel.setLayout(null);

        // TODO add your handling code here:
        //nameJField.setSize(dimension);
        nameJLabel.setSize(dimension);

        //
        field_reference.setSize(dimension);
        referenceJLabel.setSize(dimension);

        //
        field_display.setSize(dimension);
        displayJLabel.setSize(dimension);

        //
        field_plugin.setSize(dimension);
    }//GEN-LAST:event_field_nameComponentResized

    private void button_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_generateActionPerformed

        // Just to make sure.
        if (binder.isEditting() == false) {

            // Click this button to auto-generate all three forms of manifest-to-delegate identification
            binder.testButton();
        }
    }//GEN-LAST:event_button_generateActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonJPanel;
    private javax.swing.JButton button_generate;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JLabel displayJLabel;
    private javax.swing.JPanel dummyJPanel;
    private javax.swing.JTextField field_display;
    private javax.swing.JTextField field_height;
    private javax.swing.JTextField field_name;
    private javax.swing.JTextField field_plugin;
    private javax.swing.JTextField field_reference;
    private javax.swing.JTextField field_width;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton finishJButton;
    private javax.swing.JFormattedTextField heightJField;
    private javax.swing.JLabel heightJLabel1;
    private javax.swing.JTabbedPane mainJTabbedPane;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JLabel pluginJLabel;
    private javax.swing.JLabel referenceJLabel;
    private javax.swing.JPanel settingJPanel;
    private javax.swing.JFormattedTextField widthJField;
    private javax.swing.JLabel widthJLabel1;
    // End of variables declaration//GEN-END:variables
}
