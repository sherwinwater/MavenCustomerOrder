/*
 * Shuwen Wang
 * 991583096
 * Final Project
 */
package view;

import content.Customer;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DisplayCustomer extends Stage {

    private ArrayList<Customer> customerList = new ArrayList<>();
    private TextArea txaCustomer = new TextArea();

    public DisplayCustomer(ArrayList<Customer> customerList) {
        this.customerList = customerList;
        setScene(addScene());
    }

    public Scene addScene() {
        VBox pane = new VBox();
        txaCustomer.setPrefRowCount(12);
        txaCustomer.setScrollTop(10);
        pane.getChildren().add(txaCustomer);
        fillTextArea();
        Scene scene = new Scene(pane, 280, 200);
        return scene;
    }

    private void fillTextArea() {
        String sData = "";
        for (Customer customer : customerList) {
            sData += customer + "\n";
        }
        txaCustomer.setText(sData);
    }
}
