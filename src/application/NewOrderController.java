package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class NewOrderController {

    @FXML
    private ComboBox<String> cbItems;

    @FXML
    private TextField txtQuantity;
    
    @FXML
    private TextField txtTable;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnOrder;

    @FXML
    private ComboBox<String> cbTables;

    @FXML
    private Button btnHome;

    @FXML
    private TableView<ItemData> orderTable;

    @FXML
    private TableColumn<ItemData, String> itemColumn;

    @FXML
    private TableColumn<ItemData, Integer> quantityColumn;

    @FXML
    private TableColumn<ItemData, Integer> priceColumn;

    @FXML
    private Label total;
    
    private ObservableList<ItemData> tableItems = FXCollections.observableArrayList();
    private ObservableList<ItemObject> menuItems = FXCollections.observableArrayList();
    private ObservableList<String> itemNames = FXCollections.observableArrayList();
    
    
    
    String selectedItem = "";
    public int subTotal = 0;
    
    private ObservableList<ItemObject> getData(){
        menuItems.clear();
        String query = "select * from add_menu";

        try {
            PreparedStatement preparedStatement = DBHandler.connect().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                menuItems.add(new ItemObject(resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getInt(3)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    @FXML
    void initialize() {
    	
    	
    	itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    	priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    	//tableNo.setCellValueFactory(new PropertyValueFactory<>("table_num"));

    	getData();
        System.out.println(menuItems.size());

        for(ItemObject object : menuItems){
            itemNames.add(object.getItem());
        }

        cbItems.setItems(itemNames);

        cbItems.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedItem = cbItems.getValue();
                System.out.println(selectedItem);
            }
        });
    }

    @FXML
    void Home(ActionEvent event) {
    	try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/application/Homepage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

   @FXML
    void addItem(ActionEvent event){
	    //String text = cbItems.getSelectionModel().getSelectedItem();
        int quantity = 0;
       // int table_num = 0;
        if(!txtQuantity.getText().trim().isEmpty()){
            quantity = Integer.parseInt(txtQuantity.getText());
            }
       /* if(!txtTable.getText().trim().isEmpty()){
            table_num = Integer.parseInt(txtTable.getText());*/
        
        

        if(!selectedItem.isEmpty() && quantity!=0){
            int price = getPriceByName(selectedItem);
            System.out.println(selectedItem + " " + quantity + " " + price);
            subTotal = subTotal + price*quantity;
            System.out.println(subTotal);
            total.setText("" + subTotal + ".00 BDT");

            tableItems.add(new ItemData(selectedItem,quantity,price));
            System.out.println(tableItems.size());
            orderTable.setItems(tableItems);
        }
        btnOrder.setDisable(false);
    }

    private int getPriceByName(String selectedItem) {

        for(ItemObject object : menuItems){
            if (object.getItem().equals(selectedItem)){
            	
            	
                return object.getPrice();
                
                
            }
        }
        return 0;
    }

    @FXML
    void changeCombo(ActionEvent event) {
    	
    }

    @FXML
    void makeOrder(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Homepage.fxml"));
    	Parent root = (Parent) loader.load();
    	HomepageController controller = loader.getController();
    	String data = "";
    	for(ItemData item : tableItems){
    		data += item.getItem() + " - " + item.getPrice() + "\n";
    	}
    	controller.setHistory(data);
    	
          Stage newStage = new Stage();
          newStage.setScene(new Scene(root));
          newStage.show();
    	
    }

   

	public static String getTotal() {
		// TODO Auto-generated method stub
		return "";
	}

}
