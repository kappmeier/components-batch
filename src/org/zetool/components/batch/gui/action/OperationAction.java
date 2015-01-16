
package org.zetool.components.batch.gui.action;

import org.zetool.coponents.batch.gui.JBatch;
import org.zetool.components.batch.operations.Operation;
import java.awt.event.ActionEvent;
import java.util.Objects;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class OperationAction extends BatchAction {

  private Operation operation;
  private String title;

  public OperationAction( JBatch batch, Operation operation, String title ) {
    super( batch, title, "algo_24.png" );
    this.operation = Objects.requireNonNull( operation );
    System.out.println( "Creating operation action '" + operation + "' with title '" + title + "'" );
    setEnabled( false );
    this.title = title;
  }

  @Override
  public void actionPerformed( ActionEvent ae ) {
    System.out.println( "Adding operation '" + operation + "' with title '" + title + "'" );
    batch.addOperation( operation );
  }

}
