/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zetool.components.batch.gui.action;

import org.zetool.coponents.batch.gui.JBatch;
import java.awt.event.ActionEvent;

/**
 *
 * @author gross
 */
public class RemoveInputFilesAction extends BatchAction {

    public RemoveInputFilesAction(JBatch batch) {
        super(batch, "Remove input", "document_delete_24.png");
    }   
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
