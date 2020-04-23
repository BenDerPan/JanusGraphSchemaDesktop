package JanusGraphSchemaDesktop;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.janusgraph.core.EdgeLabel;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.SchemaStatus;

public class EdgeIndexModel {
    private final SimpleStringProperty edgeIndexName;
    private final SimpleStringProperty edgeIndexType;
    private final SimpleBooleanProperty edgeIndexUnique;
    private final SimpleStringProperty edgeIndexBacking;
    private final SimpleStringProperty edgeIndexKey;
    private final SimpleObjectProperty<SchemaStatus> edgeIndexStatus;

    public final JanusGraphIndex EdgeIndex;

    public EdgeIndexModel(String indexName,String indexType,
                            Boolean isUnique,String indexBacking,
                            String indexKey,SchemaStatus indexStatus,
                          JanusGraphIndex edgeIndex){
        this.edgeIndexName=new SimpleStringProperty(indexName);
        this.edgeIndexType=new SimpleStringProperty(indexType);
        this.edgeIndexUnique=new SimpleBooleanProperty(isUnique);
        this.edgeIndexBacking=new SimpleStringProperty(indexBacking);
        this.edgeIndexKey=new SimpleStringProperty(indexKey);
        this.edgeIndexStatus=new SimpleObjectProperty<>(indexStatus);
        this.EdgeIndex=edgeIndex;
    }

    public String getEdgeIndexName(){return this.edgeIndexName.get();}
    public void setEdgeIndexName(String indexName){this.edgeIndexName.set(indexName);}

    public String getEdgeIndexType(){return this.edgeIndexType.get();}
    public void setEdgeIndexType(String indexType){this.edgeIndexType.set(indexType);}

    public Boolean getEdgeIndexUnique(){return this.edgeIndexUnique.get();}
    public void setEdgeIndexUnique(Boolean isUnique){this.edgeIndexUnique.set(isUnique);}

    public String getEdgeIndexBacking(){return this.edgeIndexBacking.get();}
    public void setEdgeIndexBacking(String indexBacking){this.edgeIndexBacking.set(indexBacking);}

    public String getEdgeIndexKey(){return this.edgeIndexKey.get();}
    public void setEdgeIndexKey(String indexKey){this.edgeIndexKey.set(indexKey);}

    public SchemaStatus getEdgeIndexStatus(){return this.edgeIndexStatus.get();}
    public void setEdgeIndexStatus(SchemaStatus indexStatus){this.edgeIndexStatus.set(indexStatus);}

}
