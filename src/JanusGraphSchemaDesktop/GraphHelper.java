package JanusGraphSchemaDesktop;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import JanusGraphSchemaDesktop.GraphApp;

public class GraphHelper extends GraphApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphHelper.class);

    /**
     * Constructs a graph app using the given properties.
     * @param fileName location of the properties file
     */
    public GraphHelper(final String fileName) {
        super(fileName);
        this.supportsSchema = true;
        this.supportsTransactions = false;
        this.supportsGeoshape = false;

    }

    @Override
    public void createSchema() {
        LOGGER.info("creating schema");
        final JanusGraph janusGraph = (JanusGraph) graph;
        // naive check if the schema was previously created
       JanusGraphManagement mgmt= janusGraph.openManagement();
       String schema= mgmt.printSchema();
      System.out.println(schema);
    }

    public static void main(String[] args) {
        final String fileName = (args != null && args.length > 0) ? args[0] : null;
        final GraphHelper app = new GraphHelper(fileName);
        app.runApp();
    }
}