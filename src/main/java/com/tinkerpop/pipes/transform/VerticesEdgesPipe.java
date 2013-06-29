package com.tinkerpop.pipes.transform;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.AbstractPipe;
import com.tinkerpop.pipes.util.PipeHelper;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class VerticesEdgesPipe extends AbstractPipe<Vertex, Edge> implements TransformPipe<Vertex, Edge> {

    protected Direction direction;
    protected String[] labels;
    protected Iterator<Edge> nextEnds = PipeHelper.emptyIterator();

    public VerticesEdgesPipe(final Direction direction, final String... labels) {
        this.direction = direction;
        this.labels = labels;
    }

    public String[] getLabels() {
        return this.labels;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void reset() {
        this.nextEnds = PipeHelper.emptyIterator();
        super.reset();
    }


    protected Edge processNextStart() {
        while (true) {
            if (this.nextEnds.hasNext()) {
                return this.nextEnds.next();
            } else {
                this.nextEnds = this.starts.next().getEdges(this.direction, this.labels).iterator();
            }
        }
    }

    public String toString() {
        return PipeHelper.makePipeString(this, direction.name().toLowerCase(), Arrays.asList(labels));
    }
}
