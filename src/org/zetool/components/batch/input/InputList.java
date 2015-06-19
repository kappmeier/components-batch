
package org.zetool.components.batch.input;

import org.zetool.components.batch.Computation;
import java.util.LinkedList;

/**
 *
 * @author Martin Groß
 */
public class InputList extends LinkedList<InputFile> {

    private Computation computation;
    
    public InputList(Computation computation) {
        this.computation = computation;
    }

    public Computation getComputation() {
        return computation;
    }
    
    public String getText() {
        return computation.getType().getDescription();
    }
}
