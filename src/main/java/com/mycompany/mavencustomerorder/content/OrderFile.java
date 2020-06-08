/*
 * Shuwen Wang
 * 991583096
 * Final Project
 */
package content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class OrderFile {

    private static ArrayList<Order> orderList = new ArrayList<>();

    public static void loadOrderList() throws IOException {
        FileReader fr = new FileReader("./src/content/Order.dat");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            StringTokenizer one = new StringTokenizer(line, ",");
            Order order = new Order(one.nextToken());
            order.setCustomerID(one.nextToken());
            order.setProduct(one.nextToken());
            order.setShippingMethod(one.nextToken());
            orderList.add(order);
            line = br.readLine();
        }
        br.close();
        fr.close();
    }


    public static void fillOrderFile(ArrayList<Order> orderList) throws IOException {
        FileWriter fw = new FileWriter("./src/content/Order.dat");
        BufferedWriter bw = new BufferedWriter(fw);
        String sData = new String();
        for (Order order : orderList) {
            sData = order.getOrderID()+","+order.getCustomerID()+","+
                    order.getProduct()+","+order.getShippingMethod();
            bw.write(sData);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    public static ArrayList<Order> getOrderList() {
        return orderList;
    }

    public static void setOrderList(ArrayList<Order> orderList) {
        OrderFile.orderList = orderList;
    }
}
