package JanusGraphSchemaDesktop;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import org.janusgraph.core.VertexLabel;


public class VertexModel {
    private final SimpleStringProperty label;
    private final SimpleBooleanProperty partitioned;
    private final SimpleBooleanProperty isStatic;
    public final VertexLabel Vertex;

    public VertexModel(String label, Boolean isPartitioned, Boolean isStatic, VertexLabel vertex) {
        this.label = new SimpleStringProperty(label);
        this.partitioned = new SimpleBooleanProperty(isPartitioned);
        this.isStatic = new SimpleBooleanProperty(isStatic);
        this.Vertex=vertex;
    }

    public String getLabel() {
        return this.label.get();
    }
    public void setLabel(String label) {
        this.label.set(label);
    }

    public Boolean getPartitioned() {
        return partitioned.get();
    }
    public void setPartitioned(Boolean partitioned) {
        this.partitioned.set(partitioned);
    }

    public Boolean getIsStatic() {
        return this.isStatic.get();
    }
    public void setIsStatic(Boolean isStatic) {
        this.isStatic.set(isStatic);
    }
}
