
package org.zetool.components.batch.gui.action;

import org.zetool.components.batch.gui.JBatch;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Martin Groß
 */
public abstract class BatchAction extends AbstractAction {

    protected JBatch batch;
    
    public BatchAction(JBatch batch, String text, String icon) {
        super(text, new ImageIcon("./icons/"+icon));
        this.batch = batch;
    }
    
    public BatchAction(JBatch batch, String text, Icon icon ) {
        super(text, icon);
        this.batch = batch;
    }
    
    public JBatch getBatch() {
        return batch;
    }
}
