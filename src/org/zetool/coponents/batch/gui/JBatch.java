
package org.zetool.coponents.batch.gui;

import org.zetool.components.batch.plugins.AlgorithmicPlugin;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import org.zetool.components.batch.Computation;
import org.zetool.components.batch.ComputationList;
import org.zetool.components.batch.gui.action.NewComputationAction;
import org.zetool.components.batch.gui.action.OperationAction;
import org.zetool.components.batch.gui.action.RunComputationAction;
import org.zetool.components.batch.gui.action.StopComputationAction;
import org.zetool.components.batch.gui.input.AlgorithmPluginNode;
import org.zetool.components.batch.gui.input.ComputationListNode;
import org.zetool.components.batch.gui.input.ComputationNode;
import org.zetool.components.batch.gui.input.InputListNode;
import org.zetool.components.batch.gui.input.InputNode;
import org.zetool.components.batch.gui.input.OperationAlgorithmSelectNode;
import org.zetool.components.batch.input.InputFile;
import org.zetool.components.batch.input.InputList;
import org.zetool.components.batch.operations.AtomicOperation;
import org.zetool.components.batch.operations.Operation;
import org.zetool.components.batch.operations.OperationList;
import org.zetool.components.batch.gui.action.BatchAction;
import org.zetool.components.batch.input.Input;
import org.zetool.components.batch.gui.action.InputAction;
import org.zetool.components.batch.gui.action.OutputAction;
import org.zetool.components.batch.input.FileFormat;
import org.zetool.components.batch.input.FileFormats;
import org.zetool.components.batch.output.Output;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

/**
 *
 * @author Martin Groß
 * @author Jan-Philipp Kappmeier
 */
public class JBatch extends JPanel {

	private JInputView table;
	private final ComputationList computationList;
	private final InputSelectionListener selectionListener;
	private final InputKeyListener keyListener;

	private final JTaskPaneGroup inputPane = new JTaskPaneGroup();
  private final JTaskPaneGroup activityPane = new JTaskPaneGroup();
  private final JTaskPaneGroup outputPane = new JTaskPaneGroup();

  private final ArrayList<BatchAction> actions = new ArrayList<>();

  public JBatch() {
		super( new BorderLayout() );
		JTaskPane taskPaneContainer = new JTaskPane();
		// add JTaskPaneGroups to the container
		JTaskPaneGroup actionPane = new JTaskPaneGroup();
		actionPane.setTitle( "Computation" );
		actionPane.setSpecial( true );
		actionPane.add( new NewComputationAction( this ) );
		actionPane.add( new RunComputationAction( this ) );
		actionPane.add( new StopComputationAction( this ) );

		inputPane.setTitle( "Input" );
		inputPane.setSpecial( true );

    activityPane.setTitle( "Use cases" );
    activityPane.setSpecial( true );

    outputPane.setTitle( "Output" );
    outputPane.setSpecial( true );

		taskPaneContainer.add( actionPane );
		taskPaneContainer.add( inputPane );
		taskPaneContainer.add( activityPane );
		taskPaneContainer.add( outputPane );

    add( new JScrollPane( taskPaneContainer ), BorderLayout.WEST );

		table = new JInputView();
		table.getTree().addTreeSelectionListener( selectionListener = new InputSelectionListener() );
		table.getTree().addKeyListener( keyListener = new InputKeyListener() );

		table.getTree().addMouseListener( new MouseAdapter() {

			@Override
			public void mouseClicked( MouseEvent e ) {
				System.out.println( "Mouse klicked on tree." );
				if( e.getClickCount() == 2 ) {
					System.out.println( "Double click!!!" );
					System.out.println( "Column: " + table.getTree().getSelectedColumn() );
					System.out.println( "Row: " + table.getTree().getSelectedRow() );
					table.getTree().getTreeSelectionModel().getSelectionPaths();

					TreePath selectedPath = table.getTree().getTreeSelectionModel().getSelectionPath();
					System.out.println( selectedPath );
					Object p = selectedPath.getLastPathComponent();
					if( p instanceof AlgorithmPluginNode ) {
						AlgorithmPluginNode pluginNode = (AlgorithmPluginNode)p;
						AlgorithmicPlugin<?, ?> selectedPlugin = pluginNode.getUserObject();
						System.out.println( "Plugin: " + selectedPlugin );
						// Activate the node and deactivate all other!
						OperationAlgorithmSelectNode selectNode = (OperationAlgorithmSelectNode)pluginNode.getParent();

						int selectedIndex = selectNode.getUserObject().indexOf( selectedPlugin );
						selectNode.getUserObject().setIndex( selectedIndex );

						int index = 0;
						Enumeration<AlgorithmPluginNode> a = selectNode.children();
						while( a.hasMoreElements() ) {
							AlgorithmPluginNode otherPlugin = a.nextElement();
							if( index == selectedIndex ) {
								AtomicOperation<?, ?> ao = selectNode.getUserObject();
								AlgorithmicPlugin<?, ?> plugin = otherPlugin.getUserObject();
								ao.setSelectedAlgorithm( plugin.getAlgorithm() );

								otherPlugin.setSelected( true );
							} else
								otherPlugin.setSelected( false );
							index++;
						}
						repaint();
					}
				}
			}

		} );

		add( new JScrollPane( table ), BorderLayout.CENTER );

		PropertySheetPanel properties = new PropertySheetPanel();
		add( new JScrollPane( properties ), BorderLayout.EAST );

		computationList = new ComputationList();
		Computation computation = new Computation();
		computation.setTitle( computationList.generateGenericComputationTitle() );
		computationList.add( computation );
		table.setInput( computationList );
		Object root = table.getTree().getTreeTableModel().getRoot();
		Object child = table.getTree().getTreeTableModel().getChild( root, 0 );
		TreePath path = new TreePath( new Object[]{root, child} );
		table.getTree().getTreeSelectionModel().setSelectionPath( path );
	}

  public final void registerInputAction( Input input, String title, Icon icon ) {
    InputAction action = new InputAction(this, input, title, icon );
    actions.add( action );
    inputPane.add( action );
  }

  public final void registerOperationAction( Operation operation, String title ) {
    OperationAction action = new OperationAction( this, operation, title );
    actions.add( action );
    activityPane.add( action );
  }

  public final void registerOutputAction( Output output, String title, Icon icon ) {
    OutputAction action = new OutputAction( this, output, title, icon );
    actions.add( action );
    outputPane.add( action );
  }

  private FileFormats fileFormats = new FileFormats();
  
  public FileFormats getFileFormats() {
    return fileFormats;
  }
  
  public final void registerFileFormat( FileFormat fileFormat ) {
    fileFormats.registerFileFormat( fileFormat );
  }
  
  
  
  public void addInput( Input input ) {
    for( Computation computation : selectionListener.getSelectedComputations() ) {
			final InputList inputList = computation.getInputs();
			for( File file : input ) {
				final InputFile inputFile = new InputFile( file, fileFormats );
				if( !inputList.contains( inputFile ) ) {
					inputList.add( inputFile );
				}
			}
		}
		updateTreeTable();
  }

	public void addOperation( Operation operation ) {
		for( Computation computation : selectionListener.getSelectedComputations() ) {
			OperationList operations = computation.getOperations();
			operations.add( operation );
		}
		updateTreeTable();
	}

  public void addOutput( Output output ) {
		for( Computation computation : selectionListener.getSelectedComputations() ) {
      List<Output> outputs = computation.getOutputs();
			outputs.add( output );
		}
    updateTreeTable();
  }

  public void addComputation( Computation computation ) {
		computationList.add( computation );
		updateTreeTable();
	}

	public ComputationList getComputationList() {
		return computationList;
	}

	protected void updateTreeTable() {
		// the following is not used.
		int[] selected = table.getTree().getSelectedRows();
		List<TreePath> paths = new LinkedList<>();
		for( int i = 0; i < selected.length; i++ ) {
			TreePath path = table.getTree().getPathForRow( selected[i] );
			paths.add( path );
		}
		table.setInput( computationList );
		/*
		 for (TreePath path : paths) {
		 for (Object node : path.getPath()) {
		 table.getTree().getTreeTableModel().get
		 }
		 path.getPath()
		 System.out.println(path);
		 int row = table.getTree().getRowForPath(path);
		 System.out.println(row);
		 if (row >= 0) {
		 table.getTree().getSelectionModel().addSelectionInterval(row, row);
		 }
		 }
		 JTree tree;
		 System.out.println(table.getTree().getPathForRow(4));*/
	}

	private class InputKeyListener extends KeyAdapter {

		@Override
		public void keyPressed( final KeyEvent e ) {
			if( e.getKeyCode() == KeyEvent.VK_DELETE )
				for( TreePath path : selectionListener.getSelectedPaths() ) {
					Object leaf = path.getLastPathComponent();
					if( leaf instanceof InputNode && path.getPathComponent( path.getPathCount() - 3 ) instanceof InputListNode ) {
						InputFile file = ((InputNode)leaf).getUserObject();
						InputList list = ((InputListNode)path.getPathComponent( path.getPathCount() - 3 )).getInput();
						list.remove( file );
					}
					if( leaf instanceof ComputationNode && path.getPathComponent( path.getPathCount() - 2 ) instanceof ComputationListNode ) {
						Computation computation = ((ComputationNode)leaf).getUserObject();
						ComputationList list = ((ComputationListNode)path.getPathComponent( path.getPathCount() - 2 )).getComputations();
						list.remove( computation );
					}
				}
			updateTreeTable();
		}
	}

	private class InputSelectionListener implements TreeSelectionListener {

		private final ComputationList selectedComputations;

		public InputSelectionListener() {
			selectedComputations = new ComputationList();
		}

		public ComputationList getSelectedComputations() {
			return selectedComputations;
		}

		public TreePath[] getSelectedPaths() {
			return table.getTree().getTreeSelectionModel().getSelectionPaths();
		}

		@Override
		public void valueChanged( TreeSelectionEvent e ) {
			selectedComputations.clear();
			TreePath[] paths = table.getTree().getTreeSelectionModel().getSelectionPaths();
			for( TreePath path : paths )
				for( Object object : path.getPath() )
					if( object instanceof ComputationNode )
						selectedComputations.add( ((ComputationNode)object).getComputation() );

      for( BatchAction b : actions )
        b.setEnabled( !selectedComputations.isEmpty() );
		}
	}
}
