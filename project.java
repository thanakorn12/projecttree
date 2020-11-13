import java.io.File;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class project{
    
    public File fileRoot;
    
    public static void main(String[] args){
        
        JFrame myFrame = new JFrame("Project");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JSplitPane splitPane = new JSplitPane ( );
        splitPane.setOrientation ( JSplitPane.HORIZONTAL_SPLIT );
        
        File fileRoot = new File("‪D:\\landmark");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileRoot.getName());
        DefaultTreeModel model = new DefaultTreeModel(root) {};
        
        JTree tree = new JTree(model);
        JScrollPane treeScroll = new JScrollPane (tree);
        JLabel display = new JLabel();
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                
                TreePath treePath = e.getPath();
                DefaultMutableTreeNode newRoot = (DefaultMutableTreeNode) treePath.getLastPathComponent();
                
                String path = createFilePath(treePath);
                File newFile = new File(path);
                if(newFile.isDirectory()){
                    createChildren(newFile,newRoot); 
                }else{
                    ImageIcon image1 = new ImageIcon(path);
                    
                    display.setIcon(image1);
                    display.setHorizontalAlignment(JLabel.CENTER);
                    
                    splitPane.setRightComponent(display);
                }
            }
        });
        splitPane.setLeftComponent(treeScroll);
        myFrame.add(splitPane);
        myFrame.setSize(1000, 1000);
        myFrame.setVisible(true);
        
    }
    public static String createFilePath(TreePath treePath) {
        StringBuilder newPath = new StringBuilder();
        newPath.append("D:");
        Object[] nodes = treePath.getPath();
        for(int i=0;i<nodes.length;i++) {
            newPath.append(File.separatorChar).append(nodes[i].toString()); 
        } 
        return newPath.toString();
    }
   
    public static void createChildren(File fileRoot,DefaultMutableTreeNode node){
        File[] files = fileRoot.listFiles();
        
        for(File file : files){
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(file.getName());
            node.add(childNode);
        }
        
    }
    
    
}
