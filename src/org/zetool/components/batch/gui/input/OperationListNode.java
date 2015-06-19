/**
 * OperationListNode.java
 * Created: 27.03.2014, 17:17:51
 */
package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.operations.Operation;
import org.zetool.components.batch.operations.OperationList;
import javax.swing.ImageIcon;


/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class OperationListNode extends BatchTreeTableNode<OperationList> {
    public OperationListNode(OperationList data) {
        super(data, new String[0], new ImageIcon("./icons/gear_24.png"));
        for (Operation operation : data) {
            add( new OperationNode( operation ));
        }
    }

    @Override
    public String toString() {
        return "Operations";
    }

}
