package JanusGraphSchemaDesktop;
import JanusGraphSchemaDesktop.Dialogs.JanusPropertyKeyDialogData;
import JanusGraphSchemaDesktop.Dialogs.JanusTextInputDialog;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.janusgraph.core.*;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.JanusGraphManagement;
import org.janusgraph.core.schema.SchemaAction;
import org.janusgraph.graphdb.database.management.ManagementSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

public class GraphManager extends GraphApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphManager.class);
    JanusGraphManagement _mgmt;
    /**
     * Constructs a graph app using the given properties.
     * @param fileName location of the properties file
     */
    public GraphManager(final String fileName) {
        super(fileName);
        this.supportsSchema = true;
        this.supportsTransactions = false;
        this.supportsGeoshape = false;

    }
    JanusGraphManagement getMgmt(){
        if (_mgmt==null||!_mgmt.isOpen()){
            _mgmt=getGraph().openManagement();
        }
        return _mgmt;
    }



    JanusGraph getGraph(){
        return  (JanusGraph) graph;
    }
    @Override
    public void createSchema() {
        LOGGER.info("creating schema");
        final JanusGraph janusGraph = getGraph();
        // naive check if the schema was previously created
       JanusGraphManagement mgmt= getMgmt();
       String schema= mgmt.printSchema();
      System.out.println(schema);
    }

    public Iterable<VertexLabel> getVertexeLabels(){
        JanusGraphManagement mgmt=getMgmt();
        return mgmt.getVertexLabels();
    }

    public void addNewVertex(String vertexLabel){
        JanusGraphManagement mgmt=getMgmt();
        mgmt.getOrCreateVertexLabel(vertexLabel);
        mgmt.commit();
    }

    public void removeVertex(String vertexName){
        JanusGraphManagement mgmt=getMgmt();
        mgmt.getVertexLabel(vertexName).remove();
        mgmt.commit();
    }

    public void addPropertyKey(JanusPropertyKeyDialogData data){
        JanusGraphManagement mgmt=getMgmt();
        mgmt.makePropertyKey(data.getPropertyKey()).dataType(data.getDataTypeClass()).cardinality(data.getCardinality()).make();
        mgmt.commit();
        try {
            addIndex(data);
        }catch (Exception e){
            LOGGER.error(String.format("建立属性索引失败：{%s}",e));
        }
    }

    public void addIndex(JanusPropertyKeyDialogData data) throws InterruptedException, ExecutionException {

        JanusGraphManagement mgmt=getMgmt();
        PropertyKey key=mgmt.getPropertyKey(data.getPropertyKey());
        if (key==null){
            return;
        }
        JanusGraphManagement.IndexBuilder builder=mgmt.buildIndex(data.getIndexName(), Vertex.class).addKey(key);
        if (data.getIsIndexUnique()){
            builder=builder.unique();
        }
        if (data.getIndexType().equals(JanusPropertyKeyDialogData.CompositeIndex)){
            builder.buildCompositeIndex();
        }else{
            builder.buildMixedIndex("search");
        }
        mgmt.commit();

        ManagementSystem.awaitGraphIndexStatus(this.getGraph(), data.getIndexName()).call();

        mgmt = getMgmt();
        mgmt.updateIndex(mgmt.getGraphIndex(data.getIndexName()), SchemaAction.REINDEX).get();
        mgmt.commit();
    }

    public Iterable<JanusGraphIndex> getVertexIndexes(){
        JanusGraphManagement mgmt=getMgmt();
        return mgmt.getGraphIndexes(Vertex.class);
    }

    public Iterable<EdgeLabel> getEdgeLabels(){
        JanusGraphManagement mgmt=getMgmt();
        return mgmt.getRelationTypes(EdgeLabel.class);
    }
    public Iterable<JanusGraphIndex> getRelationIndexes(){
        JanusGraphManagement mgmt=getMgmt();
        mgmt.getRelationTypes(EdgeLabel.class);
        return null;
    }

}