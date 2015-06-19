package org.zetool.components.coga.batch;

import org.zetool.coponents.batch.gui.JBatch;
import org.zetool.components.batch.input.FileFormat;
import org.zetool.components.batch.input.InputDirectory;
import org.zetool.components.batch.input.InputFiles;
import org.zetool.components.batch.input.ProblemType;
import org.zetool.components.batch.input.reader.InputFileReader;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class TestWindow {

  static ProblemType EVACUATION_PROJECT = new ProblemType( "Evacuation Project", "Number of Floors, Number of Exits, Maximal Number of Evacuees" );
  static ProblemType MAXIMUM_FLOW = new ProblemType( "Maximum Flow Problem", "Number of Nodes, Number of Edges" );
  static ProblemType MINIMUM_COST_FLOW = new ProblemType( "Minimum Cost Flow Problem", "Number of Nodes, Number of Edges, Total Supply" );

  static FileFormat DIMACS_MAXIMUM_FLOW = new FileFormat( MAXIMUM_FLOW, TestInputFileReader.class, "DIMACS Maximum Flow Problem", "max" );
  static FileFormat DIMACS_MINIMUM_COST_FLOW = new FileFormat( MINIMUM_COST_FLOW, TestInputFileReader.class, "DIMACS Minimum Cost Flow Problem", "min", "net" );
  static FileFormat RMFGEN_MAXIMUM_FLOW = new FileFormat( MAXIMUM_FLOW, TestInputFileReader.class, "RMFGEN Maximum Flow Problem", "rmf" );
  static FileFormat ZET_PROJECT = new FileFormat( EVACUATION_PROJECT, TestInputFileReader.class, "ZET Evacuation Project", new ImageIcon( "./icons/zet_24.png" ), "zet" );
  
  
  public static void main( String args[] ) {
    SwingUtilities.invokeLater( new Runnable() {
      @Override
      public void run() {
        JFrame frame = new JFrame( "File tree" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 500, 400 );
        frame.setLocationRelativeTo( null );
        frame.setLayout( new BorderLayout() );

        JBatch b = new JBatch();
        frame.add( b, BorderLayout.CENTER );
        
        // Register some file types!
        b.registerFileFormat( DIMACS_MAXIMUM_FLOW );
        
        b.registerInputAction( new InputFiles( b, b ), "Add input file(s)", new ImageIcon("./icons/document_add_24.png" ) );
        b.registerInputAction( new InputDirectory( b, b ), "Add input directory", new ImageIcon("./icons/folder_add_24.png" ) );
        
        
        
        frame.setVisible( true );
      }
    } );

  }

  public static class TestInputFileReader extends InputFileReader<Object> {

    @Override
    public Class<Object> getTypeClass() {
      throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getProperties() {
      throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object runAlgorithm( File problem ) {
      throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

  }

}
