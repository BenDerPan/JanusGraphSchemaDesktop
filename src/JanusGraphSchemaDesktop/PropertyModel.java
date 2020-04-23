package JanusGraphSchemaDesktop;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.janusgraph.core.Cardinality;
import org.janusgraph.core.PropertyKey;

public class PropertyModel {
    private final SimpleStringProperty propertyKeyName;
    private final SimpleObjectProperty<Cardinality> propertyCardinality;
    private final SimpleObjectProperty<Class<?>> propertyDataType;

    public final PropertyKey Property;


    public PropertyModel(String propName,Cardinality propCardinality,Class<?> propDataType,PropertyKey property){
        this.propertyKeyName=new SimpleStringProperty(propName);
        this.propertyCardinality=new SimpleObjectProperty<>(propCardinality);
        this.propertyDataType=new SimpleObjectProperty<>(propDataType);
        this.Property=property;

    }

    public String getPropertyKeyName(){return this.propertyKeyName.get();}
    public void setPropertyKeyName(String propName){this.propertyKeyName.set(propName);}

    public Cardinality getPropertyCardinality(){return this.propertyCardinality.get();}
    public void setPropertyCardinality(Cardinality propCardinality){this.propertyCardinality.set(propCardinality);}

    public Class<?> getPropertyDataType(){return this.propertyDataType.get();}
    public void setPropertyDataType(Class<?> propDataType){this.propertyDataType.set(propDataType);}

}
