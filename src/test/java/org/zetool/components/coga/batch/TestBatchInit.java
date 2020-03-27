package org.zetool.components.coga.batch;

import static org.zetool.components.coga.batch.TestWindow.DIMACS_MAXIMUM_FLOW;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.ImageIcon;

import org.junit.Test;

import org.zetool.components.batch.gui.JBatch;
import org.zetool.components.batch.input.Input;
import org.zetool.components.batch.input.InputFiles;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class TestBatchInit {

    /**
     * Integration test checking if loading a file works.
     *
     * @throws java.io.IOException
     */
    @Test
    public void loadMatchingFile() throws IOException {
        // Initialize a batch object,
        // define input reader
        // define an input file first

        final File temp = File.createTempFile("temp", ".max");
        temp.deleteOnExit();

        final Input testFileInput = () -> {
            return Arrays.asList(temp).iterator();
        };

        final JBatch batch = new JBatch();

        // Register some file types!
        batch.registerFileFormat(DIMACS_MAXIMUM_FLOW);
        batch.registerInputAction(new InputFiles(batch, batch), "Add input file(s)", new ImageIcon("./icons/document_add_24.png"));
        try {
            batch.addInput(testFileInput);
        } catch (UnsupportedOperationException ex) {
            // fine, should throw exception!
            return;
        }
        throw new AssertionError("No exception thrown for input of a file!");
    }

    /**
     * Integration test checking if loading a file works.
     *
     * @throws java.io.IOException
     */
    @Test
    public void tryLoadingNonMatchingFile() throws IOException {
        // Initialize a batch object,
        // define input reader
        // define an input file first

        final File temp = File.createTempFile("temp", ".tmp");
        temp.deleteOnExit();

        final Input testFileInput = () -> {
            return Arrays.asList(temp).iterator();
        };

        final JBatch batch = new JBatch();

        // Register some file types!
        batch.registerFileFormat(DIMACS_MAXIMUM_FLOW);
        batch.registerInputAction(new InputFiles(batch, batch), "Add input file(s)", new ImageIcon("./icons/document_add_24.png"));
        batch.addInput(testFileInput);
        //nothing should happen, because the file type does not match
    }
}
