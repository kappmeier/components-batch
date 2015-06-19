/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.algorithm.AlgorithmList;
import org.zetool.components.batch.input.InputAlgorithm;
import javax.swing.ImageIcon;

/**
 *
 * @author gross
 */
public class AlgorithmListNode extends BatchTreeTableNode<AlgorithmList> {

    public AlgorithmListNode(AlgorithmList data) {
        super(data, new String[0], new ImageIcon("./icons/gear_24.png"));
        for (InputAlgorithm algorithm : data) {
            add(new InputAlgorithmNode(algorithm));
        }      
    }

    @Override
    public String toString() {
        return "Algorithms";
    }   
}
