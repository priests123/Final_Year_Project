package uyr;

/**
 * The code in this class isn't all of my own work, below is the reference:
 * Ykyuen (2011), Eureka Available at: https://eureka.ykyuen.info/2011/09/05/java-swing-datepicker-1/
 * (Accessed: 20th April 2016) 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class DatePicker {
	
	public int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	public int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	public JLabel label = new JLabel("", JLabel.CENTER);
	public String day = "";
	public JDialog d;
	public JButton[] button = new JButton[49];
	
	//Produces the calendar for a user to select a date
	public DatePicker(JFrame parent, int X, int Y, String name) {
		parent.setLocation(100,100);
	    d = new JDialog();
	    d.setModal(true);
	    String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
	    JPanel p1 = new JPanel(new GridLayout(7, 7));
	    p1.setPreferredSize(new Dimension(420, 120));
	 
	    for (int x = 0; x < button.length; x++) {
	      final int selection = x;
	      button[x] = new JButton();
	      button[x].setFocusPainted(false);
	      button[x].setBackground(Color.white);
	      if (x > 6) {
	        button[x].addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent ae) {
	            day = button[selection].getActionCommand();
	            d.dispose();
	          }
	        });
	      }
	      if (x < 7) {
	        button[x].setText(header[x]);
	        button[x].setFont(new Font("Arial", Font.BOLD, 11));
	        button[x].setForeground(Color.red);
	      }
	      p1.add(button[x]);
	    }
	    JPanel p2 = new JPanel(new GridLayout(1, 3));
	     
	    // Previous month button
	    JButton previous = new JButton("<< Previous");
	    previous.setFont(new Font("Arial", Font.PLAIN, 10));
	    previous.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        month--;
	        displayDate();
	      }
	    });
	    p2.add(previous);
	     
	    // Current month label between the previous and next buttons
	    p2.add(label);
	     
	    // Next month button
	    JButton next = new JButton("Next >>");
	    next.setFont(new Font("Arial", Font.PLAIN, 10));
	    next.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        month++;
	        displayDate();
	      }
	    });
	    p2.add(next);
	     
	    d.add(p1, BorderLayout.CENTER);
	    d.add(p2, BorderLayout.NORTH);
	    d.pack();
	   // d.setLocationRelativeTo(parent);
	    displayDate();
	    d.setLocation(X, Y);
	    d.setTitle(name);
	    d.setVisible(true);
	}
 
	public void displayDate() {
	    for (int x = 7; x < button.length; x++) {
	      button[x].setText("");
	    }
	     
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
	    java.util.Calendar cal = java.util.Calendar.getInstance();
	    cal.set(year, month, 1);
	    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
	    int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
	 
	    for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
	      button[x].setText("" + day);
	      button[x].setFont(new Font("Arial", Font.PLAIN, 10));
	    }
	   
	    label.setText(sdf.format(cal.getTime()));
	}
 
	//Returns the values selected by the user
	public String setPickedDate() {
	    if (day.equals("")) {
	      return day;
	    }
	   
	    // Set the return date format
	    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
	    java.util.Calendar cal = java.util.Calendar.getInstance();
	    cal.set(year, month, Integer.parseInt(day));
	    return sdf.format(cal.getTime());
	}
	
}