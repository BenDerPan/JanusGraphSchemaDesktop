package JanusGraphSchemaDesktop.Dialogs;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

public class JanusTextInputDialog extends TextInputDialog {

    public JanusTextInputDialog(){
        this.setHeaderText(null);
        this.setGraphic(null);
    }
    public JanusTextInputDialog(String title,String inputPromot){
        this.setHeaderText(null);
        this.setGraphic(null);

        this.setTitle(title);
        this.setContentText(inputPromot);
    }
}
