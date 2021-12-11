package exercise1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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



public class Game extends JFrame {
	//driver and database info
	static final String DRIVER = "oracle.jdbc.OracleDriver";             
	static final String DATABASE_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
	static final String USERNAME = "COMP214F21_009_P_36";
	static final String PASSWORD = "password";
	//GUI objects
	private JButton insert, show;
	private JTextArea display;
	private JPanel pEntry;
	private JLabel lblTitle,lblGameId,lblPrice;
	private JTextField txtTitle,txtGameId, txtPrice;
	
	//JDBC objects
	Connection c ;
	Statement st;
	PreparedStatement pst;

	//
	public Game()
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
		lblGameId=new JLabel("Game ID:");
		lblGameId.setFont(new Font("Arial", Font.BOLD, 24));
 		//
		txtGameId = new JTextField(20);
		txtGameId.setFont(new Font("Arial", Font.BOLD, 24));
		//
		lblTitle=new JLabel("Title:");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		//
		txtTitle=new JTextField(20);
		txtTitle.setFont(new Font("Arial", Font.BOLD, 24));
		

		//
		insert=new JButton("Insert");
		insert.setFont(new Font("Arial", Font.BOLD, 24));
		//
		show = new JButton("Show");
		show.setFont(new Font("Arial", Font.BOLD, 24));
  
		//add components to the grid
		//gridx - column, gridy - row
		addComponent(pEntry, grid, lblGameId, 0,0,1,1);
		addComponent(pEntry, grid, txtGameId, 1,0,1,1);
		
		addComponent(pEntry, grid, lblTitle, 0,1,1,1);
		addComponent(pEntry, grid, txtTitle, 1,1,1,1);
		
		addComponent(pEntry, grid, insert, 2,0,1,1);
		
		
		
		addComponent(pEntry, grid, show, 2,1,1,1);
		  	
		add(pEntry,BorderLayout.WEST);
		
		//
		ButtonHandler bHandler= new ButtonHandler();
		insert.addActionListener(bHandler);
		show.addActionListener(bHandler);
		//
		connect();
		
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
				String GameId = txtGameId.getText();
				String Title = txtTitle.getText();
				insertRow(GameId,Title);
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
			
			ResultSet rs = st.executeQuery("SELECT * FROM game");
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
	//
	public void insertRow(String GameId, String Title)
	{
		try {
			
			pst = c.prepareStatement("Insert into game (GAME_ID, GAME_TITLE) VALUES(?,?)");
			pst.setString(1, GameId);
			pst.setString(2, Title); 
			
			
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
	public static void main(String[] args) {
		JFrame frame = new Game();
		frame.setSize(800,300);
		frame.pack();
		frame.setVisible(true);
		
	}
	
}


