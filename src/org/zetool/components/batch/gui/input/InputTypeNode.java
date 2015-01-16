
package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.input.ProblemType;

/**
 *
 * @author Martin Groß
 */
public class InputTypeNode extends BatchTreeTableNode<ProblemType> {

    public InputTypeNode(ProblemType problemType, String[] propertyNames) {
        super(problemType, propertyNames, problemType.getIcon());
    }
}
