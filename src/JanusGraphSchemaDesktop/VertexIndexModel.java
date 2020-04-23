package JanusGraphSchemaDesktop;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.janusgraph.core.Cardinality;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.SchemaStatus;

public class VertexIndexModel {
    private final SimpleStringProperty vertexIndexName;
    private final SimpleStringProperty vertexIndexType;
    private final SimpleBooleanProperty vertexIndexUnique;
    private final SimpleStringProperty vertexIndexBacking;
    private final SimpleStringProperty vertexIndexKey;
    private final SimpleObjectProperty<SchemaStatus> vertexIndexStatus;

    public final JanusGraphIndex VertexIndex;

    public VertexIndexModel(String indexName,String indexType,
                            Boolean isUnique,String indexBacking,
                            String indexKey,SchemaStatus indexStatus,
                            JanusGraphIndex vertexIndex){
        this.vertexIndexName=new SimpleStringProperty(indexName);
        this.vertexIndexType=new SimpleStringProperty(indexType);
        this.vertexIndexUnique=new SimpleBooleanProperty(isUnique);
        this.vertexIndexBacking=new SimpleStringProperty(indexBacking);
        this.vertexIndexKey=new SimpleStringProperty(indexKey);
        this.vertexIndexStatus=new SimpleObjectProperty<>(indexStatus);
        this.VertexIndex=vertexIndex;
    }

    public String getVertexIndexName(){return this.vertexIndexName.get();}
    public void setVertexIndexName(String indexName){this.vertexIndexName.set(indexName);}

    public String getVertexIndexType(){return this.vertexIndexType.get();}
    public void setVertexIndexType(String indexType){this.vertexIndexType.set(indexType);}

    public Boolean getVertexIndexUnique(){return this.vertexIndexUnique.get();}
    public void setVertexIndexUnique(Boolean isUnique){this.vertexIndexUnique.set(isUnique);}

    public String getVertexIndexBacking(){return this.vertexIndexBacking.get();}
    public void setVertexIndexBacking(String indexBacking){this.vertexIndexBacking.set(indexBacking);}

    public String getVertexIndexKey(){return this.vertexIndexKey.get();}
    public void setVertexIndexKey(String indexKey){this.vertexIndexKey.set(indexKey);}

    public SchemaStatus getVertexIndexStatus(){return this.vertexIndexStatus.get();}
    public void setVertexIndexStatus(SchemaStatus indexStatus){this.vertexIndexStatus.set(indexStatus);}

}
