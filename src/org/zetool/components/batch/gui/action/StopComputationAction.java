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
public class StopComputationAction extends BatchAction {

    public StopComputationAction(JBatch batch) {
        super(batch, "Stop computation", "close_24.png");
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }   
    
}
