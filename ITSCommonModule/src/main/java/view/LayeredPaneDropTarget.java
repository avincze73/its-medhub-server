/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;

/**
 *
 * @author vincze.attila
 */
public class LayeredPaneDropTarget extends JLayeredPane implements DropTargetListener {

    private String demo;
    protected DropTarget dropTarget;

    public LayeredPaneDropTarget() {
        super();
        dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE,
                this, true, null);
    }

    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.println("enter");
    }

    public void dragOver(DropTargetDragEvent dtde) {
        //System.out.println("dragOver");
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        System.out.println("dropActionChanged");
    }

    public void dragExit(DropTargetEvent dte) {
        System.out.println("dragExit");

    }

    public void drop(DropTargetDropEvent dtde) {
        System.out.println("drop");
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            try {
// Accept the drop and get the transfer data

                dtde.acceptDrop(dtde.getDropAction());
                Transferable transferable = dtde.getTransferable();
                System.out.println(transferable.getTransferData(DataFlavor.stringFlavor));
                try {
                    dtde.dropComplete(true);
                } catch (Exception e) {
                    dtde.dropComplete(false);
                }
            } catch (UnsupportedFlavorException ex) {
                Logger.getLogger(LayeredPaneDropTarget.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LayeredPaneDropTarget.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dtde.rejectDrop();
        }

    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }
}
