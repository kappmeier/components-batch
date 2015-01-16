
package org.zetool.components.batch.plugins;

import org.zetool.common.algorithm.Algorithm;
import net.xeoh.plugins.base.Plugin;


/**
 *
 * @author Jan-Philipp Kappmeier
 */
public interface AlgorithmicPlugin<U,V> extends Plugin {
	public String getName();

	public Class<U> accepts();


	public Class<V> generates();

	public default boolean canTake( Class<?> type ) {
		return type == accepts();
	}

	public default boolean canGenerate( Class<?> type ) {
		return type == generates();
	}

	/**
	 * Factory method generating an actual algorithm of the plugins type.
	 * @return an {@link Algorithm} instance
	 */
	public Algorithm<U,V> getAlgorithm();
}
