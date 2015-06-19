package org.zetool.components.batch.gui.action;

import org.zetool.coponents.batch.gui.JBatch;
import org.zetool.components.batch.input.Input;
import java.awt.event.ActionEvent;
import java.util.Objects;
import javax.swing.Icon;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class InputAction extends BatchAction {
  private Input input;

  public InputAction( JBatch batch, Input input, String title, Icon icon ) {
    super( batch, title, icon );
    this.input = Objects.requireNonNull( input );
    setEnabled( false );
  }

  @Override
  public void actionPerformed( ActionEvent ae ) {
    System.err.println( "Action performed" );
    batch.addInput( input );
  }
}
