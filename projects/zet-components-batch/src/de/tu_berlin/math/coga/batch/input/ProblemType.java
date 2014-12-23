package de.tu_berlin.math.coga.batch.input;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class ProblemType {
  public final static ProblemType UNSPECIFIED = new ProblemType("Unspecified Problem", "");

  private final String description;
  private List<String> extensions;
  private FileFilter fileFilter;
  private final Icon icon;
  private final String[] propertyNames;

  private ProblemType( String description, String properties ) {
    this( description, properties, new ImageIcon( "./icons/document_24.png" ) );
  }

  private ProblemType( String description, String properties, Icon icon ) {
    this.description = description;
    this.icon = icon;
    this.propertyNames = properties.split( "\\s*,\\s*" );
  }

  public String getDescription() {
    return description;
  }

  public List<String> getExtensions() {
    if( extensions == null ) {
      extensions = new LinkedList<>();
      for( FileFormatEnum format : FileFormatEnum.values() ) {
        if( format.getProblemType() == this ) {
          extensions.addAll( Arrays.asList( format.getExtensions() ) );
        }
      }
      fileFilter = new FileNameExtensionFilter( description, extensions.toArray( new String[0] ) );
    }
    return extensions;
  }

  public FileFilter getFileFilter() {
    if( fileFilter == null ) {
      extensions = new LinkedList<>();
      for( FileFormatEnum format : FileFormatEnum.values() ) {
        if( format.getProblemType() == this ) {
          extensions.addAll( Arrays.asList( format.getExtensions() ) );
        }
      }
      fileFilter = new FileNameExtensionFilter( description, extensions.toArray( new String[0] ) );
    }
    return fileFilter;
  }

  public Icon getIcon() {
    return icon;
  }

  public String[] getPropertyNames() {
    return propertyNames;
  }

  @Override
  public String toString() {
    return description;
  }
}
