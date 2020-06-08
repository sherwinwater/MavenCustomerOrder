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

public class CustomerFile {

    private static ArrayList<Customer> customerList = new ArrayList<>();

    public static void loadCustomerList() throws IOException {
        FileReader fr = new FileReader("./src/content/Customer.dat");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            StringTokenizer one = new StringTokenizer(line, ",");
            Customer customer = new Customer(one.nextToken());
            customer.setName(one.nextToken());
            customer.setStreetAddress(one.nextToken());
            customer.setCity(one.nextToken());
            customerList.add(customer);
            line = br.readLine();
        }
        br.close();
        fr.close();
    }

    public static void fillCustomerFile(ArrayList<Customer> customerList) throws IOException {
        FileWriter fw = new FileWriter("./src/content/Customer.dat");
        BufferedWriter bw = new BufferedWriter(fw);
        String sData = new String();
        for (Customer customer : customerList) {
            sData = customer.getCustomerID() + "," + customer.getName() + ","
                    + customer.getStreetAddress() + "," + customer.getCity();
            bw.write(sData);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    public static ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public static void setCustomerList(ArrayList<Customer> customerList) {
        CustomerFile.customerList = customerList;
    }

}
