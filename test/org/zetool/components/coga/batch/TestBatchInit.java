package org.zetool.components.coga.batch;

import static org.zetool.components.coga.batch.TestWindow.DIMACS_MAXIMUM_FLOW;
import org.zetool.coponents.batch.gui.JBatch;
import org.zetool.components.batch.input.Input;
import org.zetool.components.batch.input.InputFiles;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.ImageIcon;
import org.junit.Test;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class TestBatchInit {

  /**
   * Integration test checking if loading a file works.
   * @throws java.io.IOException
   */
  @Test
  public void testLoadMatchingFile() throws IOException {
    // Initialize a batch object,
    // define input reader
    // define an input file first
    
    final File temp = File.createTempFile( "temp", ".max" );
    temp.deleteOnExit();
    
    final Input testFileInput = () -> {
      return Arrays.asList( temp ).iterator();
    };

    final JBatch b = new JBatch();

    // Register some file types!
    b.registerFileFormat( DIMACS_MAXIMUM_FLOW );
    b.registerInputAction( new InputFiles( b, b ), "Add input file(s)", new ImageIcon( "./icons/document_add_24.png" ) );      
    try {
      b.addInput( testFileInput );      
    } catch( UnsupportedOperationException ex ) {
      // fine, should throw exception!
      return;
    }
    throw new AssertionError( "No exception thrown for input of a file!" );
  }

  /**
   * Integration test checking if loading a file works.
   * @throws java.io.IOException
   */
  @Test
  public void testLoadNonMatchingFile() throws IOException {
    // Initialize a batch object,
    // define input reader
    // define an input file first
    
    final File temp = File.createTempFile( "temp", ".tmp" );
    temp.deleteOnExit();
    
    final Input testFileInput = () -> {
      return Arrays.asList( temp ).iterator();
    };

    final JBatch b = new JBatch();

    // Register some file types!
    b.registerFileFormat( DIMACS_MAXIMUM_FLOW );
    b.registerInputAction( new InputFiles( b, b ), "Add input file(s)", new ImageIcon( "./icons/document_add_24.png" ) );      
    b.addInput( testFileInput );      
    //nothing should happen, because the file type does not match
  }
}
