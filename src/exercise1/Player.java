package exercise1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


//import jdbc.StudentScreen.ButtonHandler;



public class Player extends JFrame {
	//driver and database info
	static final String DRIVER = "oracle.jdbc.OracleDriver";             
	static final String DATABASE_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
	static final String USERNAME = "COMP214F21_009_P_36";
	static final String PASSWORD = "password";
	//GUI objects
	private JButton insert, show,delete, update, first,previous,next,last;
	private JTextArea display;
	private JPanel pEntry;
	private JLabel lblFirstName,lblPlayerId,lblLastName,lblAddress,lblPostalCode,lblProvince,lblPhoneNumber,lblgameTitle;
	private JTextField txtFirstName,txtPlayerId, txtLastName,txtAddress,txtPostalCode,txtProvince,txtPhoneNumber,txtgameTitle;
	
	private Player player;
	
	//JDBC objects
	Connection c ;
	Statement st;
	PreparedStatement pst;

	//
	public Player()
	{
		//create and add the text area to south area of the frame
		display=new JTextArea(10,10);
		display.setFont(new Font("Arial", Font.BOLD, 24));
		
		JScrollPane scrollPane = new JScrollPane(display);
		add(scrollPane,BorderLayout.SOUTH);
		//
		pEntry =new JPanel();
		GridBagLayout grid = new GridBagLayout();
		pEntry.setLayout (grid);
		//
		lblPlayerId=new JLabel("Player ID:");
		lblPlayerId.setFont(new Font("Arial", Font.BOLD, 24));
 		//
		txtPlayerId = new JTextField(20);
		txtPlayerId.setFont(new Font("Arial", Font.BOLD, 24));
		//
		lblFirstName=new JLabel("First Name:");
		lblFirstName.setFont(new Font("Arial", Font.BOLD, 24));
		//
		txtFirstName=new JTextField(20);
		txtFirstName.setFont(new Font("Arial", Font.BOLD, 24));
		//
		lblAddress=new JLabel("Address:");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 24));
		//
		txtAddress=new JTextField(20);
		txtAddress.setFont(new Font("Arial", Font.BOLD, 24));

		//Add new content block here**************************************************1*4
		lblLastName=new JLabel("LastName:");
		lblLastName.setFont(new Font("Arial", Font.BOLD, 24));
		
		txtLastName=new JTextField(20);
		txtLastName.setFont(new Font("Arial", Font.BOLD, 24));
		
		lblPostalCode=new JLabel("Postal Code:");
		lblPostalCode.setFont(new Font("Arial", Font.BOLD, 24));
		
		txtPostalCode=new JTextField(20);
		txtPostalCode.setFont(new Font("Arial", Font.BOLD, 24));
		
		lblProvince=new JLabel("Province:");
		lblProvince.setFont(new Font("Arial", Font.BOLD, 24));
		
		txtProvince=new JTextField(20);
		txtProvince.setFont(new Font("Arial", Font.BOLD, 24));
		
		lblPhoneNumber=new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Arial", Font.BOLD, 24));
		
		txtPhoneNumber=new JTextField(20);
		txtPhoneNumber.setFont(new Font("Arial", Font.BOLD, 24));

		//lblgameTitle=new JLabel("Game Title:");
		//lblgameTitle.setFont(new Font("Arial", Font.BOLD, 24));
		
		//txtgameTitle=new JTextField(20);
		//txtgameTitle.setFont(new Font("Arial", Font.BOLD, 24));
		
		//
		insert=new JButton("Insert");
		insert.setFont(new Font("Arial", Font.BOLD, 24));
		
		show = new JButton("Display");
		show.setFont(new Font("Arial", Font.BOLD, 24));
		//Add button here ******************************************************
		delete = new JButton("Delete");
		delete.setFont(new Font("Arial", Font.BOLD, 24));
		
		update = new JButton("Update");
		update.setFont(new Font("Arial", Font.BOLD, 24));
		
		first = new JButton("First");
		first.setFont(new Font("Arial", Font.BOLD, 24));
		
		previous = new JButton("Previous");
		previous.setFont(new Font("Arial", Font.BOLD, 24));
		
		next = new JButton("Next");
		next.setFont(new Font("Arial", Font.BOLD, 24));
		
		last = new JButton("Last");
		last.setFont(new Font("Arial", Font.BOLD, 24));
  
		//add components to the grid
		//gridx - column, gridy - row
		
		addComponent(pEntry, grid, lblPlayerId, 0,0,1,1);
		addComponent(pEntry, grid, txtPlayerId, 1,0,1,1);
		
		addComponent(pEntry, grid, lblFirstName, 0,1,1,1);
		addComponent(pEntry, grid, txtFirstName, 1,1,1,1);
		
		addComponent(pEntry, grid, insert, 2,0,1,1);
		
		addComponent(pEntry, grid, lblLastName, 0,2,1,1);
		addComponent(pEntry, grid, txtLastName, 1,2,1,1);
		//Add new content block here************************************************1*2  add second number++
		addComponent(pEntry, grid, lblAddress, 0,3,1,1);
		addComponent(pEntry, grid, txtAddress, 1,3,1,1);
		
		addComponent(pEntry, grid, lblPostalCode, 0,4,1,1);
		addComponent(pEntry, grid, txtPostalCode, 1,4,1,1);
		
		addComponent(pEntry, grid, lblProvince, 0,5,1,1);
		addComponent(pEntry, grid, txtProvince, 1,5,1,1);
		
		addComponent(pEntry, grid, lblPhoneNumber, 0,6,1,1);
		addComponent(pEntry, grid, txtPhoneNumber, 1,6,1,1);
		
		//addComponent(pEntry, grid, lblgameTitle, 0,7,1,1);
		//addComponent(pEntry, grid, txtgameTitle, 1,7,1,1);
		
		addComponent(pEntry, grid, show, 2,1,1,1);
		addComponent(pEntry, grid, update, 2,2,1,1);
		addComponent(pEntry, grid, delete, 2,3,1,1);
		
		addComponent(pEntry, grid, first,3,0,1,1);
		addComponent(pEntry, grid, previous, 3,1,1,1);
		addComponent(pEntry, grid, next, 3,2,5,1);
		addComponent(pEntry, grid, last, 3,3,6,1);
		  	
		add(pEntry,BorderLayout.WEST);
	
		//
		ButtonHandler bHandler= new ButtonHandler();
		insert.addActionListener(bHandler);
		show.addActionListener(bHandler);
		//Added ********************************
		update.addActionListener(bHandler);
		delete.addActionListener(bHandler);
		//

		connect();
		
		player = new Player();
		
		
	}
	public void addComponent(JPanel p, GridBagLayout grid, Component c, int gridx, int gridy,
			int gridwidth, int gridheight)
	{
		GridBagConstraints constr = new GridBagConstraints();
		constr.gridx = gridx; //column
		constr.gridy = gridy; //row
		constr.gridwidth = gridwidth; //number of cells in the row that will be covered
		constr.gridheight = gridheight; //number of cells in the column that will be covered
		constr.fill = GridBagConstraints.HORIZONTAL; //resize the component horizontally
		// add the component 
	    grid.setConstraints(c, constr); //apply the constraints to the grid
	    p.add(c);
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==insert)
			{
				//Add new content block here************************************************1*1
				String PlayerId = txtPlayerId.getText();
				String FirstName = txtFirstName.getText();
				String LastName = txtLastName.getText();
				String Address = txtAddress.getText();
				String PostalCode = txtPostalCode.getText();
				String Province = txtProvince.getText();
				String PhoneNumber = txtPhoneNumber.getText();
				//String gameTitle = txtgameTitle.getText();
				//int PhoneNumber = Integer.parseInt(txtPhoneNumber.getText());
				//double PhoneNumber = Double.parseDouble(txtPhoneNumber.getText());
				//Add new content block here ************************************************1*1
				insertRow(PlayerId,FirstName, LastName,Address,PostalCode,Province,PhoneNumber);
			}
				else if(e.getSource()==show)
				displayResults();
		}
		
	}
	//
	public void connect()
	{
		try
		{			
			Class.forName( DRIVER );
		      // establish connection to database                              
			c = DriverManager.getConnection( DATABASE_URL,USERNAME, PASSWORD);
				
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
		}

	}
	//
	public void displayResults()
	{
		try
		{
			st = c.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM player");
			ResultSetMetaData md = rs.getMetaData();
			int row=0;
			String info="";
			while(rs.next())
			{
				for( int i=1;i <= md.getColumnCount();i++)
				{
					info+=md.getColumnName(i)+"\t: "+rs.getObject(i)+"\t"; 
				}
				row+=1;
				info+="\n";
			}
			display.setText(info);
			rs.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
		}
	}
////Add new content block here************************************************to argument
	public void insertRow(String PlayerId, String FirstName, String LastName, String Address, String PostalCode,String Province,String PhoneNumber)
	{
		try {
			
			//Add new content block here************************************************1*2
			pst = c.prepareStatement("Insert into player (PLAYER_ID, FIRST_NAME, LAST_NAME,ADDRESS,POSTAL_CODE,PROVINCE,PHONE_NUMBER) VALUES(?,?,?,?,?,?,?)");
			pst.setString(1, PlayerId);
			pst.setString(2, FirstName); 
			pst.setString(3, LastName);
			pst.setString(4, Address);
			pst.setString(5, PostalCode);
			pst.setString(6, Province);
			pst.setString(7, PhoneNumber);
			//pst.setString(8, gameTitle);
			//Execute the prepared statement using executeUpdate method:  
			int val = pst.executeUpdate(); //returns the row count
			pst.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.out.println("Done!");
		}
	}
	
	//Added Delete function*************************************
	
	
	public static void main(String[] args) {
		JFrame frame = new Player();
		frame.setSize(800,300);
		frame.pack();
		frame.setVisible(true);
		
	}
	
}


