package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;


import db.DBException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

	private Department entity;
	private DepartmentService service;
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	@FXML
	private TextField tfId;

	@FXML
	private TextField tfName;

	@FXML
	private Label lblErrorName;

	@FXML
	private Button btSave;

	@FXML
	private Button btCancel;

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		} 
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData(); // pega os dados escritos no id e name e joga no entity
			service.saveOrUpdate(entity);
			notifyDataChangeListener();
			Utils.currentStage(event).close();
		} catch(ValidationException e) {
			setErrorMessages(e.getErrors());
		}
		catch (DBException e) {
			Alerts.showAlert("Error Saving obj", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListener() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		
		ValidationException exception = new ValidationException("Validation error");
		
		obj.setId(Utils.tryParseToInt(tfId.getText())); // converte p um inteiro
		if(tfName.getText() == null || tfName.getText().trim().equals("")) {
			exception.addError("name", "Field can't be empty");
		}
		obj.setName(tfName.getText());
		
		if(exception.getErrors().size()>0) { // se tive erro, lança exception
			throw exception; 
		}
		
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	public void setDepartment(Department entity) {
		this.entity = entity;
	}

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(tfId);
		Constraints.setTextFieldMaxLength(tfName, 30);
	}

	public void updateFormData() {
		tfId.setText(String.valueOf(entity.getId()));
		tfId.setText(entity.getName());
	}
	
	private void setErrorMessages(Map<String,String> error) {
		Set<String> fields = error.keySet();
		if(fields.contains("name")) {
			lblErrorName.setText(error.get("name"));
		}
	}
}
