/**
 * AlgorithmPluginNode.java Created: 28.03.2014, 14:06:49
 */
package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.plugins.AlgorithmicPlugin;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Jan-Philipp Kappmeier
 */
public class AlgorithmPluginNode extends BatchTreeTableNode<AlgorithmicPlugin<?, ?>> {
	private static Icon disabled = new ImageIcon( "./icons/delete_24.png" );
	private static Icon enabled = new ImageIcon( "./icons/check_24.png" );

	public AlgorithmPluginNode( AlgorithmicPlugin<?, ?> algorithmicPlugin, boolean state ) {
		super( algorithmicPlugin, new String[0], state ? enabled : disabled );
	}

	public void setSelected( boolean b ) {
		setIcon( b ? enabled : disabled );
	}




}
