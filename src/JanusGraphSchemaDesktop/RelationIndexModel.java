package JanusGraphSchemaDesktop;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.janusgraph.core.schema.RelationTypeIndex;
import org.janusgraph.core.schema.SchemaStatus;

public class RelationIndexModel {
    private final SimpleStringProperty relationIndexName;
    private final SimpleStringProperty relationIndexType;
    private final SimpleStringProperty relationIndexDirection;
    private final SimpleStringProperty relationIndexSortKey;
    private final SimpleStringProperty relationIndexOrder;
    private final SimpleObjectProperty<SchemaStatus> relationIndexStatus;

    public final RelationTypeIndex RelationIndex;

    public RelationIndexModel(String indexName,String indexType,
                          String indexDirection,String indexSortKey,
                          String indexOrder,SchemaStatus indexStatus,
                              RelationTypeIndex relationIndex){
        this.relationIndexName =new SimpleStringProperty(indexName);
        this.relationIndexType =new SimpleStringProperty(indexType);
        this.relationIndexDirection =new SimpleStringProperty(indexDirection);
        this.relationIndexSortKey =new SimpleStringProperty(indexSortKey);
        this.relationIndexOrder =new SimpleStringProperty(indexOrder);
        this.relationIndexStatus =new SimpleObjectProperty<>(indexStatus);
        this.RelationIndex =relationIndex;
    }

    public String getRelationIndexName(){return this.relationIndexName.get();}
    public void setRelationIndexName(String indexName){this.relationIndexName.set(indexName);}

    public String getRelationIndexType(){return this.relationIndexType.get();}
    public void setRelationIndexType(String indexType){this.relationIndexType.set(indexType);}

    public String getRelationIndexDirection(){return this.relationIndexDirection.get();}
    public void setRelationIndexDirection(String indexDirection){this.relationIndexDirection.set(indexDirection);}

    public String getRelationIndexSortKey(){return this.relationIndexSortKey.get();}
    public void setRelationIndexSortKey(String indexSortKey){this.relationIndexSortKey.set(indexSortKey);}

    public String getRelationIndexOrder(){return this.relationIndexOrder.get();}
    public void setRelationIndexOrder(String indexOrder){this.relationIndexOrder.set(indexOrder);}

    public SchemaStatus getRelationIndexStatus(){return this.relationIndexStatus.get();}
    public void setRelationIndexStatus(SchemaStatus indexStatus){this.relationIndexStatus.set(indexStatus);}

}
