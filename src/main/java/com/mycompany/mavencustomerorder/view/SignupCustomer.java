/*
 * Shuwen Wang
 * 991583096
 * Final Project
 */
package view;

import content.Customer;
import content.Order;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SignupCustomer extends Stage {

    private ArrayList<Customer> customerList = new ArrayList<>();

    private Label lblCustomerID = new Label("Customer ID");
    private Label lblName = new Label("Name");
    private Label lblStreetAddress = new Label("Street Address");
    private Label lblCity = new Label("City");
    private TextField txtCustomerID = new TextField();
    private TextField txtName = new TextField();
    private TextField txtStreetAddress = new TextField();
    private TextField txtCity = new TextField();
    private Button btnAdd = new Button("add");

    public SignupCustomer(ArrayList<Customer> customerList) {
        this.customerList = customerList;
        setScene(addScene());
        setTitle("Sign Up New Customer");
    }

    public Scene addScene() {
        GridPane pane = new GridPane();
        pane.add(lblCustomerID, 0, 1);
        pane.add(lblName, 0, 2);
        pane.add(lblStreetAddress, 0, 3);
        pane.add(lblCity, 0, 4);
        pane.add(txtCustomerID, 1, 1);
        pane.add(txtName, 1, 2);
        pane.add(txtStreetAddress, 1, 3);
        pane.add(txtCity, 1, 4);
        pane.add(btnAdd, 1, 5);

        Scene scene = new Scene(pane, 300, 200);

        btnAdd.setOnAction((e) -> {
            addCustomer();
            ((Stage) (this.getScene().getWindow())).close();
        });

        return scene;
    }

    private void addCustomer() {
        boolean isExistingCustomerID = false;
        for (Customer customer : customerList) {
            if (txtCustomerID.getText().equals(customer.getCustomerID())) {
                isExistingCustomerID = true;
            }
        }
        if (isExistingCustomerID) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("CustomerID aleady exsits. Choose another one");
            txtCustomerID.clear();
        }
        if (!isExistingCustomerID) {
            // sign up new customer
            Customer customer = new Customer(txtCustomerID.getText());
            customer.setName(txtName.getText());
            customer.setStreetAddress(txtStreetAddress.getText());
            customer.setCity(txtCity.getText());
            customerList.add(customer);
        }
    }
}
