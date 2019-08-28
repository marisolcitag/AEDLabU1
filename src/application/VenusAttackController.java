package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.LocateSpaceships;

public class VenusAttackController {

	//ATRIBUTOS
    @FXML private ToggleGroup toggleMatrixA;
    @FXML private TextField rowsATextField;
    @FXML private TextField columnsATextField;
    @FXML private ScrollPane matrixABattlefield;
    @FXML private RadioButton withRepeatA;
    @FXML private RadioButton withoutRepeatA;
    @FXML private ToggleGroup toggleMatrixB;
    @FXML private TextField rowsBTextField;
    @FXML private TextField columnsBTextField;
    @FXML private ScrollPane matrixBBattlefield;
    @FXML private RadioButton withRepeatB;    
    @FXML private RadioButton withoutRepeatB;
    @FXML private ToggleGroup toggleMultiplyOperation;
    @FXML private RadioButton standarMultiplicationOptionRadioButton;    
    @FXML private RadioButton divideAndConquererOptionRadioButton;
    @FXML private RadioButton strassenMultiplicationOptionRadioButton;
    
    /**Este atributo Representa el GridPane de la Matriz de Batalla.
   	 */
    private GridPane matrixA;
    
    /**Este atributo Representa el GridPane de la Matriz del Servicio Secreto de Venus.
   	 */
    private GridPane matrixB;
    
    /**Este atributo Representa  la Matriz de las Posiciones de las Tropas de Marte en guerras pasadas.
   	 */
    private long[][] A;
    
    /**Este atributo Representa la Matriz de Coeficientes que descubrió el Servicio Secreto de Venus.
   	 */
    private long[][] B;
    
    /**Este atributo Representa una Instancia de la clase del Modelo LocateSpaceships.
   	 */
    private LocateSpaceships locate;
    
    //METODOS
    
    /**Nombre Metodo:initialize().
     * Descripción: Este metodo se encarga de Inicializar los Objetos.
   	 */
	@FXML
	public void initialize() {
		locate = new LocateSpaceships();
		
		withRepeatA.setUserData(true);
		withoutRepeatA.setUserData(false);
		
		withRepeatB.setUserData(true);
		withoutRepeatB.setUserData(false);
		
		standarMultiplicationOptionRadioButton.setUserData(LocateSpaceships.STANDARDMULTIPLICATION);
		divideAndConquererOptionRadioButton.setUserData(LocateSpaceships.DIVIDECONQUERMULTIPLICATION);
		strassenMultiplicationOptionRadioButton.setUserData(LocateSpaceships.STRASSENMULTIPLICATION);
		
		matrixA = new GridPane();
		matrixABattlefield.setContent(matrixA);
		matrixB = new GridPane();
		matrixBBattlefield.setContent(matrixB);
	}
	
	/**Nombre Metodo: generateMatrixA
	 * Descripción: Este metodo se encarga de generar la matriz de batalla con la dimensión especificada por el usuario.
	 * @param event un ActionEvent que representa el evento asociado a presionar el botón Generar Matriz de Batalla.
   	 */
	@FXML
	public void generateMatrixA(ActionEvent event) {
		matrixA.getChildren().clear();
		if(!rowsATextField.getText().isEmpty() && !columnsATextField.getText().isEmpty()) {
			A = locate.generateRandomMatrix(Integer.parseInt(rowsATextField.getText()), Integer.parseInt(columnsATextField.getText()), (boolean)toggleMatrixA.getSelectedToggle().getUserData());
			Label box;
			for(int i = 0; i < A.length; i++) {
				int evenOrOdd = i;
				for(int j = 0; j < A[i].length; j++) {
					evenOrOdd += 1;
					box = new Label(""+A[i][j]);
					box.setMinWidth(40);
					matrixA.add(box, j, i);
				}
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING, "Por favor Ingresar las Dimensiones mxn para Generar la Matriz de Batalla" );
			alert.showAndWait();
			return;
		}
	}
	/**Nombre Metodo: generateMatrixB
	 * Descripción: Este metodo se encarga de generar la matriz de inteligencia del servicio secreto con la dimensión especificada por el usuario.
	 * @param event un ActionEvent que representa el evento asociado a presionar el botón Generar Matriz de Inteligencia.
   	 */
	@FXML
	public void generateMatrixB(ActionEvent event) {
		matrixB.getChildren().clear();
		if(!rowsBTextField.getText().isEmpty() && !columnsBTextField.getText().isEmpty()) {
			B = locate.generateRandomMatrix(Integer.parseInt(rowsBTextField.getText()), Integer.parseInt(columnsBTextField.getText()), (boolean)toggleMatrixB.getSelectedToggle().getUserData());
			Label box;
			for(int i = 0; i < B.length; i++) {
				int evenOrOdd = i;
				for(int j = 0; j < B[i].length; j++) {
					evenOrOdd += 1;
					box = new Label(""+B[i][j]);
					box.setMinWidth(40);
					matrixB.add(box, j, i);
				}
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING, "Por favor Ingresar las Dimensiones mxn para Generar la Matriz del Servicio Secreto de Venus " );
			alert.showAndWait();
			return;
		}
	}
	/**Nombre Metodo: multyplyMatrix
	 * Descripción: Este metodo multiplica las matrices A y B mediante la estrategia del Algoritmo seleccionado por el usuario
	 * @param event un ActionEvent que representa el evento asociado a presionar el botón Ubicar Tropas Marte.
   	 */
	@FXML
	public void multiplyMatrix(ActionEvent event) {
		try {
			long[][] C = null;
			switch((String)toggleMultiplyOperation.getSelectedToggle().getUserData()) {
			case LocateSpaceships.STANDARDMULTIPLICATION:
				C = locate.standardMultiply(A, B);
				break;
			case LocateSpaceships.DIVIDECONQUERMULTIPLICATION:
				C = locate.divideAndConquerMultiply(A, B);
				break;
			case LocateSpaceships.STRASSENMULTIPLICATION:
				C = locate.strassenMultiply(A, B);
				break;
			}
			showMatrixResult(C);
		} catch(IllegalArgumentException e) {
			Alert alert = new Alert(AlertType.WARNING, "Por favor genere correctamente la Matriz de Batalla y la Matriz del Servicio de Inteligencia de Venus");
			alert.showAndWait();
			return;
		} catch(NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING, "Por favor genere correctamente la Matriz de Batalla y la Matriz del Servicio de Inteligencia de Venus");
			alert.showAndWait();
			return;
		} 
	}
	
	/**Nombre Metodo: showResult
	 * Descripción: Este metodo se encarga de mostrar la matriz resultante C despues de multiplicar la matriz A y la matriz B, indicando las posiciones exactas de las Tropas de Marte
	 * @param C La matriz generada despues de multiplicar la matriz A y la matriz B.
   	 */
	public void showMatrixResult(long[][] C) {
		GridPane gridPane = new GridPane();
		ScrollPane scrollPane = new ScrollPane(gridPane);
		Scene scene = new Scene(scrollPane);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initOwner(matrixA.getParent().getScene().getWindow());
		stage.initModality(Modality.WINDOW_MODAL);
		
		Label box;
		for(int i = 0; i < C.length; i++) {
			int evenOrOdd = i;
			for(int j = 0; j < C[i].length; j++) {
				evenOrOdd += 1;
				box = new Label(""+C[i][j]);
				box.setMinWidth(40);
				gridPane.add(box, j, i);
				boolean primeNumber = locate.isPrime(C[i][j]);
				if(primeNumber) {
					box.setTextFill(Color.BLUEVIOLET);
					box.setStyle(box.getStyle()+";-fx-font-weight: BOLD;");
				}
			}
		}
		stage.setTitle("Posiciones Exactas de las Tropas de Marte");
		stage.setWidth(400);
		stage.showAndWait();
	}
}