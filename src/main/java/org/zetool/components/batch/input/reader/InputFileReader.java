/* zet evacuation tool copyright (c) 2007-15 zet evacuation team
 *
 * This program is free software; you can redistribute it and/or
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.zetool.components.batch.input.reader;

import java.io.File;
import org.zetool.common.datastructure.parameter.ParameterSet;
import org.zetool.common.datastructure.parameter.Parameter;
import org.zetool.common.algorithm.AbstractAlgorithm;
import org.zetool.common.algorithm.template.DefaultParameterTemplateSet;
import org.zetool.common.algorithm.template.ParameterTemplateSet;

/**
 * The abstract base class for algorithms that read instances out of files.
 *
 * @author Martin Groß
 * @param <T>
 */
public abstract class InputFileReader<T> extends AbstractAlgorithm<File, T> {

    public abstract Class<T> getTypeClass();

    /**
     * An enumeration type that specifies whether the reader should try to optimize its runtime or its memory
     * consumption.
     */
    public enum OptimizationHint {
        SPEED, MEMORY;
    }

    private final static String OPTIMIZATION = "Optimization";

    /**
     * Stores the optimization goal.
     */
    private Parameter<OptimizationHint> optimizationHint;

    /**
     * Creates a new input file reader and creates a parameter to specify an optimization goal by the user. It is up to
     * subclasses to pay heed to this, though.
     */
    protected InputFileReader() {
        super();
        optimizationHint = new Parameter<>(OPTIMIZATION, OptimizationHint.SPEED);
    }

    private final static ParameterTemplateSet tps = new DefaultParameterTemplateSet.Builder().withParameter(
            OPTIMIZATION, "Specifies whether the reader should try to conserve runtime or memory",
            OptimizationHint.SPEED, (Object[]) OptimizationHint.values()).build();

    /**
     * Returns the template set with the parameters for file reading. The available optimization parameters include
     * {@link OptimizationHint} specifying whether the loaded datastructure created from the input file should be
     * optimized for speed or memory size.
     *
     * @return the supported parameters by the algorithm
     */
    @Override
    public ParameterTemplateSet getParameters() {
        return tps;
    }

    @Override
    public void setParameterSet(ParameterSet parameterSet) {
        super.setParameterSet(parameterSet);
        optimizationHint = (Parameter<OptimizationHint>) parameterSet.stream()
                .filter(parameter -> parameter.getName().equals(OPTIMIZATION))
                .findAny().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Mnemonic shortcut for <code>getProblem()</code>.
     *
     * @return the input file of the reader.
     */
    public File getFile() {
        return getProblem();
    }

    /**
     * Mnemonic shortcut for <code>setProblem(File file)</code>.
     *
     * @param file the input file for the reader.
     */
    public void setFile(File file) {
        setProblem(file);
    }

    /**
     * Returns the optimization hint for this reader, i.e. whether memory should be preserved or runtime speed. Note
     * that this is only a hint for subclasses.
     *
     * @return the optimization hint.
     */
    public OptimizationHint getOptimization() {
        return optimizationHint.getValue();
    }

    /**
     * Sets the optimization hint for this problem.
     *
     * @param hint the optimization hint.
     */
    public void setOptimization(OptimizationHint hint) {
        optimizationHint = new Parameter<>(OPTIMIZATION, hint);
    }

    /**
     * Abstract method that returns an array of String properties with basic information about the input file. The
     * specifics depend on the sub-classing reader and the file format it is for. Usually the properties contain
     * information about the size of the instance, and can be obtained quickly by the reader, i.e. without parsing the
     * whole file but only its header.
     *
     * @return the properties of the input file.
     */
    public abstract String[] getProperties();
}
