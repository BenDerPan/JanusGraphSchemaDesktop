package JanusGraphSchemaDesktop.Dialogs;

import JanusGraphSchemaDesktop.JanusUtils;
import org.janusgraph.core.Cardinality;

import java.security.InvalidParameterException;

public class JanusPropertyKeyDialogData {
    public static final String  MixedIndex="Mixed" ;
    public static final String CompositeIndex="Composite";
    private String propertyKey;
    private Cardinality cardinality=Cardinality.SINGLE;
    private String dataType="String";
    private Boolean shouldIndex=false;
    private Boolean isIndexUnique=false;
    private String indexName;
    private String indexType=MixedIndex;

    public String getPropertyKey(){
        return this.propertyKey;
    }
    public void setPropertyKey(String propertyKey){
        this.propertyKey=propertyKey;
    }

    public Cardinality getCardinality(){
        return this.cardinality;
    }

    public void setCardinality(Cardinality cardinality){
        this.cardinality=cardinality;
    }

    public String getDataType(){
        return this.dataType;
    }

    public Class<?> getDataTypeClass(){
        return JanusUtils.getDataTypeByName(this.dataType);
    }

    public void setDataType(String dataType){
        this.dataType=dataType;
    }

    public Boolean getShouldIndex(){
        return this.shouldIndex;
    }

    public void setShouldIndex(Boolean shouldIndex){
        this.shouldIndex=shouldIndex;
    }

    public Boolean getIsIndexUnique(){
        return this.isIndexUnique;
    }

    public void setIsIndexUnique(Boolean isIndexUnique){
        this.isIndexUnique=isIndexUnique;
    }

    public String getIndexName(){
        return this.indexName;
    }

    public void setIndexName(String indexName){
        this.indexName=indexName;
    }

    public String getIndexType(){
        return this.indexType;
    }

    public void setIndexType(String indexType){
        this.indexType=indexType;
    }

    public JanusPropertyKeyDialogData() {

    }
}
