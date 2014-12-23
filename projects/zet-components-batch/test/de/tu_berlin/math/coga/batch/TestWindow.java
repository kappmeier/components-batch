package de.tu_berlin.math.coga.batch;

import de.tu_berlin.math.coga.batch.gui.JBatch;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class TestWindow {

  public static void main( String args[] ) {
    SwingUtilities.invokeLater( new Runnable() {
      @Override
      public void run() {
        JFrame frame = new JFrame( "File tree" );
        frame.setSize( 500, 400 );
        frame.setLocationRelativeTo( null );
        frame.setLayout( new BorderLayout() );

        JBatch b = new JBatch();
        frame.add( b, BorderLayout.CENTER );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
      }
    } );

  }

}
