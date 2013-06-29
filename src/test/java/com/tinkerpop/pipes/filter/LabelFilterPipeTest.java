package com.tinkerpop.pipes.filter;

import com.tinkerpop.blueprints.Compare;
import com.tinkerpop.blueprints.Contains;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Query;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraphFactory;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author: Marko A. Rodriguez (http://markorodriguez.com)
 */
public class LabelFilterPipeTest extends TestCase {

    public void testFilterLabels() {
        Graph graph = TinkerGraphFactory.createTinkerGraph();
        Vertex marko = graph.getVertex("1");
        LabelFilterPipe lfp = new LabelFilterPipe(Compare.EQUAL, "knows");
        lfp.setStarts(marko.getEdges(Direction.OUT).iterator());
        assertTrue(lfp.hasNext());
        int counter = 0;
        while (lfp.hasNext()) {
            Edge e = lfp.next();
            assertEquals(e.getVertex(Direction.OUT), marko);
            assertTrue(e.getVertex(Direction.IN).getId().equals("2") || e.getVertex(Direction.IN).getId().equals("4"));
            counter++;
        }
        assertEquals(counter, 2);

        lfp = new LabelFilterPipe(Contains.NOT_IN, Arrays.asList("knows","blah"));
        lfp.setStarts(marko.getEdges(Direction.OUT).iterator());
        assertTrue(lfp.hasNext());
        counter = 0;
        while (lfp.hasNext()) {
            Edge e = lfp.next();
            assertEquals(e.getVertex(Direction.OUT), marko);
            assertTrue(e.getVertex(Direction.IN).getId().equals("3"));
            counter++;
        }
        assertEquals(counter, 1);
    }
}
