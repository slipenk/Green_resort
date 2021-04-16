package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.db_classes.Homesteads;

import java.io.IOException;
import java.sql.*;

public class Homesteads_controller {

    @FXML
    private Button Add_btn;
    @FXML
    private Button Update_btn;
    @FXML
    private Button Delete_btn;
    @FXML
    private TableView<Homesteads> table_homesteads;
    @FXML
    private TableColumn<Homesteads, String> Name_col;
    @FXML
    private TableColumn<Homesteads, Byte> Beds_col;
    @FXML
    private TableColumn<Homesteads, Byte> Rooms_col;
    @FXML
    private TableColumn<Homesteads, Byte> Floors_col;
    @FXML
    private TableColumn<Homesteads, Boolean> Air_col;
    @FXML
    private TableColumn<Homesteads, Boolean> Safe_col;
    @FXML
    private TableColumn<Homesteads, Boolean> Wi_Fi_col;
    @FXML
    private TableColumn<Homesteads, Boolean> Ref_col;
    @FXML
    private TableColumn<Homesteads, Boolean> Iron_col;
    @FXML
    private TableColumn<Homesteads, Boolean> Hair_col;
    @FXML
    private TableColumn<Homesteads, Byte> Rate_col;
    @FXML
    private TableColumn<Homesteads, Float> Price_col;
    @FXML
    private TableColumn<Homesteads, Boolean> Active_col;


    public void initialize() {
        ShowHomesteads();
    }

    public Connection GetConnection() {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;database=Green_resort_Coursework;sendStringParametersAsUnicode=true";
        String name = "sa";
        String password = "Mysql1892";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(connectionUrl, name, password);
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    private ObservableList<Homesteads> getHomesteads() {
        ObservableList<Homesteads> HomesteadsList = FXCollections.observableArrayList();
        Connection conn = GetConnection();
        String query = "SELECT * FROM Homesteads";
        Statement st;
        ResultSet rs;

        try {
           if(conn != null) {
               st = conn.createStatement();
               rs = st.executeQuery(query);
               Homesteads homesteads;
               while (rs.next()) {
                   homesteads = new Homesteads(rs.getInt("ID_Homestead"), rs.getString("Name_homestead"),
                           rs.getByte("Number_of_beds_homestead"), rs.getByte("Number_of_rooms_homestead"), rs.getByte("Number_of_floors_homestead"), rs.getBoolean("Is_Air_Conditioning"),
                           rs.getBoolean("Is_Safe"), rs.getBoolean("Is_Wi_Fi"), rs.getBoolean("Is_Refrigerator"), rs.getBoolean("Is_Clothes_Iron"),
                           rs.getBoolean("Is_Hair_Dryer"), rs.getByte("Rate_homestead"), rs.getFloat("Price_homestead"), rs.getBoolean("Is_Active"));
                   HomesteadsList.add(homesteads);
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HomesteadsList;
    }

    public void ShowHomesteads() {
        ObservableList<Homesteads> list = getHomesteads();

        Name_col.setCellValueFactory(new PropertyValueFactory<>("Name_homestead"));
        Beds_col.setCellValueFactory(new PropertyValueFactory<>("Number_of_beds_homestead"));
        Rooms_col.setCellValueFactory(new PropertyValueFactory<>("Number_of_rooms_homestead"));
        Floors_col.setCellValueFactory(new PropertyValueFactory<>("Number_of_floors_homestead"));
        Air_col.setCellValueFactory(new PropertyValueFactory<>("Is_Air_Conditioning"));
        Safe_col.setCellValueFactory(new PropertyValueFactory<>("Is_Safe"));
        Wi_Fi_col.setCellValueFactory(new PropertyValueFactory<>("Is_Wi_Fi"));
        Ref_col.setCellValueFactory(new PropertyValueFactory<>("Is_Refrigerator"));
        Iron_col.setCellValueFactory(new PropertyValueFactory<>("Is_Clothes_Iron"));
        Hair_col.setCellValueFactory(new PropertyValueFactory<>("Is_Hair_Dryer"));
        Rate_col.setCellValueFactory(new PropertyValueFactory<>("Rate_homestead"));
        Price_col.setCellValueFactory(new PropertyValueFactory<>("Price_homestead"));
        Active_col.setCellValueFactory(new PropertyValueFactory<>("Is_Active"));
        table_homesteads.setItems(list);

    }

    public void Add_method(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Add_homesteads.fxml"));
        Parent parent = fxmlLoader.load();
        AddHomesteads addHomesteads_controller = fxmlLoader.getController();
        addHomesteads_controller.setWindow(this);
        Scene scene = new Scene(parent, 490, 600);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void Update_method(ActionEvent actionEvent) {
    }

    public void Delete_method(ActionEvent actionEvent) {
    }
}
