
package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.operations.AtomicOperation;
import org.zetool.components.batch.operations.Operation;
import javax.swing.ImageIcon;


/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class OperationNode extends BatchTreeTableNode<Operation<?,?>> {
    public OperationNode(Operation<?,?> operation) {
      super( operation, new String[0], new ImageIcon("./icons/algo_24.png") );
			for( AtomicOperation<?,?> ao : operation.getAtomicOperations() ) {
				add(new OperationAlgorithmSelectNode( ao ) );
			}
    }
}
