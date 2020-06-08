/*
 * Shuwen Wang
 * 991583096
 * Final Project
 */
package view;

import content.Order;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DisplayOrder extends Stage {

    private ArrayList<Order> orderList = new ArrayList<>();
    private TextArea txaOrder = new TextArea();

    public DisplayOrder(ArrayList<Order> orderList) {
        this.orderList = orderList;
        setScene(addScene());
    }

    public Scene addScene() {
        VBox pane = new VBox();
        txaOrder.setPrefRowCount(12);
        txaOrder.setScrollTop(10);
        pane.getChildren().add(txaOrder);
        fillTextArea();
        Scene scene = new Scene(pane, 280, 200);
        return scene;
    }

    private void fillTextArea() {
        String sData = "";
        for (Order order : orderList) {
            sData += order + "\n";
        }
        txaOrder.setText(sData);
    }
}
