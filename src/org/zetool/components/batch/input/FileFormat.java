package org.zetool.components.batch.input;

import org.zetool.components.batch.input.reader.InputFileReader;
import org.zetool.components.batch.input.reader.UnknownFileReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Specifies a file format that is supported by the batch view window.
 * @author Jan-Philipp Kappmeier
 */
public class FileFormat {

  private final String description;
  private final Collection<String> extensions = new LinkedList<>();
  private final Icon icon;
  private final ProblemType problemType;
  private final Class<? extends InputFileReader> reader;

  public final static FileFormat UNKNOWN = new FileFormat( ProblemType.UNSPECIFIED, UnknownFileReader.class, "Unknown File Format" );
  
  public FileFormat( ProblemType problemType, Class<? extends InputFileReader> reader, String description, String... extensions ) {
    this( problemType, reader, description, new ImageIcon( "./icons/document_24.png" ), extensions );
  }

  
  public FileFormat( ProblemType problemType, Class<? extends InputFileReader> reader, String description, Icon icon, String... extensions ) {
    this.description = description;
    this.extensions.addAll( Arrays.asList( extensions ) );
    this.icon = icon;
    this.problemType = problemType;
    this.reader = reader;
  }

  public String getDescription() {
    return description;
  }

  public Collection<String> getExtensions() {
    return extensions;
  }

  public Icon getIcon() {
    return icon;
  }

  public ProblemType getProblemType() {
    return problemType;
  }

  public Class<? extends InputFileReader> getReader() {
    return reader;
  }
}
