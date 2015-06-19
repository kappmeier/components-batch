
package org.zetool.components.batch.gui.action;

import org.zetool.coponents.batch.gui.JBatch;
import org.zetool.components.batch.output.Output;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.Icon;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class OutputAction extends BatchAction {
  private Output output;

  public OutputAction( JBatch batch, Output output, String title, Icon icon ) {
    super( batch, title, icon );
    this.output = Objects.requireNonNull( output );
    setEnabled( false );
  }

  @Override
  public void actionPerformed( ActionEvent ae ) {
    batch.addOutput( output );
  }
}
