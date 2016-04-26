package Display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.xml.bind.JAXBElement.GlobalScope;




public class StatisticsGUI extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class Names {
		public static final String IMPORT = "Import";
		public static final String SEARCH = "Search";
		public static final String CLEAR = "Clear";
		public static final String DATEFROM = "...";
		public static final String DATETO = "...";
	}
	

	private JButton theBtImport = new JButton(Names.IMPORT);	
	private JButton theBtSearch = new JButton(Names.SEARCH);
	private JButton theBtClear = new JButton(Names.CLEAR);
	private Actions theCB = new Actions();
	static Container rootWindow;
	private JComboBox<String> routeSelect = new JComboBox<String>();
	
	private SpinnerDateModel smFrom = new SpinnerDateModel();
	private SpinnerDateModel smTo = new SpinnerDateModel();
	private JSpinner timeFromSpinner = new JSpinner(smFrom);
	private JSpinner timeToSpinner = new JSpinner(smTo);
	private JSpinner.DateEditor deFrom = new JSpinner.DateEditor(timeFromSpinner, "HH:mm:ss");
	private JSpinner.DateEditor deTo = new JSpinner.DateEditor(timeToSpinner, "HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	private JLabel lblIntro = new JLabel();
	private JLabel lblIntro2 = new JLabel();
	private JLabel lblIntro3 = new JLabel();
	private JLabel lblRoute = new JLabel();
	private JLabel lblDateFrom = new JLabel();
	private JLabel lblTimeFrom = new JLabel();
	private JLabel lblDateTo = new JLabel();
	private JLabel lblTimeTo = new JLabel();
	private JLabel logo = new JLabel();
	private JLabel lblRouteStatistics = new JLabel();
	private static JLabel errorMsg = new JLabel();
	ArrayList<String> values = new ArrayList<String>();
	private static JTextPane dataView = new JTextPane();
	private static JScrollPane dataViewScroll = new JScrollPane(dataView);
	private static StyledDocument doc = dataView.getStyledDocument();
	private static JTextPane dataViewStats = new JTextPane();
	private static JScrollPane dataViewScrollStats = new JScrollPane(dataViewStats);
	private static StyledDocument doc2 = dataViewStats.getStyledDocument();
	
	private JButton theBtDateFrom = new JButton(Names.DATEFROM);
	private JTextField tfDateFrom = new JTextField();
	private JFrame frameDateFrom = new JFrame();
	
	private JButton theBtDateTo = new JButton(Names.DATETO);
	private JTextField tfDateTo = new JTextField();
	private JFrame frameDateTo = new JFrame();
	
	Date dateStartSpinner = cal.getTime();
	 
	public static String routeSelected = "";
	public static String dateFrom = "";
	public static String timeFrom = "";
	public static String dateTo = "";
	public static String timeTo = "";
	
	public static String dateTimeFrom = "";
	public static String dateTimeTo = "";
	
	
	public StatisticsGUI(RootPaneContainer rpc) { 
		
		Container cp = rpc.getContentPane();
		rootWindow = (Container) rpc;
		cp.setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		int H = 1000;
		int W = 1600;
		rootWindow.setSize(W, H);
		rootWindow.setLocation((width / 2 - W/2), (height / 2 - H/2) - H/40);
		
		

		lblIntro.setBounds(10, 10, 1200, 20);
		lblIntro.setText("Welcome to understanding your routes. To get started, use the below route, date & time selectors to set your search parameters, then click search. This displays all of the route");
		lblIntro.setFont(Global.font2);
		cp.add(lblIntro);
		
		lblIntro2.setBounds(10, 27, 1200, 20);
		lblIntro2.setText("information that is in the system in the top box, and a summary of the statistics for the route based on this data at the bottom. To import new data into the system, click the import");
		lblIntro2.setFont(Global.font2);
		cp.add(lblIntro2);
		
		lblIntro3.setBounds(10, 44, 1200, 20);
		lblIntro3.setText("button on the right hand side and follow the on screen instructions.");
		lblIntro3.setFont(Global.font2);
		cp.add(lblIntro3);
		
		logo.setIcon(new ImageIcon("logo.png"));
		logo.setBounds(1283, 0, 300, 150);
        cp.add(logo);
		
        
		lblRoute.setBounds(10, 95, 100, 30);
		lblRoute.setText("Routes");
		lblRoute.setFont(Global.font1);
		cp.add(lblRoute);
		
		lblDateFrom.setBounds(500, 95, 100, 30);
		lblDateFrom.setText("Date from");
		lblDateFrom.setFont(Global.font1);
		cp.add(lblDateFrom);
		
		lblTimeFrom.setBounds(645, 95, 100, 30);
		lblTimeFrom.setText("Time from");
		lblTimeFrom.setFont(Global.font1);
		cp.add(lblTimeFrom);
		
		lblDateTo.setBounds(785, 95, 100, 30);
		lblDateTo.setText("Date to");
		lblDateTo.setFont(Global.font1);
		cp.add(lblDateTo);
		
		lblTimeTo.setBounds(930, 95, 100, 30);
		lblTimeTo.setText("Time to");
		lblTimeTo.setFont(Global.font1);
		cp.add(lblTimeTo);
		
		errorMsg.setBounds(500, 60, 530, 30);
		errorMsg.setFont(Global.font1);
		errorMsg.setForeground(Color.RED);
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setVerticalAlignment(SwingConstants.CENTER);
		cp.add(errorMsg);
		
		
		routeSelect.setBounds(10, 125, 450, 25);
		routeSelect.addActionListener(theCB);
		routeSelect.addItem("");
		routeSelect.setFont(Global.font2);
		values = RouteSelectValues.populateAllRoutes();	
		for(int a = 0; a < values.size(); a++){
			routeSelect.addItem(values.get(a));
		}
		cp.add(routeSelect);
		
		tfDateFrom.setBounds(500, 125, 100, 25);
		tfDateFrom.setFont(Global.font2);
		cp.add(tfDateFrom);
		 
		theBtDateFrom.setBounds(600, 125, 30, 25);
		theBtDateFrom.addActionListener(theCB);
		theBtDateFrom.setFont(Global.font2);
		cp.add(theBtDateFrom);
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		dateStartSpinner = cal.getTime();
		
		timeFromSpinner.setValue(dateStartSpinner);
		timeFromSpinner.setEditor(deFrom);
		timeFromSpinner.setBounds(645, 125, 100, 25);
		timeFromSpinner.setFont(Global.font2);
		cp.add(timeFromSpinner);
		
		tfDateTo.setBounds(785, 125, 100, 25);
		tfDateTo.setFont(Global.font2);
		cp.add(tfDateTo);
		 
		theBtDateTo.setBounds(885, 125, 30, 25);
		theBtDateTo.addActionListener(theCB);
		theBtDateTo.setFont(Global.font2);
		cp.add(theBtDateTo);
		
		timeToSpinner.setValue(dateStartSpinner);
		timeToSpinner.setEditor(deTo);
		timeToSpinner.setBounds(930, 125, 100, 25);
		timeToSpinner.setFont(Global.font2);
		cp.add(timeToSpinner);
		
		
		theBtClear.setBounds(1070, 120, 100, 30);
		theBtClear.addActionListener(theCB);
		theBtClear.setFont(Global.font1);
		cp.add(theBtClear);
		
		theBtSearch.setBounds(1176, 120, 100, 30);
		theBtSearch.addActionListener(theCB);
		theBtSearch.setFont(Global.font1);
		cp.add(theBtSearch);
		
		theBtImport.setBounds(1176, 40, 100, 30);
		theBtImport.addActionListener(theCB);
		theBtImport.setFont(Global.font1);
		cp.add(theBtImport);
		
		
		dataViewScroll.setBounds(10, 170, 1560, 480);
		//dataViewScroll.setFont(Global.font4);
		dataViewScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		dataViewScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		dataView.setEditable(false);
		dataView.setFont(Global.font4);
		cp.add(dataViewScroll);
		
		lblRouteStatistics.setBounds(10, 665, 200, 30);
		lblRouteStatistics.setText("Route Statistics");
		lblRouteStatistics.setFont(Global.font1);
		cp.add(lblRouteStatistics);
		
		dataViewScrollStats.setBounds(10, 695, 1560, 250);
		//dataViewScroll.setFont(Global.font4);
		dataViewScrollStats.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		dataViewScrollStats.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dataViewStats.setEditable(false);
		dataViewStats.setFont(Global.font4);
		cp.add(dataViewScrollStats);
		
		rootWindow.setVisible(true);
		
	}

	class Actions implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			String actionIs = ae.getActionCommand();
			Object c = ae.getSource();
			
			if(actionIs.equals(Names.IMPORT)){
				rootWindow.setVisible(false);
				Main.displayImportGUI();
			}
		
			if(actionIs.equals(Names.CLEAR)){
			routeSelect.setSelectedIndex(0);
			tfDateFrom.setText("");
			timeFromSpinner.setValue(dateStartSpinner);
			tfDateTo.setText("");
			timeToSpinner.setValue(dateStartSpinner);
			clearDataView();
		}
	
		if(c == theBtDateFrom){
			tfDateFrom.setText(new DatePicker(frameDateFrom, 660, 205, "Date from").setPickedDate());
		}
		
		if(c == theBtDateTo){
			tfDateTo.setText(new DatePicker(frameDateTo, 945, 205, "Date to").setPickedDate());
		}

		if(actionIs.equals(Names.SEARCH)){
			routeSelected = routeSelect.getSelectedItem().toString();
			dateFrom = tfDateFrom.getText();
			timeFrom = deFrom.getFormat().format(timeFromSpinner.getValue());	
			dateTo = tfDateTo.getText();
			timeTo = deTo.getFormat().format(timeToSpinner.getValue());
			
			if(checkSearch.checkSearch(routeSelected, dateFrom, timeFrom, dateTo, timeTo)){
				dateTimeFrom = tfDateFrom.getText() + " " + deFrom.getFormat().format(timeFromSpinner.getValue());
				dateTimeTo = tfDateTo.getText() + " " + deTo.getFormat().format(timeToSpinner.getValue());
				new DataForDisplay(routeSelected, dateTimeFrom, dateTimeTo);
			}
		}		
	}
}
	
public static void setStatisticsGUIVisible(){
	rootWindow.setVisible(true);
}
	
public static void setErrorMsg(String msg){
	errorMsg.setText(msg);
}

public static void addToDataViewFont1(String toPrint){
	
	
	Style style = dataView.addStyle("", null);
	StyleConstants.setFontSize(style, 16);
	StyleConstants.setBold(style, true);
	try {
		doc.insertString(doc.getLength(), toPrint, style);
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//dataView.append(toPrint);
}

public static void addToDataViewFont2(String toPrint){
	
	Style style = dataView.addStyle("", null);
	StyleConstants.setFontSize(style, 12);
	try {
		doc.insertString(doc.getLength(), toPrint, style);
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//dataView.append(toPrint);
	
	
}

public static void clearDataView(){
	dataView.setText("");
	dataViewStats.setText("");
}


public static void addToDataViewStatsFont1(String toPrint){

	Style style = dataViewStats.addStyle("", null);
	StyleConstants.setFontSize(style, 16);
	StyleConstants.setBold(style, true);
	try {
		doc2.insertString(doc2.getLength(), toPrint, style);
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//dataView.append(toPrint);
}

public static void addToDataViewStatsFont2(String toPrint){
	
	Style style = dataViewStats.addStyle("", null);
	StyleConstants.setFontSize(style, 12);
	try {
		doc2.insertString(doc2.getLength(), toPrint, style);
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//dataView.append(toPrint);
	
	
}

public static void setScrollPos(){
	dataView.setCaretPosition(0);
	dataViewStats.setCaretPosition(0);
}
	
}
