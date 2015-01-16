
package de.tu_berlin.math.coga.batch.gui.dialog;

import de.tu_berlin.math.coga.batch.input.FileFormats;
import de.tu_berlin.math.coga.batch.input.ProblemType;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author Martin Groß
 * @author Jan-Philipp Kappmeier
 */
public class AddFileDialog extends JFileChooser {
    public AddFileDialog( FileFormats formats ) {
        super();
        File directory = new File(System.getProperty("user.dir") + "/examples");
        if (!directory.exists()) {
            directory = new File(System.getProperty("user.dir"));
        }
        setCurrentDirectory(directory);
        //this.problemType = problemType;
        //System.out.println( "Problem type: " + problemType );
        //for (ProblemType type : problemTypes ) {
        for (ProblemType type : formats.getKnownTypes() ) {
            if (type == ProblemType.UNSPECIFIED ) {
                continue;
            }
            addChoosableFileFilter( formats.getFileFilter( type ) );
            //addChoosableFileFilter(type.getFileFilter( ));
        }
        setAcceptAllFileFilterUsed(true);
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        //setFileFilter(problemType.getFileFilter());
        setDialogTitle("Select input file(s)...");
        setMultiSelectionEnabled(true);        
    }

    //public AddFileDialog() {
    //    this(null);
    //}

    //public ProblemType getProblemType() {
    //    return problemType;
    //}

    //public void setProblemType(ProblemType problemType) {
        //this.problemType = problemType;
        //if (problemType != null) {
    //        setFileFilter( formats.getFileFilter( problemType ) );
        //}
    //}
}
