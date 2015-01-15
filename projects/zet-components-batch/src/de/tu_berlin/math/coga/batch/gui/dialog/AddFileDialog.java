
package de.tu_berlin.math.coga.batch.gui.dialog;

import de.tu_berlin.math.coga.batch.input.ProblemType;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JFileChooser;

/**
 *
 * @author Martin Groß
 */
public class AddFileDialog extends JFileChooser {
    
    private ProblemType problemType;
    private LinkedList<ProblemType> knownTypes;
    
    public AddFileDialog(ProblemType problemType) {
        super();
        File directory = new File(System.getProperty("user.dir") + "/examples");
        if (!directory.exists()) {
            directory = new File(System.getProperty("user.dir"));
        }
        setCurrentDirectory(directory);
        this.problemType = problemType;
        System.out.println( "Problem type: " + problemType );
        for (ProblemType type : knownTypes ) {
            if (type == ProblemType.UNSPECIFIED ) {
                continue;
            }
            addChoosableFileFilter(type.getFileFilter());            
        }
        setAcceptAllFileFilterUsed(true);
        setFileSelectionMode(JFileChooser.FILES_ONLY);
        //setFileFilter(problemType.getFileFilter());
        setDialogTitle("Select input file(s)...");
        setMultiSelectionEnabled(true);        
    }

    public AddFileDialog() {
        this(null);
    }

    public ProblemType getProblemType() {
        return problemType;
    }

    public void setProblemType(ProblemType problemType) {
        this.problemType = problemType;
        if (problemType != null) {
            setFileFilter(problemType.getFileFilter());
        }
    }
}
