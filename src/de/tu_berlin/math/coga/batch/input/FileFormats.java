package de.tu_berlin.math.coga.batch.input;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Collects all file formats.
 *
 * @author Jan-Philipp Kappmeier
 */
public class FileFormats {
  private final LinkedList<FileFormat> knownFileFormats = new LinkedList<>();
  private final List<String> extensionList = new LinkedList<>();
  
  public void registerFileFormat( FileFormat f ) {
    knownFileFormats.add( f );
    extensionList.addAll( f.getExtensions() );
  }

  /**
   * Returns the file format for a given file. If the format cannot be
   * distinguished by the ending, the default unknown file format type is
   * returned.
   * @param file the file to be tested
   * @return the file format belonging to the file or the default unknown file
   */
  public FileFormat determineFileFormat( File file ) {
    int index = file.getName().lastIndexOf( "." );
    String ext = (index >= 0) ? file.getName().substring( file.getName().lastIndexOf( "." ) + 1 ) : "";
    for( FileFormat format : knownFileFormats ) {
      for( String extension : format.getExtensions() ) {
        if( ext.equals( extension ) ) {
          return format;
        }
      }
    }
    return FileFormat.UNKNOWN;
  }

  public List<String> getAllKnownExtensions() {
    return Collections.unmodifiableList( extensionList );
  }
}
