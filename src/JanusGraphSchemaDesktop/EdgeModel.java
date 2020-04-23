package JanusGraphSchemaDesktop;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.janusgraph.core.EdgeLabel;
import org.janusgraph.core.Multiplicity;

public class EdgeModel {
    private final SimpleStringProperty edgeLabelName;
    private final SimpleBooleanProperty edgeDirected;
    private final SimpleBooleanProperty edgeUndirected;
    private final SimpleObjectProperty<Multiplicity> edgeMultiplicity;
    public final EdgeLabel Edge;

    public EdgeModel(String edgeLabelName, Boolean edgeDirected, Boolean edgeUndirected, Multiplicity edgeMultiplicity, EdgeLabel edge){
        this.edgeLabelName=new SimpleStringProperty(edgeLabelName);
        this.edgeDirected=new SimpleBooleanProperty(edgeDirected);
        this.edgeUndirected=new SimpleBooleanProperty(edgeUndirected);
        this.edgeMultiplicity=new SimpleObjectProperty<>(edgeMultiplicity);
        this.Edge=edge;
    }

    public String getEdgeLabelName(){return this.edgeLabelName.get();}
    public void setEgeLabelName(String edgeLabelName){this.edgeLabelName.set(edgeLabelName);}

    public Boolean getEdgeDirected(){return this.edgeDirected.get();}
    public void setEdgeDirected(Boolean edgeDirected){this.edgeDirected.set(edgeDirected);}

    public Boolean getEdgeUndirected(){return this.edgeUndirected.get();}
    public void setEgeUndirected(Boolean edgeUndirected){this.edgeUndirected.set(edgeUndirected);}

    public Multiplicity getEdgeMultiplicity(){return this.edgeMultiplicity.get();}
    public void setEdgeMultiplicity(Multiplicity edgeMultiplicity){this.edgeMultiplicity.set(edgeMultiplicity);}


}
