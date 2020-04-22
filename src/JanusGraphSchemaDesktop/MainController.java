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
import org.janusgraph.core.EdgeLabel;
import org.janusgraph.core.PropertyKey;
import org.janusgraph.core.VertexLabel;
import org.janusgraph.core.schema.JanusGraphIndex;

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
    ListView<PropertyKey>lvProperty;
    @FXML
    ListView<EdgeLabel> lvEdge;
    @FXML
    ListView<JanusGraphIndex> lvIndex;


    Stage stage;
    ContextMenu muVertex,muProperty,muEdge,muIndex;

    Boolean isConnected =false;
    GraphManager app=null;

    public  MainController(){


    }


    void initVertexContextMenu(){
        colVertexLabelName.setCellValueFactory(
                new PropertyValueFactory<VertexModel,String>("label")
        );
        colVertexPartitioned.setCellValueFactory(
                new PropertyValueFactory<VertexModel,Boolean>("partitioned")
        );
        colVertexStatic.setCellValueFactory(
                new PropertyValueFactory<VertexModel,Boolean>("isStatic")
        );

        muVertex=new ContextMenu();
        MenuItem refressh=new MenuItem("刷新");
        refressh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：刷新操作
                tvVertex.getItems().clear();
                Iterable<VertexLabel> vertexes=app.getVertexeLabels();
                final ObservableList<VertexModel> data = FXCollections.observableArrayList();

                for (VertexLabel lb :
                        vertexes) {
                    data.add(new VertexModel(lb.name(),lb.isPartitioned(),lb.isStatic(),lb));

                }

                tvVertex.setItems(data);
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
        lvProperty.setContextMenu(muProperty);
    }

    void initEdgeContextMenu(){
        muEdge=new ContextMenu();
        MenuItem refressh=new MenuItem("刷新");
        refressh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：刷新操作
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
        lvEdge.setContextMenu(muEdge);
    }

    void initIndexContextMenu(){
        muIndex=new ContextMenu();
        MenuItem refressh=new MenuItem("刷新");
        refressh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO：刷新操作
                lvIndex.getItems().clear();
                Iterable<JanusGraphIndex> indexes=app.getIndexes();
                for (JanusGraphIndex index :indexes) {
                    lvIndex.getItems().add(index);
                }
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
        lvIndex.setContextMenu(muIndex);
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
        initVertexContextMenu();
        initPropertyContextMenu();
        initEdgeContextMenu();
        initIndexContextMenu();
    }
}
