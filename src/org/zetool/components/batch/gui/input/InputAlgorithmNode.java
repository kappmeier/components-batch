/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.input.InputAlgorithm;
import javax.swing.ImageIcon;

/**
 *
 * @author gross
 */
public class InputAlgorithmNode extends BatchTreeTableNode<InputAlgorithm> {

    public InputAlgorithmNode(InputAlgorithm algorithm) {
        super(algorithm, new String[0], new ImageIcon("./icons/algo_24.png"));
    }
}
