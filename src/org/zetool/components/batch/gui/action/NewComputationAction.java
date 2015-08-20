
package org.zetool.components.batch.gui.action;

import org.zetool.components.batch.Computation;
import org.zetool.components.batch.gui.JBatch;
import java.awt.event.ActionEvent;

/**
 *
 * @author Martin Groß
 */
public class NewComputationAction extends BatchAction {

  public NewComputationAction( JBatch batch ) {
    super( batch, "New computation", "document_24.png" );
  }

  @Override
  public void actionPerformed( ActionEvent ae ) {
    Computation computation = new Computation();
    computation.setTitle( batch.getComputationList().generateGenericComputationTitle() );
    batch.addComputation( computation );
  }
}
