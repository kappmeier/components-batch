package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.input.InputFile;

/**
 *
 * @author Martin Groß
 */
public class InputNode extends BatchTreeTableNode<InputFile> {

    public InputNode(InputFile input) {
        super(input, input.getProperties(), input.getIcon() );
    }

    @Override
    public String getToolTipText() {
        return getUserObject().getDescription();
    }
}
