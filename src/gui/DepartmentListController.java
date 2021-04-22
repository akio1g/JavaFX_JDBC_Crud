package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Department;

public class DepartmentListController implements Initializable {
	@FXML
	private TableView<Department> tvDepartment;
	@FXML
	private TableColumn<Department, Integer> tcId;
	@FXML
	private TableColumn<Department, String> tcName;

	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("teste button");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}
	private void initializeNodes() {
		tcId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
	}

}
