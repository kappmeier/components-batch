package org.zetool.components.batch.input;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Collects all file formats.
 *
 * @author Jan-Philipp Kappmeier
 */
public class FileFormats {
  private final LinkedList<FileFormat> knownFileFormats = new LinkedList<>();
  private final List<String> extensionList = new LinkedList<>();
  private final Set<ProblemType> solvableTypes = new HashSet<>();
  
  public void registerFileFormat( FileFormat f ) {
    knownFileFormats.add( f );
    extensionList.addAll( f.getExtensions() );
    solvableTypes.add( f.getProblemType() );
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

  public List<String> getExtensions( ProblemType problemType ) {
    List<String> extensions;
    //if( extensions == null ) {
      extensions = new LinkedList<>();
      for( FileFormat format : knownFileFormats ) { //FileFormatEnum.values() ) {
        if( format.getProblemType() == problemType ) {
          //extensions.addAll( Arrays.asList( format.getExtensions() ) );
          extensions.addAll( format.getExtensions() );
        }
      }
      //fileFilter = new FileNameExtensionFilter( description, extensions.toArray( new String[0] ) );
    //}
    return extensions;
  }

  public FileFilter getFileFilter( ProblemType problemType ) {
    FileFilter fileFilter;
    //if( fileFilter == null ) {
      //extensions = new LinkedList<>();
      //for( FileFormat format : formats ) { //FileFormatEnum.values() ) {
      //  if( format.getProblemType() == this ) {
      //    extensions.addAll( format.getExtensions() );
      //  }
      //}
      //fileFilter = new FileNameExtensionFilter( description, extensions.toArray( new String[0] ) );
    fileFilter = new FileNameExtensionFilter( problemType.getDescription(), getExtensions( problemType ).toArray( new String[0] ) );
    
    return fileFilter;
  }

  public List<String> getAllKnownExtensions() {
    return Collections.unmodifiableList( extensionList );
  }

  public Iterable<ProblemType> getKnownTypes() {
    return solvableTypes;
  }
}
