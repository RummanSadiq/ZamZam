package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;


public class Controller extends Thread implements Initializable{

    private Label name, qty, price, total;

    public Pane cbh, cbf, cbr, cbs;
    public Pane cph, cpf, cpr, cps;
    public Pane sbh, sbf;
    public Pane sph, spf;
    public Pane drink, raita, shaami, salad;

    public Button half1ChikenBiryani, regChikenBiryani;
    public Pane halfChikenBiryani, backpane,cover,order, ppp;
    public Pane cbpaneh, cbpanef, cbpaner, cbpanes;
    public Pane cppaneh, cppanef, cppaner, cppanes;
    public Pane sbpaneh, sbpanef;
    public Pane sppaneh, sppanef;
    public Pane dpane, rpane, shamipane, saladpane;

    public GridPane txt;

    public ImageView invoice;
    public TextArea area;
    public GridPane voice,nGrid;
    public GridPane item;
    public Label iName, finaltotal;

    private int grandtotal = 0;

    private Date d;
    private DateFormat dateFormat;
    private String fileName;
    private File file;
    private static FileWriter fw;
    private static BufferedWriter bw;



    private ItemPrice itemPrice;
    @FXML
    VBox nameBox,qtyBox,priceBox,totalBox;


    public Controller() {
        itemPrice = new ItemPrice();
    }


    @Override
    public void run() {

    }


    public void modify_item(MouseEvent mouseEvent) {
        String action = null;
        Pane pane = (Pane) mouseEvent.getSource();

        GridPane gridPane = (GridPane) pane.getParent();
        Pane numberPane = (Pane) gridPane.getChildren().get(1);
        Label numberLabel = (Label) numberPane.getChildren().get(0);

        Label label = (Label) pane.getChildren().get(1);
        if(label.getText().equals("+")) {
            action = "increment";
        } else if(label.getText().equals("-")) {
            action = "decrement";
        }

        Pane parent = (Pane) gridPane.getParent();

        if(parent == cbh) {
            modify_old_item("Half Chicken Biryani", action, numberLabel, parent);
        } else if(parent == cbf) {
            modify_old_item("Full Chicken Biryani", action, numberLabel, parent);
        } else if(parent == cbr) {
            modify_old_item("Regular Chicken Biryani", action, numberLabel, parent);
        } else if(parent == cbs) {
            modify_old_item("Special Chicken Biryani", action, numberLabel, parent);
        } else if(parent == cph) {
            modify_old_item("Half Chicken Pulao", action, numberLabel, parent);
        } else if(parent == cpf) {
            modify_old_item("Full Chicken Pulao", action, numberLabel, parent);
        } else if(parent == cpr) {
            modify_old_item("Regular Chicken Pulao", action, numberLabel, parent);
        } else if(parent == cps) {
            modify_old_item("Special Chicken Pulao", action, numberLabel, parent);
        } else if(parent == sbh) {
            modify_old_item("Half Simple Biryani", action, numberLabel, parent);
        } else if(parent == sbf) {
            modify_old_item("Full Simple Biryani", action, numberLabel, parent);
        } else if(parent == sph) {
            modify_old_item("Half Simple Pulao", action, numberLabel, parent);
        } else if(parent == spf) {
            modify_old_item("Full Simple Pulao", action, numberLabel, parent);
        } else if(parent == drink) {
            modify_old_item("Cold-Drink", action, numberLabel, parent);
        } else if(parent == raita) {
            modify_old_item("Raita", action, numberLabel, parent);
        } else if(parent == shaami) {
            modify_old_item("Shaami", action, numberLabel, parent);
        } else if(parent == salad) {
            modify_old_item("Salad", action, numberLabel, parent);
        }
    }

    private void modify_old_item(String str, String action, Label number, Pane parent) {
        Label nameLabel;
        ObservableList<Node> listOfItemName = nameBox.getChildren();

        for (int i = 0; i < listOfItemName.size(); i++) {

            nameLabel = (Label) listOfItemName.get(i);

            if (nameLabel.getText().equals(str)) {
                updateOrder(i, action, number, parent);
            }
        }
    }

    private void updateOrder(int i, String str, Label num, Pane p) {

        Label qtyLabel;
        Label priceLabel;
        Label totalLabel;
        qtyLabel = (Label) qtyBox.getChildren().get(i);
        priceLabel = (Label) priceBox.getChildren().get(i);
        totalLabel = (Label) totalBox.getChildren().get(i);

        int quantity = Integer.parseInt(qtyLabel.getText());
        if(str.equals("increment")) {
            quantity++;
            grandtotal += Integer.parseInt(priceLabel.getText());
        } else if(str.equals("decrement")) {
            quantity--;
            grandtotal -= Integer.parseInt(priceLabel.getText());
        }
        if(quantity == 0) {
            nameBox.getChildren().remove(i);
            qtyBox.getChildren().remove(i);
            priceBox.getChildren().remove(i);
            totalBox.getChildren().remove(i);

            p.getChildren().get(1).setVisible(false);
            p.getChildren().get(2).setVisible(false);

        } else {
            qtyLabel.setText(Integer.toString(quantity));
            totalLabel.setText(Integer.toString(Integer.parseInt(priceLabel.getText()) * quantity));
            num.setText(Integer.toString(quantity));
        }
        finaltotal.setText(Integer.toString(grandtotal));
    }

    public void addItem(MouseEvent mouseEvent) {
        Pane pane = (Pane) mouseEvent.getSource();

        if(pane == cbpaneh) {
            addNewItem("Half Chicken Biryani");
        } else if(pane == cbpanef) {
            addNewItem("Full Chicken Biryani");
        } else if(pane == cbpaner) {
            addNewItem("Regular Chicken Biryani");
        } else if(pane == cbpanes) {
            addNewItem("Special Chicken Biryani");
        } else if(pane == cppaneh) {
            addNewItem("Half Chicken Pulao");
        } else if(pane == cppanef) {
            addNewItem("Full Chicken Pulao");
        } else if(pane == cppaner) {
            addNewItem("Regular Chicken Pulao");
        } else if(pane == cppanes) {
            addNewItem("Special Chicken Pulao");
        } else if(pane == sbpaneh) {
            addNewItem("Half Simple Biryani");
        } else if(pane == sbpanef) {
            addNewItem("Full Simple Biryani");
        } else if(pane == sppaneh) {
            addNewItem("Half Simple Pulao");
        } else if(pane == sppanef) {
            addNewItem("Full Simple Pulao");
        } else if(pane == dpane) {
            addNewItem("Cold-Drink");
        } else if(pane == rpane) {
            addNewItem("Raita");
        } else if(pane == shamipane) {
            addNewItem("Shaami");
        } else if(pane == saladpane) {
            addNewItem("Salad");
        }

        Pane parent = (Pane) pane.getParent();
        parent.getChildren().get(1).setVisible(true);
        parent.getChildren().get(2).setVisible(true);
    }

    private void addNewItem(String itemName) {



        name = new Label(itemName);
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font(14));
        qty = new Label("1");
        qty.setTextFill(Color.WHITE);
        qty.setFont(Font.font(14));

        int p = getPrice(itemName);

        price = new Label(Integer.toString(p));
        price.setTextFill(Color.WHITE);
        price.setFont(Font.font(14));


        int q = Integer.parseInt(qty.getText());
        int t = q*p;
        total =  new Label(Integer.toString(t));
        total.setTextFill(Color.WHITE);
        total.setFont(Font.font(14));

        nameBox.getChildren().add(name);
        qtyBox.getChildren().add(qty);
        priceBox.getChildren().add(price);
        totalBox.getChildren().add(total);

        grandtotal += t;
        finaltotal.setText(Integer.toString(grandtotal));

    }

    private int getPrice(String itemName) {
        if(itemName.equals("Half Chicken Biryani")) {
            return itemPrice.getHcb();
        } else if(itemName.equals("Full Chicken Biryani")) {
            return itemPrice.getFcb();
        } else if(itemName.equals("Regular Chicken Biryani")) {
            return itemPrice.getRcb();
        } else if(itemName.equals("Special Chicken Biryani")) {
            return itemPrice.getScb();
        } else if(itemName.equals("Half Chicken Pulao")) {
            return itemPrice.getHcp();
        } else if(itemName.equals("Full Chicken Pulao")) {
            return itemPrice.getFcp();
        } else if(itemName.equals("Regular Chicken Pulao")) {
            return itemPrice.getRcp();
        } else if(itemName.equals("Special Chicken Pulao")) {
            return itemPrice.getScp();
        } else if(itemName.equals("Half Simple Biryani")) {
            return itemPrice.getHsb();
        } else if(itemName.equals("Full Simple Biryani")) {
            return itemPrice.getFsb();
        } else if(itemName.equals("Half Simple Pulao")) {
            return itemPrice.getHsp();
        } else if(itemName.equals("Full Simple Pulao")) {
            return itemPrice.getFsp();
        } else if(itemName.equals("Cold-Drink")) {
            return itemPrice.getColdDrink();
        } else if(itemName.equals("Raita")) {
            return itemPrice.getRaita();
        } else if(itemName.equals("Shaami")) {
            return itemPrice.getShami();
        } else if(itemName.equals("Salad")) {
            return itemPrice.getSalad();
        } else {
            return 0;
        }
    }

    public void closeFile() {
        try {
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Problem with closing the file");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        d = new Date();
        dateFormat = new SimpleDateFormat("yyyy_MMM_dd");
        fileName = "Receipts_" + dateFormat.format(d)+".txt";
        file = new File(fileName);
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }


        invoice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                PrinterJob job = PrinterJob.createPrinterJob();
                if(job!= null) {
                    if(job.printPage(ppp)) {

                        job.endJob();
                        store();

                        grandtotal = 0;
                        finaltotal.setText(Integer.toString(grandtotal));
                        nameBox.getChildren().clear();
                        qtyBox.getChildren().clear();
                        priceBox.getChildren().clear();
                        totalBox.getChildren().clear();

                        manageVisibility(cbh);
                        manageVisibility(cbf);
                        manageVisibility(cbr);
                        manageVisibility(cbs);
                        manageVisibility(cph);
                        manageVisibility(cpf);
                        manageVisibility(cpr);
                        manageVisibility(cps);
                        manageVisibility(sbh);
                        manageVisibility(sbf);
                        manageVisibility(sph);
                        manageVisibility(spf);
                        manageVisibility(drink);
                        manageVisibility(raita);
                        manageVisibility(shaami);
                        manageVisibility(salad);

                    }
                }

            }
        });

    }

    private void manageVisibility(Pane p) {

        GridPane gp = (GridPane) p.getChildren().get(2);
        AnchorPane ap = (AnchorPane) gp.getChildren().get(1);
        Label label = (Label) ap.getChildren().get(0);
        label.setText("1");

        gp.setVisible(false);
        p.getChildren().get(1).setVisible(false);

    }


    private void store() {

        System.out.println(fileName);

        try {

            bw.write(String.valueOf(nameBox.getChildren()));
            bw.write(String.valueOf(priceBox.getChildren()));
            bw.write(String.valueOf(qtyBox.getChildren()));
            bw.write(String.valueOf(totalBox.getChildren()));


            bw.write("\n");
        } catch (IOException e) {
            System.out.println("Exception in writing order");
        }
    }
}


