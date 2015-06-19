package org.zetool.components.batch.gui.input;

import org.zetool.components.batch.input.InputFile;
import org.zetool.components.batch.input.InputList;
import org.zetool.components.batch.input.ProblemType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.ImageIcon;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;

/**
 *
 * @author Martin Groß
 */
public class InputListNode extends BatchTreeTableNode<InputList> {

  private int currentSortIndex = 0;
  private boolean ascending = true;

  private Map<ProblemType, InputTypeNode> typeNodes;

  public InputListNode( InputList input ) {
    super( input, new String[0], new ImageIcon( "./icons/folder_24.png" ) );
    typeNodes = new HashMap<>();
    for( InputFile file : input ) {
      if( !typeNodes.containsKey( file.getProblemType() ) ) {
        InputTypeNode node = new InputTypeNode( file.getProblemType(), file.getPropertyNames() );
        add( node );
        typeNodes.put( file.getProblemType(), node );
      }
      InputTypeNode node = typeNodes.get( file.getProblemType() );
      node.add( new InputNode( file ) );
    }
    sort();
  }

  @Override
  public int getColumnCount() {
    int max = 0;
    for( ProblemType type : typeNodes.keySet() ) {
      if( max < type.getPropertyNames().length ) {
        max = type.getPropertyNames().length;
      }
    }
    return 1 + max;
  }

  public InputList getInput() {
    return (InputList)getUserObject();
  }

  private void sort() {
    for( Iterator<MutableTreeTableNode> it = children.iterator(); it.hasNext(); ) {
      InputTypeNode child = (InputTypeNode)it.next();
      child.sort( currentSortIndex, ascending );
    }
  }

  public void sort( int column ) {
    if( column == currentSortIndex ) {
      ascending = !ascending;
    }
    for( Iterator<MutableTreeTableNode> it = children.iterator(); it.hasNext(); ) {
      InputTypeNode child = (InputTypeNode)it.next();
      child.sort( column, ascending );
    }
    currentSortIndex = column;
  }

  @Override
  public String toString() {
    return "Input";
  }
}
