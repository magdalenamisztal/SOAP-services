package com.example.lab3;

import com.example.lab3.laptop.Laptop;
import com.example.lab3.xml.LaptopXmlWriter;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

@RequiredArgsConstructor
@Component
public class SimpleController implements Initializable {

	private final HostServices hostServices;
	private final LaptopServiceImpl laptopServiceImpl;

	private int rowNumber;
	private int duplicatesNumber;
	private int newRecordsNumber;
	private List<Integer> rowNumbersToRed;
	private List<Integer> rowNumbersToWhite;
	private List<Integer> rowNumbersToGray;
	private List<Laptop> previousList;

	@FXML
	public Label label;

	@FXML
	public Button button;


	@FXML
	private Label welcomeText;
	@FXML
	private Label duplicatesNumberText;
	@FXML
	private Label newRecordsNumberText;

	@FXML
	private TableView<Laptop> table = new TableView<Laptop>();

	private TableColumn column;

	@FXML
	private TableColumn manufacturerColumn;
	@FXML
	private TableColumn screenSizeColumn;
	@FXML
	private TableColumn screenResolutionColumn;
	@FXML
	private TableColumn screenTypeColumn;
	@FXML
	private TableColumn touchScreenColumn;
	@FXML
	private TableColumn processorColumn;
	@FXML
	private TableColumn physicalCoresColumn;
	@FXML
	private TableColumn clockSpeedColumn;
	@FXML
	private TableColumn ramColumn;
	@FXML
	private TableColumn discStorageColumn;
	@FXML
	private TableColumn discTypeColumn;
	@FXML
	private TableColumn graphicCardColumn;
	@FXML
	private TableColumn graphicCardMemoryColumn;
	@FXML
	private TableColumn osColumn;
	@FXML
	private TableColumn discReaderColumn;

	@FXML
	private TextField manufacturerInput;
	@FXML
	private TextField screenSizeInput;
	@FXML
	private TextField screenResolutionInput;
	@FXML
	private TextField screenTypeInput;
	@FXML
	private TextField touchScreenInput;
	@FXML
	private TextField processorInput;
	@FXML
	private TextField physicalCoresInput;
	@FXML
	private TextField clockSpeedInput;
	@FXML
	private TextField ramInput;
	@FXML
	private TextField discStorageInput;
	@FXML
	private TextField discTypeInput;
	@FXML
	private TextField graphicCardInput;
	@FXML
	private TextField graphicCardMemoryInput;
	@FXML
	private TextField osInput;
	@FXML
	private TextField discReaderInput;


	private void ustawTabele(ObservableList<Laptop> dane) {
		previousList = table.getItems();
		table.itemsProperty().setValue(dane);
		setDuplicatesNewRowsNumbersAndColorLists(dane);
		duplicatesNumberText.setText("Liczba duplikatów: "+ duplicatesNumber);
		newRecordsNumberText.setText("Liczba nowych rekordów: "+ newRecordsNumber);

		kolorujTabele();

		laptopsWithQualities();
	}

	private void laptopsWithQualities() {
		System.out.println("laptopsWithQualities");

		Map<String, String> qualitiesMap = new HashMap<>();
		qualitiesMap.put("screenResolution", "1600x900");
		qualitiesMap.put("screenType", "matowa");
		qualitiesMap.put("touchScreen", "nie");
		qualitiesMap.put("processor", "intel i5");
		qualitiesMap.put("discType", "SSD");

		List<Laptop> laptopsWithQualities = laptopServiceImpl.getLaptopsWithQualities(qualitiesMap);

		System.out.println("Wylosowane laptopy: ");
		for(Laptop laptop : laptopsWithQualities) {
			System.out.println(laptop);
		}

		zapiszWylosowane(laptopsWithQualities);
	}
	void zapiszWylosowane(List<Laptop> laptopsWithQualities )  {
		try {
			LaptopXmlWriter xmlWriter = new LaptopXmlWriter();
			String filePath = "zapiszHaveQualities.xml";
			xmlWriter.writeLaptopsToXml(laptopsWithQualities, filePath);
		} catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

	private void kolorujTabele() {
		table.setRowFactory(tv -> {
			TableRow<Laptop> row = new TableRow<>();
			row.itemProperty().addListener((obs, oldLaptop, newLaptop) -> {
				int rowIndex = row.getIndex();
				if(rowIndex!=(-1) && rowIndex<tv.getItems().size()) {
					if (!rowNumbersToGray.isEmpty()) {
						for (int i = 0; i < rowNumbersToGray.size(); i++) {
							if (rowIndex == rowNumbersToGray.get(i)) {
								row.setStyle("-fx-background-color: grey;");
								break;
							}
						}
					}
					if (!rowNumbersToRed.isEmpty()) {
						for (int i = 0; i < rowNumbersToRed.size(); i++) {
							if (rowIndex == rowNumbersToRed.get(i)) {
								row.setStyle("-fx-background-color: red;");
								break;
							}
						}
					}
					if (!rowNumbersToWhite.isEmpty()) {

						for (int i = 0; i < rowNumbersToWhite.size(); i++) {

							if (rowIndex == rowNumbersToWhite.get(i)) {
								row.setStyle("-fx-background-color: white;");
								break;
							}
						}
					}
				}
			});
			return row;
		});
		table.refresh();
	}

	private void setDuplicatesNewRowsNumbersAndColorLists(ObservableList<Laptop> dane) {
		clearNumbersAndColorLists();

		if(previousList.isEmpty()){
			newRecordsNumber = dane.size();
			for(int i=0; i<dane.size(); i++) {
				rowNumbersToGray.add(i);
			}
		}
		else {
			ObservableList<Laptop> previousListNoDuplicates = fromListToNoDuplicates(previousList);
			for(Laptop laptop: previousListNoDuplicates) {
				System.out.println(laptop);
			}
			for(int i=0; i<dane.size(); i++) {
				Laptop laptopi = dane.get(i);
				for(int j=0; j<previousListNoDuplicates.size(); j++) {
					Laptop laptopj = previousListNoDuplicates.get(j);
					if(sameLaptopDetails(laptopi, laptopj)) {
						duplicatesNumber++;
						rowNumbersToRed.add(i);
						break;
					}
					if(j==(previousListNoDuplicates.size()-1)){
						newRecordsNumber++;
						rowNumbersToGray.add(i);
					}
				}
			}
		}

	}
	public void clearNumbersAndColorLists() {
		duplicatesNumber = 0;
		newRecordsNumber = 0;
		if(!rowNumbersToRed.isEmpty()){
			rowNumbersToRed.clear();
		}
		if(!rowNumbersToGray.isEmpty()){
			rowNumbersToGray.clear();
		}
		if(!rowNumbersToWhite.isEmpty()){
			rowNumbersToWhite.clear();
		}
	}

	public void onFromTXTButtonClick() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		// File file = fileChooser.showOpenDialog(null);
		File file = new File("C:/Users/Dell/IdeaProjects/desktopowe/hello1/laptopy.txt");

		try {
			String linia;
			Scanner skaner = new Scanner(file);
			ObservableList<Laptop> dane = FXCollections.observableArrayList();
			while (skaner.hasNextLine()) {
				linia = skaner.nextLine();
				Laptop laptop = fromLineToLaptop(linia);
				System.out.println(laptop.toString());
				dane.add(laptop);
			}
			skaner.close();
			ustawTabele(dane);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Laptop fromLineToLaptop(String line) {
		String[] splits = line.split(";");

		if(line != "\n") {
			for(int i=0; i< splits.length; i++) {
				if(splits[i].equals("")) {
					splits[i] = null;
				}
			}
			if (splits.length == 15) {
				return new Laptop(
						splits[0], splits[1], splits[2], splits[3], splits[4], splits[5], splits[6], splits[7], splits[8], splits[9], splits[10], splits[11], splits[12], splits[13], splits[14]
				);
			} else {
				return new Laptop(
						splits[0], splits[1], splits[2], splits[3], splits[4], splits[5], splits[6], splits[7], splits[8], splits[9], splits[10], splits[11], splits[12], splits[13], "brak"
				);
			}
		}
		else return new Laptop();
	}

	public void onFromXMLButtonClick() throws ParserConfigurationException, IOException, SAXException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
		File file = fileChooser.showOpenDialog(null);

		Document doc = builder.parse(file);

		Element root = doc.getDocumentElement();

		Laptop laptop = new Laptop();

		NodeList laptops = root.getChildNodes();
		ObservableList dane = metoda1(laptops);
		ustawTabele(dane);
	}

	private ObservableList metoda1(NodeList laptops) {
		ObservableList dane = FXCollections.observableArrayList();
		for(int i=0; i< laptops.getLength(); i++) {
			Node laptopsChild = laptops.item(i);
			// laptopsChild = poziom <laptop> - pojedynczy laptop
			if(laptopsChild instanceof Element) {
				Laptop laptop = new Laptop();
				NodeList laptopDetails = laptopsChild.getChildNodes();
				// lista laptopDetails = poziom <manufacturer> - dzieci laptopa
				laptop = metoda2(laptopDetails, laptop);
				dane.add(laptop);
			}
		}
		return dane;
	}

	private Laptop metoda2(NodeList children, Laptop laptop) {

		for(int i=0; i< children.getLength(); i++) {
			Node child = children.item(i);
			// child = poziom <manufacturer>, <screen> - dziecko laptopa
			if(child instanceof Element) {
				String nodeName = child.getNodeName(); // <nazwa>
				laptop = met(laptop, child);
			}
		}
		return laptop;
	}

	private Laptop met(Laptop laptop, Node child) {
		String nodeName = child.getNodeName();// <nazwa>
		if(nodeNameEqualsNoChildren(nodeName)){
			laptop = setLaptopValues(laptop, child);
		}
		else {
			NodeList childDetails = child.getChildNodes();
			laptop = metoda3(childDetails, laptop);
		}
		return laptop;
	}

	private Laptop metoda3(NodeList children, Laptop laptop) {
		for(int i=0; i<children.getLength(); i++) {
			Node child = children.item(i);
			if(child instanceof Element) {
				String nodeName = child.getNodeName();// <nazwa>
				laptop = setLaptopValues(laptop, child);
			}
		}
		return laptop;
	}

	private Laptop setLaptopValues(Laptop laptop, Node child) {
		String nodeName = child.getNodeName();
		String nodeValue = "";

		var textNode = (Text) child.getFirstChild();

		if(textNode != null) {
			nodeValue = textNode.getData().trim();
		}


		if(nodeName.equals("manufacturer")) {
			laptop.setManufacturer(nodeValue);
		}
		else if(nodeName.equals("resolution")) {
			laptop.setScreenResolution(nodeValue);
		}
		else if(nodeName.equals("size")) {
			laptop.setScreenSize(nodeValue);
		}
		else if(nodeName.equals("type") && child.getParentNode().getNodeName().equals("screen")) {
			laptop.setScreenType(nodeValue);
		}
		else if(nodeName.equals("touchscreen")) {
			laptop.setTouchScreen(nodeValue);
		}
		else if(nodeName.equals("name") && child.getParentNode().getNodeName().equals("processor")) {
			laptop.setProcessor(nodeValue);
		}
		else if(nodeName.equals("physical_cores")) {
			laptop.setPhysicalCores(nodeValue);
		}
		else if(nodeName.equals("clock_speed")) {
			laptop.setClockSpeed(nodeValue);
		}
		else if(nodeName.equals("ram")) {
			laptop.setRam(nodeValue);
		}
		else if(nodeName.equals("storage")) {
			laptop.setDiscStorage(nodeValue);
		}
		else if(nodeName.equals("type") && child.getParentNode().getNodeName().equals("disc")) {
			laptop.setDiscType(nodeValue);
		}
		else if(nodeName.equals("name") && child.getParentNode().getNodeName().equals("graphic_card")) {
			laptop.setGraphicCard(nodeValue);
		}
		else if(nodeName.equals("memory")) {
			laptop.setGraphicCardMemory(nodeValue);
		}
		else if(nodeName.equals("os")) {
			laptop.setOs(nodeValue);
		}
		else if(nodeName.equals("disc_reader")) {
			laptop.setDiscReader(nodeValue);
		}

		return laptop;
	}

	private boolean nodeNameEqualsNoChildren(String nodeName) {
		if(nodeName.equals("manufacturer")
				|| nodeName.equals("ram")
				|| nodeName.equals("os")
				|| nodeName.equals("disc_reader")){
			return true;
		}
		else return false;
	}


	public void onToTXTButtonClick() throws FileNotFoundException {

		PrintWriter zapis = new PrintWriter("zapisTXT.txt");

		ObservableList dane = table.getItems();

		String doZapisu = fromDaneToString(dane);

		zapis.print(doZapisu);
		zapis.close();
	}

	private String fromDaneToString(ObservableList dane) {
		String doZapisu = "";

		Object[] laptops = dane.toArray();

		for(int i=0; i< laptops.length; i++) {
			if(i!=0) {
				doZapisu += "\n";
			}
			Laptop laptop = (Laptop) laptops[i];
			doZapisu += fromLaptopToLine(laptop);
		}

		return doZapisu;
	}

	private String fromLaptopToLine(Laptop laptop) {
		String line = "";

		String manufacturer = laptop.getManufacturer();
		line = dodajTekst(line, manufacturer);

		String screenSize = laptop.getScreenSize();
		line = dodajTekst(line, screenSize);

		String screenResolution = laptop.getScreenResolution();
		line = dodajTekst(line, screenResolution);

		String screenType = laptop.getScreenType();
		line = dodajTekst(line, screenType);

		String touchScreen = laptop.getTouchScreen();
		line = dodajTekst(line, touchScreen);

		String processor = laptop.getProcessor();
		line = dodajTekst(line, processor);

		String physicalCores = laptop.getPhysicalCores();
		line = dodajTekst(line, physicalCores);

		String clockSpeed = laptop.getClockSpeed();
		line = dodajTekst(line, clockSpeed);

		String ram = laptop.getRam();
		line = dodajTekst(line, ram);

		String discStorage = laptop.getDiscStorage();
		line = dodajTekst(line, discStorage);

		String discType = laptop.getDiscType();
		line = dodajTekst(line, discType);

		String graphicCard = laptop.getGraphicCard();
		line = dodajTekst(line, graphicCard);

		String graphicCardMemory = laptop.getGraphicCardMemory();
		line = dodajTekst(line, graphicCardMemory);

		String os = laptop.getOs();
		line = dodajTekst(line, os);

		String discReader = laptop.getDiscReader();
		line = dodajTekst(line, discReader);
		return line;
	}

	private String dodajTekst(String line, String tekst) {
		if(tekst != null) {
			line += tekst + ";";
		}
		else {
			line += ";";
		}
		return line;
	}

	public void onToXMLButtonClick() {
		ObservableList dane = table.getItems();
		try
		{
			LaptopXmlWriter xmlWriter = new LaptopXmlWriter();
			String filePath = "zapisXML.xml";

			xmlWriter.writeLaptopsToXml(dane, filePath);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	public void initialize() {
		this.button.setOnAction(actionEvent ->
			this.label.setText(this.hostServices.getDocumentBase())
		);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		duplicatesNumber=0;
		newRecordsNumber=0;
		rowNumbersToRed = new ArrayList<>();
		rowNumbersToGray = new ArrayList<>();
		rowNumbersToWhite = new ArrayList<>();
		previousList = new ArrayList<>();

		table.setEditable(true);

		setCellValues();

	}

	public void setCellValues() {
		setCellValueFactory(manufacturerColumn, "manufacturer");
		setCellValueFactory(screenSizeColumn, "screenSize");
		setCellValueFactory(screenResolutionColumn, "screenResolution");
		setCellValueFactory(screenTypeColumn, "screenType");
		setCellValueFactory(touchScreenColumn, "touchScreen");
		setCellValueFactory(processorColumn, "processor");
		setCellValueFactory(physicalCoresColumn, "physicalCores");
		setCellValueFactory(clockSpeedColumn, "clockSpeed");
		setCellValueFactory(ramColumn, "ram");
		setCellValueFactory(discStorageColumn, "discStorage");
		setCellValueFactory(discTypeColumn, "discType");
		setCellValueFactory(graphicCardColumn, "graphicCard");
		setCellValueFactory(graphicCardMemoryColumn, "graphicCardMemory");
		setCellValueFactory(osColumn, "os");
		setCellValueFactory(discReaderColumn, "discReader");
	}

	public void setCellValueFactory(TableColumn column, String value) {
		column.setCellValueFactory(
				new PropertyValueFactory<Laptop, String>(value)
		);
	}


	public void rowClicked(MouseEvent mouseEvent) {
		Laptop clickedLaptop = table.getSelectionModel().getSelectedItem();
		setText(clickedLaptop);
	}

	public void setText(Laptop clickedLaptop) {
		manufacturerInput.setText(clickedLaptop.getManufacturer());
		screenSizeInput.setText(clickedLaptop.getScreenSize());
		screenResolutionInput.setText(clickedLaptop.getScreenResolution());
		screenTypeInput.setText(clickedLaptop.getScreenType());
		touchScreenInput.setText(clickedLaptop.getTouchScreen());
		processorInput.setText(clickedLaptop.getProcessor());
		physicalCoresInput.setText(clickedLaptop.getPhysicalCores());
		clockSpeedInput.setText(clickedLaptop.getClockSpeed());
		ramInput.setText(clickedLaptop.getRam());
		discStorageInput.setText(clickedLaptop.getDiscStorage());
		discTypeInput.setText(clickedLaptop.getDiscType());
		graphicCardInput.setText(clickedLaptop.getGraphicCard());
		graphicCardMemoryInput.setText(clickedLaptop.getGraphicCardMemory());
		osInput.setText(clickedLaptop.getOs());
		discReaderInput.setText(clickedLaptop.getDiscReader());

	}

	public void submit(ActionEvent actionEvent) {
		ObservableList<Laptop> currentTableData = table.getItems();
		Laptop selectedLaptop = table.getSelectionModel().getSelectedItem();

		rowNumber = table.getSelectionModel().getFocusedIndex();
		System.out.println("row to update: "+rowNumber);

		Laptop laptop = currentTableData.get(rowNumber);
		laptop = updateValues(laptop);
		table.setItems(currentTableData);
		correctColorListsAfterUpdate(rowNumber);
		kolorujTabele();
		table.refresh();

	}

	private void correctColorListsAfterUpdate(int updatedRowNumber) {
		ObservableList<Laptop> dane = table.getItems();
		rowNumbersToWhite.add(updatedRowNumber);
		for(int i=0; i<rowNumbersToRed.size(); i++) {
			if(rowNumbersToRed.get(i)==updatedRowNumber) {
				rowNumbersToRed.remove(i);
				break;
			}
		}
		for(int i=0; i<rowNumbersToGray.size(); i++) {
			if(rowNumbersToGray.get(i)==updatedRowNumber) {
				rowNumbersToGray.remove(i);
				break;
			}
		}
	}

	private Laptop updateValues(Laptop selectedLaptop) {
		selectedLaptop.setManufacturer(manufacturerInput.getText());
		selectedLaptop.setScreenSize(screenSizeInput.getText());
		selectedLaptop.setScreenResolution(screenResolutionInput.getText());
		selectedLaptop.setScreenType(screenTypeInput.getText());
		selectedLaptop.setTouchScreen(touchScreenInput.getText());
		selectedLaptop.setProcessor(processorInput.getText());
		selectedLaptop.setPhysicalCores(physicalCoresInput.getText());
		selectedLaptop.setClockSpeed(clockSpeedInput.getText());
		selectedLaptop.setRam(ramInput.getText());
		selectedLaptop.setDiscStorage(discStorageInput.getText());
		selectedLaptop.setDiscType(discTypeInput.getText());
		selectedLaptop.setGraphicCard(graphicCardInput.getText());
		selectedLaptop.setGraphicCardMemory(graphicCardMemoryInput.getText());
		selectedLaptop.setOs(osInput.getText());
		selectedLaptop.setDiscReader(discReaderInput.getText());
		return selectedLaptop;
	}


	public void onToDBButtonClick() {
		ObservableList<Laptop> dane = table.getItems();
        laptopServiceImpl.addLaptops(dane);
	}


	public void onFromDBButtonClick() {
		previousList = table.getItems();

		List<Laptop> laptopsDB = laptopServiceImpl.getAllLaptops();
		ObservableList<Laptop> dane = fromListToDane(laptopsDB);
		ustawTabele(dane);

	}

	public ObservableList<Laptop> fromListToDane(List<Laptop> laptops) {
		ObservableList<Laptop> dane = FXCollections.observableArrayList();
		for(Laptop laptop: laptops) {
			dane.add(laptop);
		}
		return dane;
	}

	public ObservableList<Laptop> fromListToNoDuplicates(List<Laptop> laptops) {

		ObservableList<Laptop> noDuplicates = FXCollections.observableArrayList();

		if(laptops.isEmpty()){
			return noDuplicates;
		}

		noDuplicates.add(laptops.get(0));
		if(laptops.size()==1) {
			return noDuplicates;
		}

		for(int i=1; i<laptops.size(); i++) {
			System.out.println("i = "+i);
			for(int j=0; j<i; j++) {
				if(sameLaptopDetails(laptops.get(i), laptops.get(j))) {
					break;
				}
				if(j==(i-1)) {
					noDuplicates.add(laptops.get(i));
				}
			}
		}
		return noDuplicates;
	}

	public boolean sameLaptopDetails(Laptop laptop1, Laptop laptop2) {
		ArrayList<String> details1 = new ArrayList<String>();
		ArrayList<String> details2 = new ArrayList<String>();
		ArrayList<String> methods = setMethodsArray();
        for (int i=0; i<methods.size(); i++) {
			String method = methods.get(i);
			String value1 = laptop1.getValue(method);
			if(value1==null){
				value1="";
			}
			String value2 = laptop2.getValue(method);
			if(value2==null){
				value2="";
			}
			if(!value1.equals(value2)) {
				return false;
			}
        }
		return true;
	}

	public ArrayList<String> setMethodsArray() {
		ArrayList<String> methods = new ArrayList<String>();
		methods.add("getManufacturer");
		methods.add("getScreenSize");
		methods.add("getScreenResolution");
		methods.add("getScreenType");
		methods.add("getProcessor");
		methods.add("getTouchScreen");
		methods.add("getPhysicalCores");
		methods.add("getClockSpeed");
		methods.add("getRam");
		methods.add("getDiscStorage");
		methods.add("getDiscType");
		methods.add("getGraphicCard");
		methods.add("getGraphicCardMemory");
		methods.add("getOs");
		methods.add("getDiscReader");
		return methods;
	}
}
