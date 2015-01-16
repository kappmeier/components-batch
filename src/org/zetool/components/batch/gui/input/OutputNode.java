package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.output.Output;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class OutputNode extends BatchTreeTableNode<Output> {

  public OutputNode( Output output ) {
    super( output, output.getIcon() );
  }

  @Override
  public String getToolTipText() {
    return getUserObject().getDescription();
  }
}
