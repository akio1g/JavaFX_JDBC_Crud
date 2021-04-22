package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;	
	
	@FXML
	private TableView<Department> tvDepartment;
	@FXML
	private TableColumn<Department, Integer> tcId;
	@FXML
	private TableColumn<Department, String> tcName;

	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("teste button");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}
	private void initializeNodes() {
		tcId.setCellValueFactory(new PropertyValueFactory<>("Id")); // define nome da coluna
		tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		Stage stage = (Stage) Main.GetMainScene().getWindow();
		tvDepartment.prefHeightProperty().bind(stage.heightProperty()); // macete pra tabela acompanhar a janela
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tvDepartment.setItems(obsList);
	}
}
