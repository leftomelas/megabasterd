package megabasterd;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.tree.DefaultTreeModel;
import static megabasterd.MainPanel.FONT_DEFAULT;
import static megabasterd.MainPanel.THREAD_POOL;
import static megabasterd.MiscTools.deleteAllExceptSelectedTreeItems;
import static megabasterd.MiscTools.deleteSelectedTreeItems;
import static megabasterd.MiscTools.findFirstRegex;
import static megabasterd.MiscTools.formatBytes;
import static megabasterd.MiscTools.sortTree;
import static megabasterd.MiscTools.swingReflectionInvoke;
import static megabasterd.MiscTools.updateFont;

/**
 *
 * @author tonikelope
 */
public final class FolderLinkDialog extends javax.swing.JDialog {

    private String _link;
    
    private boolean _download;
    
    private final List<HashMap> _download_links;
    
    private long _total_space;
    
    private boolean _mega_error;

    public List<HashMap> getDownload_links() {
        return _download_links;
    }

    public boolean isDownload() {
        return _download;
    }

    public boolean isMega_error() {
        return _mega_error;
    }
    
    
    /**
     * Creates new form FolderLink
     */
    public FolderLinkDialog(java.awt.Frame parent, boolean modal, String link) {
        
        super(parent, modal);
 
        initComponents();

        _mega_error = false;
        _total_space = 0L;
        _download = false;
        _link = null;

        updateFont(file_tree, FONT_DEFAULT, PLAIN);
        updateFont(link_detected_label, FONT_DEFAULT, PLAIN);
        updateFont(warning_label, FONT_DEFAULT, PLAIN);
        updateFont(skip_button, FONT_DEFAULT, PLAIN);
        updateFont(dance_button, FONT_DEFAULT, PLAIN);
        updateFont(restore_button, FONT_DEFAULT, PLAIN);
        updateFont(skip_rest_button, FONT_DEFAULT, PLAIN);
        updateFont(total_space_label, FONT_DEFAULT, BOLD);
        updateFont(folder_link_label, FONT_DEFAULT, PLAIN);
        
        swingReflectionInvoke("setVisible", restore_button, false);
        
        swingReflectionInvoke("setEnabled", total_space_label, false);
        
        _link = link;
        
        swingReflectionInvoke("setText", folder_link_label, link);
        
        _download_links = new ArrayList<>();
  
        _loadMegaDirTree();
        
        if(!_mega_error) {
            
            _genDownloadLiks();
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

        file_tree_scrollpane = new javax.swing.JScrollPane();
        file_tree = new javax.swing.JTree();
        skip_button = new javax.swing.JButton();
        link_detected_label = new javax.swing.JLabel();
        dance_button = new javax.swing.JButton();
        folder_link_label = new javax.swing.JLabel();
        warning_label = new javax.swing.JLabel();
        skip_rest_button = new javax.swing.JButton();
        restore_button = new javax.swing.JButton();
        total_space_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FolderLink");

        file_tree.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        file_tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        file_tree.setDoubleBuffered(true);
        file_tree_scrollpane.setViewportView(file_tree);

        skip_button.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        skip_button.setText("REMOVE THIS");
        skip_button.setDoubleBuffered(true);
        skip_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skip_buttonActionPerformed(evt);
            }
        });

        link_detected_label.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        link_detected_label.setText("Folder link detected!");
        link_detected_label.setDoubleBuffered(true);

        dance_button.setBackground(new java.awt.Color(102, 204, 255));
        dance_button.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        dance_button.setForeground(new java.awt.Color(255, 255, 255));
        dance_button.setText("Let's dance, baby");
        dance_button.setDoubleBuffered(true);
        dance_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dance_buttonActionPerformed(evt);
            }
        });

        folder_link_label.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        folder_link_label.setText("jLabel2");
        folder_link_label.setDoubleBuffered(true);

        warning_label.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        warning_label.setText("If you DO NOT want to download some folder or file you can REMOVE it.");
        warning_label.setDoubleBuffered(true);

        skip_rest_button.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        skip_rest_button.setText("REMOVE ALL EXCEPT THIS");
        skip_rest_button.setDoubleBuffered(true);
        skip_rest_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skip_rest_buttonActionPerformed(evt);
            }
        });

        restore_button.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        restore_button.setText("Restore folder data");
        restore_button.setDoubleBuffered(true);
        restore_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restore_buttonActionPerformed(evt);
            }
        });

        total_space_label.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        total_space_label.setText("[0 B]");
        total_space_label.setDoubleBuffered(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(file_tree_scrollpane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(skip_rest_button)
                        .addGap(18, 18, 18)
                        .addComponent(skip_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dance_button, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(folder_link_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(restore_button))
                    .addComponent(total_space_label, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(link_detected_label)
                            .addComponent(warning_label))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(link_detected_label)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folder_link_label)
                    .addComponent(restore_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(file_tree_scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total_space_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warning_label)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(dance_button))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(skip_rest_button)
                            .addComponent(skip_button))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void skip_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skip_buttonActionPerformed
        
        
        if(deleteSelectedTreeItems(file_tree)) {
            
            _genDownloadLiks();
            swingReflectionInvoke("setVisible", restore_button, true);
        }
  
    }//GEN-LAST:event_skip_buttonActionPerformed

    private void dance_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dance_buttonActionPerformed
        
        
        
        _download = true;
        swingReflectionInvoke("setVisible", this, false);
    }//GEN-LAST:event_dance_buttonActionPerformed

    private void skip_rest_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skip_rest_buttonActionPerformed
        
  
        if(deleteAllExceptSelectedTreeItems(file_tree)) {
            
            _genDownloadLiks();
            swingReflectionInvoke("setVisible", restore_button, true);
        }
     
    }//GEN-LAST:event_skip_rest_buttonActionPerformed

    private void restore_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restore_buttonActionPerformed
        
        
        swingReflectionInvoke("setEnabled", restore_button, false);
        
        swingReflectionInvoke("setEnabled", dance_button, false);
        
        swingReflectionInvoke("setEnabled", file_tree, false);

        final FolderLinkDialog main = this;
         
        THREAD_POOL.execute(new Runnable(){
                    @Override
                    public void run() {
                        
                    main._loadMegaDirTree();
                    
                    main._genDownloadLiks();
                    
                    swingReflectionInvoke("setEnabled", main.restore_button, true);
                    
                    swingReflectionInvoke("setVisible", main.restore_button, false);
        
                    swingReflectionInvoke("setEnabled", main.dance_button, true);
        
                    swingReflectionInvoke("setEnabled", main.file_tree, true);
                    
                    }});
    }//GEN-LAST:event_restore_buttonActionPerformed
    
    private void _loadMegaDirTree() {
        
        try {
            HashMap<String,Object> folder_nodes;
            
            MegaAPI ma = new MegaAPI();
            
            String folder_id = findFirstRegex("#F!([^!]+)", _link, 1);
            
            String folder_key = findFirstRegex("#F![^!]+!(.+)", _link, 1);
            
            folder_nodes = ma.getFolderNodes(folder_id, folder_key);
             
            MegaMutableTreeNode root=null;

            for(Object o:folder_nodes.values()) {

                HashMap<String,Object> current_hashmap_node = (HashMap<String,Object>)o;

                MegaMutableTreeNode current_node;

                if(current_hashmap_node.get("jtree_node") == null) {

                    current_node = new MegaMutableTreeNode(current_hashmap_node);

                    current_hashmap_node.put("jtree_node", current_node);

                } else {

                    current_node = (MegaMutableTreeNode)current_hashmap_node.get("jtree_node");
                }

                String parent_id=(String)current_hashmap_node.get("parent");

                root=null;

                do{

                    if(folder_nodes.get(parent_id) != null) {

                        HashMap<String,Object> parent_hashmap_node = (HashMap)folder_nodes.get(parent_id);

                        MegaMutableTreeNode parent_node;

                        if(parent_hashmap_node.get("jtree_node") == null) {

                            parent_node = new MegaMutableTreeNode(parent_hashmap_node);

                            parent_hashmap_node.put("jtree_node", parent_node);

                        } else {

                            parent_node = (MegaMutableTreeNode)parent_hashmap_node.get("jtree_node");
                        }

                        parent_node.add(current_node);

                        parent_id = (String)parent_hashmap_node.get("parent");

                        current_node = parent_node;

                    } else {

                        root = current_node;
                    }

                }while(current_node != root);
            }

            file_tree.setModel(new DefaultTreeModel(sortTree(root)));
    
        } catch (Exception ex) {
            
            getLogger(FolderLinkDialog.class.getName()).log(SEVERE, null, ex);
            
            _mega_error = true;
        }

    }
    
    private void _genDownloadLiks(){
        
        String folder_id = findFirstRegex("#F!([^!]+)", _link, 1);
        
        _download_links.clear();
        
        MegaMutableTreeNode root = (MegaMutableTreeNode)file_tree.getModel().getRoot();
        
        Enumeration files_tree = root.depthFirstEnumeration();
        
        _total_space = 0L;
        
        while(files_tree.hasMoreElements()) {
            
            MegaMutableTreeNode node = (MegaMutableTreeNode)files_tree.nextElement();
            
            if(node.isLeaf() && node!=root && ((HashMap<String,Object>)node.getUserObject()).get("size") != null) {
                
                String path = "";
                
                Object[] object_path = node.getUserObjectPath();
                
                for(Object p:object_path) {
                    
                    path+="/"+((Map<String,Object>)p).get("name");
                }
                
                path = path.replaceAll("^/+", "").trim();
                
                String url = "https://mega.nz/#N!" + ((Map<String, Object>) node.getUserObject()).get("h") + "!" + ((Map<String, Object>) node.getUserObject()).get("key") + "###n=" + folder_id ;

                HashMap<String,Object> download_link = new HashMap<>();

                download_link.put("url", url);

                download_link.put("filename", path);

                download_link.put("filekey", ((Map<String, Object>) node.getUserObject()).get("key"));

                download_link.put("filesize", ((HashMap<String,Object>)node.getUserObject()).get("size"));
                
                _total_space+=(long)download_link.get("filesize");

                _download_links.add(download_link);
            }
        }
      
        swingReflectionInvoke("setText", total_space_label, "["+formatBytes(_total_space)+"]");
        
        if(_total_space == 0) {

            swingReflectionInvoke("setEnabled", dance_button, false);
            swingReflectionInvoke("setEnabled", warning_label, false);
            swingReflectionInvoke("setEnabled", skip_button, false);
            swingReflectionInvoke("setEnabled", skip_rest_button, false);
            swingReflectionInvoke("setEnabled", total_space_label, false);
            
        } else {

            swingReflectionInvoke("setEnabled", dance_button, true);
            swingReflectionInvoke("setEnabled", warning_label, true);
            swingReflectionInvoke("setEnabled", skip_button, true);
            swingReflectionInvoke("setEnabled", skip_rest_button, true);
            swingReflectionInvoke("setEnabled", total_space_label, true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dance_button;
    private javax.swing.JTree file_tree;
    private javax.swing.JScrollPane file_tree_scrollpane;
    private javax.swing.JLabel folder_link_label;
    private javax.swing.JLabel link_detected_label;
    private javax.swing.JButton restore_button;
    private javax.swing.JButton skip_button;
    private javax.swing.JButton skip_rest_button;
    private javax.swing.JLabel total_space_label;
    private javax.swing.JLabel warning_label;
    // End of variables declaration//GEN-END:variables
}