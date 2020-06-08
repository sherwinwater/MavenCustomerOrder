/*
 * Shuwen Wang
 * 991583096
 * Final Project
 */
package customerorder;

import content.Customer;
import content.CustomerFile;
import content.Order;
import content.OrderFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.DisplayCustomer;
import view.SignupCustomer;
import view.DisplayOrder;

public class Main extends Application {

    private Label lblOrderID = new Label("Order ID");
    private Label lblCustomerID = new Label("Customer ID");
    private Label lblProduct = new Label("Product");
    private Label lblShippingMethod = new Label("Shipping Method");
    private TextField txtOrderID = new TextField();
    private TextField txtCustomerID = new TextField();
    private TextField txtProduct = new TextField();
    private TextField txtShippingMethod = new TextField();
    private Button btnFirst = new Button("first");
    private Button btnLast = new Button("last");
    private Button btnPrevious = new Button("previous");
    private Button btnNext = new Button("next");
    private Button btnUpdate = new Button("update");
    private Button btnDelete = new Button("delete");
    private Button btnAdd = new Button("add");
    private Button btnSearchOrder = new Button("search Order");
    private Button btnSearchCustomer = new Button("search Customer");
    private Button btnSearchProduct = new Button("search Product");

    private ArrayList<Order> orderList = new ArrayList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();
    private int count = 0;
    private boolean isAdd = false;
    private boolean isExistingOrderID = false;
    private boolean isExistingCustomer = false;

    @Override
    public void start(Stage primaryStage) throws IOException {
        process();
        Scene scene = new Scene(addScene(), 450, 300);
        primaryStage.setTitle("Customer Order");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void process() throws IOException {
        CustomerFile.loadCustomerList();
        OrderFile.loadOrderList();
        customerList = CustomerFile.getCustomerList();
        orderList = OrderFile.getOrderList();
        showFirstOrder();
        btnFirst.setOnAction((e) -> {
            showFirstOrder();
        });
        btnLast.setOnAction((e) -> {
            showLastOrder();
        });
        btnNext.setOnAction((e) -> {
            showNextOrder();
        });
        btnPrevious.setOnAction((e) -> {
            showPreviousOrder();
        });
        btnUpdate.setOnAction((e) -> {
            validateUpdate();
            confirmUpdate();
        });
        btnDelete.setOnAction((e) -> {
            confirmDelete();
        });
        btnAdd.setOnAction((e) -> {
            clearText();
        });
        btnSearchOrder.setOnAction((e) -> {
            searchOrder();
        });
        btnSearchCustomer.setOnAction((e) -> {
            searchCustomer();
        });
        btnSearchProduct.setOnAction((e) -> {
            searchProduct();
        });
    }

    @Override
    public void stop() throws IOException {
        // Save data to sequential file while close the application
        CustomerFile.fillCustomerFile(this.customerList);
        OrderFile.fillOrderFile(this.orderList);
    }

    private void searchOrder() {
        if (txtOrderID.getText().isEmpty()) {
            DisplayOrder one = new DisplayOrder(orderList);
            one.show();
        } else {
            boolean isExistingOrder = false;
            ArrayList<Order> orderList2 = new ArrayList<>();
            for (Order order : orderList) {
                if (txtOrderID.getText().equals(order.getOrderID())) {
                    orderList2.add(order);
                    isExistingOrder = true;
                }
            }
            if (!orderList2.isEmpty()) {
                DisplayOrder one = new DisplayOrder(orderList2);
                one.show();
            }
            if (!isExistingOrder) {
                alertMsg("No order exists.");
            }
        }
    }

    private void searchProduct() {
        if (txtProduct.getText().isEmpty()) {
            DisplayOrder one = new DisplayOrder(orderList);
            one.show();
        } else {
            boolean isExistingProduct = false;
            ArrayList<Order> orderList2 = new ArrayList<>();
            for (Order order : orderList) {
                if (txtProduct.getText().equals(order.getProduct())) {
                    orderList2.add(order);
                    isExistingProduct = true;
                }
            }
            if (!orderList2.isEmpty()) {
                DisplayOrder one = new DisplayOrder(orderList2);
                one.show();
            }
            if (!isExistingProduct) {
                alertMsg("No order exists.");
            }
        }
    }

    private void searchCustomer() {
        if (txtCustomerID.getText().isEmpty()) {
            DisplayCustomer one = new DisplayCustomer(customerList);
            one.show();
        } else {
            boolean isExistingCustomer = false;
            ArrayList<Order> orderList2 = new ArrayList<>();
            for (Order order : orderList) {
                if (txtCustomerID.getText().equals(order.getCustomerID())) {
                    orderList2.add(order);
                    isExistingCustomer = true;
                }
            }
            if (!orderList2.isEmpty()) {
                DisplayOrder one = new DisplayOrder(orderList2);
                one.show();
            }

            if (!isExistingCustomer) {
                alertMsg("No order of the customer exists.");
            }
        }
    }

    private void showFirstOrder() {
        txtOrderID.setText(orderList.get(0).getOrderID());
        txtCustomerID.setText(orderList.get(0).getCustomerID());
        txtProduct.setText(orderList.get(0).getProduct());
        txtShippingMethod.setText(orderList.get(0).getShippingMethod());
        isAdd = false;
    }

    private void showLastOrder() {
        txtOrderID.setText(orderList.get(orderList.size() - 1).getOrderID());
        txtCustomerID.setText(orderList.get(orderList.size() - 1).getCustomerID());
        txtProduct.setText(orderList.get(orderList.size() - 1).getProduct());
        txtShippingMethod.setText(orderList.get(orderList.size() - 1).getShippingMethod());
        isAdd = false;
    }

    private void showNextOrder() {
        if (count < orderList.size() - 1) {
            txtOrderID.setText(orderList.get(count + 1).getOrderID());
            txtCustomerID.setText(orderList.get(count + 1).getCustomerID());
            txtProduct.setText(orderList.get(count + 1).getProduct());
            txtShippingMethod.setText(orderList.get(count + 1).getShippingMethod());
            count++;
        }
        isAdd = false;
    }

    private void showPreviousOrder() {
        if (count > 0) {
            txtOrderID.setText(orderList.get(count - 1).getOrderID());
            txtCustomerID.setText(orderList.get(count - 1).getCustomerID());
            txtProduct.setText(orderList.get(count - 1).getProduct());
            txtShippingMethod.setText(orderList.get(count - 1).getShippingMethod());
            count--;
        }
        isAdd = false;
    }

    private void showCurrentPageOrder() {
        txtOrderID.setText(orderList.get(count).getOrderID());
        txtCustomerID.setText(orderList.get(count).getCustomerID());
        txtProduct.setText(orderList.get(count).getProduct());
        txtShippingMethod.setText(orderList.get(count).getShippingMethod());
    }

    private void alertMsg(String msg) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setContentText(msg);
        alert.show();
    }

    private void validateUpdate() {
        isExistingOrderID = false;
        isExistingCustomer = false;

        if (!txtOrderID.getText().isEmpty()
                && !txtCustomerID.getText().isEmpty()
                && !txtProduct.getText().isEmpty()
                && !txtShippingMethod.getText().isEmpty()) {
            for (Order order : orderList) {
                if (txtOrderID.getText().equals(order.getOrderID())) {
                    isExistingOrderID = true;
                }
                if (txtCustomerID.getText().equals(order.getCustomerID())) {
                    isExistingCustomer = true;
                }
            }

            // validate input for update the existing order
            if (!isAdd) {
                String value = String.valueOf(isExistingOrderID)
                        + String.valueOf(isExistingCustomer);
                switch (value) {
                    case "falsefalse":
                        alertMsg("OrderID or CustomerID cannot be changed!");
                        showCurrentPageOrder();
                        break;
                    case "falsetrue":
                        alertMsg("OrderID cannot be changed!");
                        showCurrentPageOrder();
                        break;
                    case "truefalse":
                        alertMsg("CustomerID cannot be changed!");
                        showCurrentPageOrder();
                        break;
                }

                // validate input for adding new order
            } else {
                if (isExistingOrderID) {
                    alertMsg("OrderID aleady exsits. Choose another one.");
                }
            }
        } else {
            alertMsg("OrderID,Customer ID, Product or Shipping Method "
                    + "cannot be empty");
        }
    }

    private void confirmUpdate() {
        // confirmation for update
        if (!isAdd) {
            if (isExistingOrderID && isExistingCustomer) {
                System.out.println("h3");
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Update");
                alert.setHeaderText("Order Update Confirmation");
                String content = "Are you sure to update the order?\n";
                if (!orderList.get(count).getProduct().
                        equals(txtProduct.getText())
                        && !orderList.get(count).getShippingMethod().
                                equals(txtShippingMethod.getText())) {
                    content += "origin product: "
                            + orderList.get(count).getProduct()
                            + " changed to " + txtProduct.getText() + "\n"
                            + "origin shipping method: "
                            + orderList.get(count).getShippingMethod()
                            + " changed to " + txtShippingMethod.getText();

                } else if (!orderList.get(count).getProduct().
                        equals(txtProduct.getText())) {
                    content += "origin product: "
                            + orderList.get(count).getProduct()
                            + " changed to " + txtProduct.getText();

                } else if (!orderList.get(count).getShippingMethod().
                        equals(txtShippingMethod.getText())) {
                    content += "origin shipping method: "
                            + orderList.get(count).getShippingMethod()
                            + " changed to " + txtShippingMethod.getText();
                } else {
                    content = "There is nothing to update.";
                }
                alert.setContentText(content);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    updateOrder();
                } else {
                    showCurrentPageOrder();
                }
            }
            // confirmation for adding new order
        } else {
            if (!txtOrderID.getText().isEmpty()
                    && !txtCustomerID.getText().isEmpty()
                    && !txtProduct.getText().isEmpty()
                    && !txtShippingMethod.getText().isEmpty()
                    && !isExistingOrderID) {

                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Add");
                alert.setHeaderText("Order Add Confirmation");
                alert.setContentText("Are you sure to add the order?\n"
                        + txtOrderID.getText() + " "
                        + txtCustomerID.getText() + " "
                        + txtProduct.getText() + " "
                        + txtShippingMethod.getText());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    updateOrder();
                } else {
                    showCurrentPageOrder();
                }
            }
        }
    }

    private void updateOrder() {
        // add order
        if (!isAdd) {
            for (Order order : orderList) {
                if (order.getOrderID().equals(txtOrderID.getText())
                        && order.getCustomerID().equals(txtCustomerID.getText())) {
                    order.setProduct(txtProduct.getText());
                    order.setShippingMethod(txtShippingMethod.getText());
                }
            }

            // if the order ID exists or new customer to display new stage
        } else {
            if (!txtOrderID.getText().isEmpty()
                    && !txtCustomerID.getText().isEmpty()
                    && !txtProduct.getText().isEmpty()
                    && !txtShippingMethod.getText().isEmpty()) {
                Order order = new Order(txtOrderID.getText());
                order.setCustomerID(txtCustomerID.getText());
                order.setProduct(txtProduct.getText());
                order.setShippingMethod(txtShippingMethod.getText());
                orderList.add(order);
            }
            if (!isExistingCustomer) {
                // sign up new customer
                SignupCustomer one = new SignupCustomer(customerList);
                one.show();
            }
        }
        isAdd = false;
    }

    private void deleteOrder() {
        orderList.remove(orderList.get(count));
        showCurrentPageOrder();
        count--;
    }

    private void confirmDelete() {
        if (!txtOrderID.getText().isEmpty()
                && !txtCustomerID.getText().isEmpty()
                && !txtProduct.getText().isEmpty()
                && !txtShippingMethod.getText().isEmpty()) {
            if (isAdd) {
                alertMsg("After you click add, you cannot delete but update order. "
                        + "Please use first,last,next,previous to "
                        + "choose the order you like to delete ");
            } else {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Delete");
                alert.setHeaderText("Order Delete Confirmation");
                alert.setContentText("Are you sure to delete the order?\n"
                        + orderList.get(count));

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    deleteOrder();
                } else {
                    showCurrentPageOrder();
                }
            }
        } else {
            alertMsg("Please fill all data and then delete");
        }
    }

    private void clearText() {
        txtOrderID.clear();
        txtCustomerID.clear();
        txtProduct.clear();
        txtShippingMethod.clear();
        isAdd = true;
    }

    private GridPane addScene() {
        GridPane pane = new GridPane();

        pane.add(lblOrderID, 0, 1);
        pane.add(lblCustomerID, 0, 2);
        pane.add(lblProduct, 0, 3);
        pane.add(lblShippingMethod, 0, 4);
        pane.add(txtOrderID, 1, 1);
        pane.add(txtCustomerID, 1, 2);
        pane.add(txtProduct, 1, 3);
        pane.add(txtShippingMethod, 1, 4);
        pane.add(btnSearchOrder, 2, 1);
        pane.add(btnSearchCustomer, 2, 2);
        pane.add(btnSearchProduct, 2, 3);
        pane.add(btnFirst, 0, 5);
        pane.add(btnLast, 0, 6);
        pane.add(btnNext, 0, 7);
        pane.add(btnPrevious, 0, 8);
        pane.add(btnAdd, 1, 5);
        pane.add(btnUpdate, 1, 6);
        pane.add(btnDelete, 1, 7);

        pane.setPadding(new Insets(10, 10, 5, 10));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setVgap(5);
        pane.setHgap(5);

        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
