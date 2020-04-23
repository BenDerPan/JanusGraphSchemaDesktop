package JanusGraphSchemaDesktop;


import JanusGraphSchemaDesktop.Dialogs.JanusPropertyKeyDialog;
import JanusGraphSchemaDesktop.Dialogs.JanusTextInputDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.janusgraph.core.*;
import org.janusgraph.core.schema.JanusGraphIndex;
import org.janusgraph.core.schema.SchemaStatus;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    TextField tbConfigFile;
    @FXML
    GridPane rootLayout;
    @FXML
    Button btnChooseConfigFile;
    @FXML
    Button btnConnect;

    @FXML
    TableView<VertexModel> tvVertex;
    @FXML
    TableColumn<VertexModel,String> colVertexLabelName;
    @FXML
    TableColumn<VertexModel,Boolean> colVertexPartitioned;
    @FXML
    TableColumn<VertexModel,Boolean> colVertexStatic;


    @FXML
    TableView<PropertyModel> tvProperty;
    @FXML
    TableColumn<PropertyModel,String> colPropertyKeyName;
    @FXML
    TableColumn<PropertyModel, Cardinality> colPropertyCardinality;
    @FXML
    TableColumn<PropertyModel,Class<?>> colPropertyDataType;

    @FXML
    TableView<EdgeModel> tvEdge;
    @FXML
    TableColumn<EdgeModel,String> colEdgeLabelName;
    @FXML
    TableColumn<EdgeModel,Boolean> colEdgeDirected;
    @FXML
    TableColumn<EdgeModel,Boolean> colEdgeUndirected;
    @FXML
    TableColumn<EdgeModel, Multiplicity> colEdgeMultiplicity;


    @FXML
    TableView<VertexIndexModel> tvVertexIndex;
    @FXML
    TableColumn<VertexIndexModel,String> colVertexIndexName;
    @FXML
    TableColumn<VertexIndexModel,String> colVertexIndexType;
    @FXML
    TableColumn<VertexIndexModel,Boolean> colVertexIndexUnique;
    @FXML
    TableColumn<VertexIndexModel,String> colVertexIndexBacking;
    @FXML
    TableColumn<VertexIndexModel,String> colVertexIndexKey;
    @FXML
    TableColumn<VertexIndexModel, SchemaStatus> colVertexIndexStatus;

    @FXML
    TableView<EdgeIndexModel> tvEdgeIndex;
    @FXML
    TableColumn<EdgeIndexModel,String> colEdgeIndexName;
    @FXML
    TableColumn<EdgeIndexModel,String> colEdgeIndexType;
    @FXML
    TableColumn<EdgeIndexModel,Boolean> colEdgeIndexUnique;
    @FXML
    TableColumn<EdgeIndexModel,String> colEdgeIndexBacking;
    @FXML
    TableColumn<EdgeIndexModel,String> colEdgeIndexKey;
    @FXML
    TableColumn<EdgeIndexModel, SchemaStatus> colEdgeIndexStatus;


    @FXML
    TableView<RelationIndexModel> tvRelationIndex;
    @FXML
    TableColumn<RelationIndexModel,String> colRelationIndexName;
    @FXML
    TableColumn<RelationIndexModel,String> colRelationIndexType;
    @FXML
    TableColumn<RelationIndexModel,String> colRelationIndexDirection;
    @FXML
    TableColumn<RelationIndexModel,String> colRelationIndexSortKey;
    @FXML
    TableColumn<RelationIndexModel,String> colRelationIndexOrder;
    @FXML
    TableColumn<RelationIndexModel, SchemaStatus> colRelationIndexStatus;

    Stage stage;
    ContextMenu muVertex,muProperty,muEdge,muIndex;

    Boolean isConnected =false;
    GraphManager app=null;

    public  MainController(){


    }

    void initColumnDefines(){
        //Vertex
        colVertexLabelName.setCellValueFactory(
                new PropertyValueFactory<VertexModel,String>("label")
        );
        colVertexPartitioned.setCellValueFactory(
                new PropertyValueFactory<VertexModel,Boolean>("partitioned")
        );
        colVertexStatic.setCellValueFactory(
                new PropertyValueFactory<VertexModel,Boolean>("isStatic")
        );

        //Property
        colPropertyKeyName.setCellValueFactory(
                new PropertyValueFactory<PropertyModel,String>("propertyKeyName")
        );
        colPropertyCardinality.setCellValueFactory(
                new PropertyValueFactory<PropertyModel,Cardinality>("propertyCardinality")
        );
        colPropertyDataType.setCellValueFactory(
                new PropertyValueFactory<PropertyModel,Class<?> >("propertyDataType")
        );

        //Edge
        colEdgeLabelName.setCellValueFactory(
                new PropertyValueFactory<EdgeModel,String>("edgeLabelName")
        );
        colEdgeDirected.setCellValueFactory(
                new PropertyValueFactory<EdgeModel, Boolean>("edgeDirected")
        );
        colEdgeUndirected.setCellValueFactory(
                new PropertyValueFactory<EdgeModel,Boolean>("edgeUndirected")
        );
        colEdgeMultiplicity.setCellValueFactory(
                new PropertyValueFactory<EdgeModel,Multiplicity>("edgeMultiplicity")
        );

        //VertexIndex
        colVertexIndexName.setCellValueFactory(
                new PropertyValueFactory<VertexIndexModel,String>("vertexIndexName")
        );
        colVertexIndexType.setCellValueFactory(
                new PropertyValueFactory<VertexIndexModel,String>("vertexIndexType")
        );
        colVertexIndexUnique.setCellValueFactory(
                new PropertyValueFactory<VertexIndexModel,Boolean>("vertexIndexUnique")
        );
        colVertexIndexBacking.setCellValueFactory(
                new PropertyValueFactory<VertexIndexModel,String>("vertexIndexBacking")
        );
        colVertexIndexKey.setCellValueFactory(
                new PropertyValueFactory<VertexIndexModel,String>("vertexIndexKey")
        );
        colVertexIndexStatus.setCellValueFactory(
                new PropertyValueFactory<VertexIndexModel,SchemaStatus>("vertexIndexStatus")
        );

        //EdgeIndex
        colEdgeIndexName.setCellValueFactory(
                new PropertyValueFactory<EdgeIndexModel,String>("edgeIndexName")
        );
        colEdgeIndexType.setCellValueFactory(
                new PropertyValueFactory<EdgeIndexModel,String>("edgeIndexType")
        );
        colEdgeIndexUnique.setCellValueFactory(
                new PropertyValueFactory<EdgeIndexModel,Boolean>("edgeIndexUnique")
        );
        colEdgeIndexBacking.setCellValueFactory(
                new PropertyValueFactory<EdgeIndexModel,String>("edgeIndexBacking")
        );
        colEdgeIndexKey.setCellValueFactory(
                new PropertyValueFactory<EdgeIndexModel,String>("edgeIndexKey")
        );
        colEdgeIndexStatus.setCellValueFactory(
                new PropertyValueFactory<EdgeIndexModel,SchemaStatus>("edgeIndexStatus")
        );

        //RelationIndex
        colRelationIndexName.setCellValueFactory(
                new PropertyValueFactory<RelationIndexModel,String>("relationIndexName")
        );
        colRelationIndexType.setCellValueFactory(
                new PropertyValueFactory<RelationIndexModel,String>("relationIndexType")
        );
        colRelationIndexDirection.setCellValueFactory(
                new PropertyValueFactory<RelationIndexModel,String>("relationIndexDirection")
        );
        colRelationIndexSortKey.setCellValueFactory(
                new PropertyValueFactory<RelationIndexModel,String>("relationIndexSortKey")
        );
        colRelationIndexOrder.setCellValueFactory(
                new PropertyValueFactory<RelationIndexModel,String>("relationIndexOrder")
        );
        colRelationIndexStatus.setCellValueFactory(
                new PropertyValueFactory<RelationIndexModel,SchemaStatus>("relationIndexStatus")
        );

    }

    void initVertexContextMenu(){
        muVertex=new ContextMenu();
        MenuItem refressh=new MenuItem("刷新");
        refressh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：刷新操作
                doRefreshAll();
            }
        });

        MenuItem add=new MenuItem("新建节点定义");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JanusTextInputDialog dialog = new JanusTextInputDialog("新建节点定义","输入节点定义名称");
                dialog.showAndWait().ifPresent(value->{
                    //TODO:新增
                    app.addNewVertex(value);
                });
            }
        });

        MenuItem modify=new MenuItem("修改节点定义");
        modify.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO:修改
            }
        });

        MenuItem delete=new MenuItem("删除节点定义");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：删除
               VertexModel vertex=  tvVertex.getSelectionModel().getSelectedItem();
               app.removeVertex(vertex.getLabel());
            }
        });
        muVertex.getItems().addAll(refressh,add,modify,delete);
        tvVertex.setContextMenu(muVertex);


    }

    void initPropertyContextMenu(){
        muProperty=new ContextMenu();
        MenuItem refressh=new MenuItem("刷新");
        refressh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：刷新操作
                doRefreshAll();
            }
        });

        MenuItem add=new MenuItem("新建属性定义");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO:新增
                JanusPropertyKeyDialog dialog=new JanusPropertyKeyDialog("新建属性标签",null);
                dialog.showAndWait().ifPresent(data->{
                    app.addPropertyKey(data);
                });
            }
        });

        MenuItem modify=new MenuItem("修改属性定义");
        modify.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO:修改
            }
        });

        MenuItem delete=new MenuItem("删除属性定义");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：删除
            }
        });
        muProperty.getItems().addAll(refressh,add,modify,delete);
        tvProperty.setContextMenu(muProperty);
    }

    void initEdgeContextMenu(){
        muEdge=new ContextMenu();
        MenuItem refressh=new MenuItem("刷新");
        refressh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：刷新操作
                doRefreshAll();
            }
        });

        MenuItem add=new MenuItem("新建关系定义");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO:新增
            }
        });

        MenuItem modify=new MenuItem("修改关系定义");
        modify.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO:修改
            }
        });

        MenuItem delete=new MenuItem("删除关系定义");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：删除
            }
        });
        muEdge.getItems().addAll(refressh,add,modify,delete);
        tvEdge.setContextMenu(muEdge);
    }

    void initIndexContextMenu(){
        muIndex=new ContextMenu();
        MenuItem refressh=new MenuItem("刷新");
        refressh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：刷新操作
                doRefreshAll();
            }
        });

        MenuItem add=new MenuItem("新建索引定义");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO:新增
            }
        });

        MenuItem modify=new MenuItem("修改索引定义");
        modify.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO:修改
            }
        });

        MenuItem delete=new MenuItem("删除索引定义");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：删除
            }
        });
        muIndex.getItems().addAll(refressh,add,modify,delete);
        tvVertexIndex.setContextMenu(muIndex);
    }

    void doRefreshAll(){
        //刷新Vertex
        final ObservableList<VertexModel> dataVertexes = FXCollections.observableArrayList();
        Iterable<VertexLabel> vertexes=app.getVertexeLabels();
        for (VertexLabel lb :vertexes) {
            dataVertexes.add(new VertexModel(lb.name(),lb.isPartitioned(),lb.isStatic(),lb));
        }
        tvVertex.setItems(dataVertexes);

        //刷新Property

        //刷新Edge
        final ObservableList<EdgeModel> dataEdgeLabels = FXCollections.observableArrayList();
        Iterable<EdgeLabel> edges= app.getEdgeLabels();
        for (EdgeLabel edge :edges){
            dataEdgeLabels.add(new EdgeModel(edge.name(),edge.isDirected(),edge.isUnidirected(),edge.multiplicity(),edge));
        }
        tvEdge.setItems(dataEdgeLabels);

        //刷新索引
        final ObservableList<VertexIndexModel> dataVertexIndices = FXCollections.observableArrayList();
        Iterable<JanusGraphIndex> vertexIndices=app.getVertexIndexes();
        for (JanusGraphIndex index:vertexIndices){
            for (PropertyKey key :index.getFieldKeys()){
                dataVertexIndices.add(new VertexIndexModel(index.name(),
                        index.isCompositeIndex()?JanusIndexTypes.CompositeIndex:JanusIndexTypes.MixedIndex,
                        index.isUnique(),index.getBackingIndex(),key.name(),
                        index.getIndexStatus(key),index));
            }
        }
        tvVertexIndex.setItems(dataVertexIndices);

//        Iterable<JanusGraphIndex> edgeIndices=app.getEdgeIndexes();
//        for (JanusGraphIndex index:edgeIndices){
//
//        }
//        Iterable<JanusGraphIndex> relationIndices=app.getRelationIndexes();
//        for (JanusGraphIndex index:relationIndices){
//
//        }

    }


    void regStage(){
        if (stage==null){
            stage = (Stage) rootLayout.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    tryClose();
                }
            });
        }
    }

    void tryClose(){
        if (app!=null){
            try {
                app.closeGraph();
            }catch (Exception e){

            }
            app=null;
        }
    }

    boolean tryOpen(){
        try {
            app.openGraph();
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    public void onConnect(ActionEvent event) {
        regStage();
        if (!isConnected){
            final String fileName=tbConfigFile.getText().trim();
            app = new GraphManager(fileName);
            if (tryOpen()) {
                tbConfigFile.setEditable(false);
                btnConnect.setText("断开连接");
                isConnected = true;
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("图库连接失败");
                alert.setHeaderText("图库连接失败，更多:");
                alert.setContentText("请检查配置文件配置！");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    } });
            }

        }else{
            //关闭连接
            tryClose();
            isConnected=false;
            tbConfigFile.setEditable(true);
            btnConnect.setText("连接图库");
        }
    }

    public void onChooseConfigFile(ActionEvent event){
        if (!isConnected){

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Janusgraph config files (*.properties)", "*.properties");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(stage);
            tbConfigFile.setText(file.getAbsolutePath());
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumnDefines();
        initVertexContextMenu();
        initPropertyContextMenu();
        initEdgeContextMenu();
        initIndexContextMenu();
    }
}
