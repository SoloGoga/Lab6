package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class Controller {
    private ObservableList<LondonWeather> dataTable = FXCollections.observableArrayList();

    @FXML
    TableView<LondonWeather> mainTable = new TableView<LondonWeather>(dataTable);
    @FXML
    TableColumn<LondonWeather, String> weatherColTbl;
    @FXML
    TableColumn<LondonWeather, String> dateColTbl;
    @FXML
    TableColumn<LondonWeather, Double> minTempColTbl;
    @FXML
    TableColumn<LondonWeather, Double> maxTempColTbl;
    @FXML
    TableColumn<LondonWeather, Double> currTempColTbl;
    @FXML
    TableColumn<LondonWeather, Double> windSpdColTbl;
    @FXML
    TableColumn<LondonWeather, Double> airPressureColTbl;

    @FXML
    TextField yearTxt;
    @FXML
    TextField monthTxt;
    @FXML
    TextField dayTxt;
    @FXML
    Button getWeatherBtn;

    @FXML
    private void initialize()
    {
        weatherColTbl.setCellValueFactory(new PropertyValueFactory<>("weatherStateName"));
        dateColTbl.setCellValueFactory(new PropertyValueFactory<>("created"));
        minTempColTbl.setCellValueFactory(new PropertyValueFactory<>("minTemp"));
        maxTempColTbl.setCellValueFactory(new PropertyValueFactory<>("maxTemp"));
        currTempColTbl.setCellValueFactory(new PropertyValueFactory<>("theTemp"));
        windSpdColTbl.setCellValueFactory(new PropertyValueFactory<>("windSpeed"));
        airPressureColTbl.setCellValueFactory(new PropertyValueFactory<>("airPressure"));
    }

    public void getData() {
        if(!Main.jsonGetter.isAlive())
        {
            Main.jsonGetter.start();
        }

        if (!Main.jsonGetter.checkDatForExistence(yearTxt.getText(), monthTxt.getText(), dayTxt.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Incorrect category name. Please, try again.");
        }
    }

    public void addToTable(LondonWeather stats){
        dataTable.add(stats);
        mainTable.getItems().add(stats);
        show();
    }

    private void show() {
        tableUpdate(Main.weatherStats.weather);
    }

    private void tableUpdate() {
        mainTable.setItems(dataTable);
    }

    public void tableUpdate(List<LondonWeather> statsList){
        dataTable.clear();

        for(LondonWeather lndw : statsList)
            dataTable.add(lndw);

        tableUpdate();
    }
}
