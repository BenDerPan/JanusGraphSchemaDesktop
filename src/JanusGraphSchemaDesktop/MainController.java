package JanusGraphSchemaDesktop;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class MainController {
    @FXML
    TextField tbConfigFile;
    @FXML
    VBox rootLayout;
    @FXML
    Button btnChooseConfigFile;
    @FXML
    Button btnConnect;

    Stage stage;

    Boolean isConnected =false;
    GraphHelper app=null;

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
            app = new GraphHelper(fileName);
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
}
