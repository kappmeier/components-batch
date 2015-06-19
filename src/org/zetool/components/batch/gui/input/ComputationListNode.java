package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.Computation;
import org.zetool.components.batch.ComputationList;

/**
 *
 * @author Martin Groß
 */
public class ComputationListNode extends BatchTreeTableNode<ComputationList> {

  public ComputationListNode( ComputationList computations ) {
    super( computations, new String[0] );
    for( Computation computation : computations ) {
      add( new ComputationNode( computation ) );
    }
  }

  public ComputationList getComputations() {
    return getUserObject();
  }
}
