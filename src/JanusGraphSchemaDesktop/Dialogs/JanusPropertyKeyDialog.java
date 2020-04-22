package JanusGraphSchemaDesktop.Dialogs;

import JanusGraphSchemaDesktop.JanusUtils;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.janusgraph.core.Cardinality;

public class JanusPropertyKeyDialog extends Dialog<JanusPropertyKeyDialogData> {
    private JanusPropertyKeyDialogData data;
    public JanusPropertyKeyDialog(String title, JanusPropertyKeyDialogData dataModel){
        this.data=dataModel;
        if (this.data==null){
            this.data=new JanusPropertyKeyDialogData();
        }
        this.setTitle(title);
        this.setHeaderText(null);
        this.setGraphic(null);

        this.getDialogPane().getButtonTypes().addAll(new ButtonType("确定", ButtonBar.ButtonData.OK_DONE)
    ,new ButtonType("取消", ButtonBar.ButtonData.CANCEL_CLOSE) );

        VBox root=new VBox();

        TextField tbPropertyKey=new TextField();
        tbPropertyKey.setPromptText("属性名称");
        tbPropertyKey.setText(this.data.getPropertyKey());
        root.getChildren().add(tbPropertyKey);

        ComboBox<String> cbDataType=new ComboBox<>();
        cbDataType.getItems().addAll(JanusUtils.SupportDataTypes.keySet());
        cbDataType.getSelectionModel().select(this.data.getDataType());
        root.getChildren().add(cbDataType);

        ComboBox<Cardinality> cbCardinality=new ComboBox<>();
        cbCardinality.getItems().addAll(Cardinality.values());
        cbCardinality.getSelectionModel().select(this.data.getCardinality());
        root.getChildren().add(cbCardinality);

        CheckBox ckShouldIndex=new CheckBox();
        ckShouldIndex.setText("同时建立索引");
        ckShouldIndex.setSelected(this.data.getShouldIndex());
        root.getChildren().add(ckShouldIndex);

        TextField tbIndexName=new TextField();
        tbIndexName.setPromptText("索引名称");
        tbIndexName.setText(this.data.getIndexName());
        root.getChildren().add(tbIndexName);

        CheckBox ckIsIndexUnique=new CheckBox();
        ckIsIndexUnique.setText("索引是否唯一");
        ckIsIndexUnique.setSelected(this.data.getIsIndexUnique());
        root.getChildren().add(ckIsIndexUnique);

        ComboBox<String> cbIndexType=new ComboBox<>();
        cbIndexType.getItems().addAll(JanusPropertyKeyDialogData.CompositeIndex
                ,JanusPropertyKeyDialogData.MixedIndex);
        cbIndexType.getSelectionModel().select(this.data.getIndexType());
        root.getChildren().add(cbIndexType);

        this.getDialogPane().setContent(root);

        this.setResultConverter(btn->{
            if (btn.getButtonData()== ButtonBar.ButtonData.OK_DONE){
                if (this.data!=null){
                    this.data.setPropertyKey(tbPropertyKey.getText());
                    this.data.setCardinality(cbCardinality.getSelectionModel().getSelectedItem());
                    this.data.setDataType(cbDataType.getSelectionModel().getSelectedItem());
                    this.data.setShouldIndex(ckShouldIndex.isSelected());
                    this.data.setIndexName(tbIndexName.getText());
                    this.data.setIsIndexUnique(ckIsIndexUnique.isSelected());
                    this.data.setIndexType(cbIndexType.getSelectionModel().getSelectedItem());
                }
                return this.data;
            }
            return null;
        });

    }

    public JanusPropertyKeyDialogData getData(){
        return this.data;
    }

}
