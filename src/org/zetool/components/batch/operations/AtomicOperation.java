
package org.zetool.components.batch.operations;

import org.zetool.components.batch.plugins.BatchAlgorithm;
import org.zetool.common.algorithm.Algorithm;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;


/**
 * An atomic operation transforms an input to an output and uses an algorithm
 * for that. The algorithm is undefined in the beginning and can be set.
 * @author Jan-Philipp Kappmeier
 * @param <U>
 * @param <V>
 */
public class AtomicOperation<U,V> implements Iterable<BatchAlgorithm<U,V>> {
	private final Class<U> accepts;
	private final Class<V> generates;
	private final LinkedHashSet<BatchAlgorithm<U,V>> availableAlgorithms = new LinkedHashSet<>();
	private final String name;

	private Algorithm<U,V> selectedAlgorithm;
	private U instance;
	private V solution;

	int index = -1;

	public AtomicOperation( String name, Class<U> accepts, Class<V> generates ) {
		this.name = name;
		this.accepts = accepts;
		this.generates = generates;

		//checkForPlugins();
	}

	@SuppressWarnings( "unchecked" )
	private void checkForPlugins() {
    throw new UnsupportedOperationException();
    //PluginManager pm = PluginManagerFactory.createPluginManager();
    
    //System.out.println( pm );

    
		//PluginManagerUtil pmu = new PluginManagerUtil( pm );
		//pm.addPluginsFrom( new File( "./plugins" ).toURI() );

//    Collection<BatchAlgorithm> plugins = null;// = pmu.getPlugins( BatchAlgorithm.class );

//    BatchAlgorithm<?,?> plugina = pm.getPlugin(BatchAlgorithm.class);
//    if( plugina == null ) {
//      System.err.println( "Plugin is null!" );
//    } else {
//      System.err.println( plugina.toString() );
//    }

    
//		for( BatchAlgorithm<?,?> plugin : plugins ) {
//			if( servedBy( plugin ) ) {
//				System.out.println( plugin );
//				availableAlgorithms.add((BatchAlgorithm<U,V>)plugin);
//			}
//		}
	}

  public final void registerAlgorithm( BatchAlgorithm<U,V> algorithm ) {
    availableAlgorithms.add( algorithm );
  }
  
  @SuppressWarnings("unchecked")
  public final void registerAlgorithmIfCompatible( BatchAlgorithm<?,?> algorithm ) {
    if( servedBy( algorithm ) ) {
      registerAlgorithm((BatchAlgorithm<U, V>)algorithm );
    }
  }
  
	public final boolean servedBy( BatchAlgorithm<?,?> plugin ) {
		if( plugin.canTake( accepts() ) ) {
			if( plugin.canGenerate( generates() ) ) {
				System.out.println( "Plugin " + plugin + " accepts " + toString() );
				return true;
			} else {

      }
		} else {

    }
		return false;
	}

	public void setIndex( int index ) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public Iterator<BatchAlgorithm<U,V>> iterator() {
		return availableAlgorithms.iterator();
	}

	public void addAvailable( BatchAlgorithm<U,V> plugin ) {
		availableAlgorithms.add( plugin );
	}

	public Algorithm<U, V> getSelectedAlgorithm() {
		return selectedAlgorithm;
	}

	public void setSelectedAlgorithm( Algorithm<?,?> selectedAlgorithm ) {
    // TODO: Necessary to have more error checks?
    // until this is checked, warning is not supressed
		this.selectedAlgorithm = (Algorithm<U,V>) selectedAlgorithm;
	}

	public void setInstance( U instance ) {
		this.instance = instance;
	}

	public V getSolution() {
		return solution;
	}

	public V run() {
		selectedAlgorithm.setProblem( instance );
		selectedAlgorithm.run();
		return selectedAlgorithm.getSolution();
	}

	public Class<U> accepts() {
		return accepts;
	}

	public Class<V> generates() {
		return generates;
	}

	@Override
	public String toString() {
		return name;
	}

	public int indexOf( BatchAlgorithm<?, ?> selectedPlugin ) {
    int listIndex = 0;
    for( BatchAlgorithm<?,?> plugin : availableAlgorithms ) {
      if( selectedPlugin.equals( plugin ) ) {
        return listIndex;        
      }
      listIndex++;
    }
    return -1;
	}

}
