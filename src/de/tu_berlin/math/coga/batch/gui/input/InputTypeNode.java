
package de.tu_berlin.math.coga.batch.gui.input;

import de.tu_berlin.math.coga.batch.input.ProblemType;

/**
 *
 * @author Martin Groß
 */
public class InputTypeNode extends BatchTreeTableNode<ProblemType> {

    public InputTypeNode(ProblemType problemType, String[] propertyNames) {
        super(problemType, propertyNames, problemType.getIcon());
    }
}
