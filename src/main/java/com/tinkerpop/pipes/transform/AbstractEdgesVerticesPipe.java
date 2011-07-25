package com.tinkerpop.pipes.transform;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.pipes.AbstractPipe;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public abstract class AbstractEdgesVerticesPipe extends AbstractPipe<Vertex, Vertex> {

    protected Iterator<Edge> nextEnds;
    protected final String[] labels;

    public AbstractEdgesVerticesPipe(final String... labels) {
        this.labels = labels;
    }

    public String toString() {
        if (this.labels.length == 0) {
            return super.toString();
        } else {
            return super.toString() + "(" + Arrays.asList(this.labels) + ")";
        }
    }

    public void reset() {
        this.nextEnds = null;
        super.reset();
    }
}