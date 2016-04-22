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
	private JLabel lblRoute = new JLabel();
	private JLabel lblDateFrom = new JLabel();
	private JLabel lblTimeFrom = new JLabel();
	private JLabel lblDateTo = new JLabel();
	private JLabel lblTimeTo = new JLabel();
	private JLabel logo = new JLabel();
	private static JLabel errorMsg = new JLabel();
	ArrayList<String> values = new ArrayList<String>();
	private JTextArea dataView = new JTextArea();
	
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
		int H = 900;
		int W = 1600;
		rootWindow.setSize(W, H);
		rootWindow.setLocation((width / 2 - W/2), (height / 2 - H/2));
		
		

		lblIntro.setBounds(10, 10, 1200, 20);
		lblIntro.setText("Welcome to understanding your routes. To get started, use the below route, date & time selectors to set what data you'd like to see. Then click search to see the current timing data for");
		lblIntro.setFont(Global.font2);
		cp.add(lblIntro);
		
		lblIntro2.setBounds(10, 27, 1200, 20);
		lblIntro2.setText("the route. To import new data into the system, click the import button on the right hand side.");
		lblIntro2.setFont(Global.font2);
		cp.add(lblIntro2);
		
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
		
		
		dataView.setBounds(10, 170, 1560, 670);
		dataView.setFont(Global.font2);
		cp.add(dataView);
		
		
		
		
		
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
		}
	
		if(c == theBtDateFrom){
			tfDateFrom.setText(new DatePicker(frameDateFrom, 660, 280, "Date from").setPickedDate());
		}
		
		if(c == theBtDateTo){
			tfDateTo.setText(new DatePicker(frameDateTo, 945, 280, "Date to").setPickedDate());
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

	
}
