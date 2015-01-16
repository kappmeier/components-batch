package org.zetool.components.batch;

import org.zetool.components.batch.algorithm.AlgorithmList;
import org.zetool.components.batch.input.InputList;
import org.zetool.components.batch.input.ProblemType;
import org.zetool.components.batch.operations.OperationList;
import org.zetool.components.batch.output.Output;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Groß
 * @author Jan-Philipp Kappmeier
 */
public class Computation {

  private AlgorithmList algorithms;
  private OperationList operations;
  private LinkedList<Output> outputs;
  private InputList input;
  private String title;
  private ProblemType type;

  public Computation( ProblemType problemType ) {
    this.type = problemType;
    this.algorithms = new AlgorithmList();
    this.input = new InputList( this );
    this.operations = new OperationList();
    outputs = new LinkedList<>();
    this.title = "Computation";
  }

  public Computation() {
    this( null );
  }

  public String getTitle() {
    return title;
  }

  public void setTitle( String title ) {
    this.title = title;
  }

  public ProblemType getType() {
    return type;
  }

  public void setType( ProblemType type ) {
    this.type = type;
  }

  public AlgorithmList getAlgorithms() {
    return algorithms;
  }

  public InputList getInputs() {
    return input;
  }

  public OperationList getOperations() {
    return operations;
  }

  public List<Output> getOutputs() {
    return outputs;
  }
}
