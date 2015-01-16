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

  public ProblemType( String description, String properties ) {
    this( description, properties, new ImageIcon( "./icons/document_24.png" ) );
  }

  public ProblemType( String description, String properties, Icon icon ) {
    this.description = description;
    this.icon = icon;
    this.propertyNames = properties.split( "\\s*,\\s*" );
  }

  public String getDescription() {
    return description;
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
