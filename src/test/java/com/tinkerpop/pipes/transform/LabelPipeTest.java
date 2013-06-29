package com.tinkerpop.pipes.transform;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraphFactory;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class LabelPipeTest extends TestCase {

    public void testLabels() {
        Graph graph = TinkerGraphFactory.createTinkerGraph();
        LabelPipe pipe = new LabelPipe();
        pipe.setStarts(graph.getVertex("1").getEdges(Direction.OUT).iterator());
        Assert.assertTrue(pipe.hasNext());
        int counter = 0;
        while (pipe.hasNext()) {
            String label = pipe.next();
            Assert.assertTrue(label.equals("knows") || label.equals("created"));
            counter++;
        }
        Assert.assertEquals(counter, 3);
    }
}
