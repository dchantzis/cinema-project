import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;


public class v_form extends javax.swing.JFrame implements ActionListener{
    
    
    private JFrame v_frame = null;
    
    private JPanel v_actions = null;
    private JPanel v_entries = null;
    private JPanel v_panel = null;
    
    private JLabel v_hall = null;//"Hall" label
    private JLabel v_movie = null;//"Choose movie:" label
	private JLabel from = null;//"from" label
    private JLabel hall = null;//hall name (changes)
    private JLabel r_seats = null;//"Reserved Seats" label
    private JLabel r_seats_all = null;//all the seats for each movie
    private JLabel r_seats_taken = null;//reserved seats for each movie label
    
	private int r_seats_free;//all the seats for each movie
    
    private JComboBox v_combo = null;
    private JButton v_exit = null;

   	private SQL v_m_h = null; //adikeimeno tupou SQL gia emfanish onomatos tainias kai aithousas

	private String[][] temp_results;//
 	private int m_n;//plithos tainiwn sto pinaka movie
    
    /** Creates new form v_frame */
    public v_form() {

		v_frame = new JFrame();//main frame for view all reservations
		v_panel = new JPanel();//
				
		v_panel.setLayout(new java.awt.GridBagLayout());

		//initialize and add all components
        initComponents();

        // Add the panel to the frame.
		v_frame.getContentPane().add(v_panel, BorderLayout.CENTER);


        v_frame.setVisible(true);
        v_frame.setLocation(500,200);//child_location to be changed!!!
        //v_frame.setAlwaysOnTop(true);

        v_frame.pack();
        v_frame.show(); 
    }
    

    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        v_entries = new JPanel();
        v_combo = new JComboBox();
        v_movie = new JLabel();
        v_hall = new JLabel();
        hall = new JLabel();
        r_seats = new JLabel();
        r_seats_taken = new JLabel();
        from = new JLabel();
        r_seats_all = new JLabel();
        v_actions = new JPanel();
        v_exit = new JButton();

        getContentPane().setLayout(new GridBagLayout());

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        v_panel.setLayout(new GridBagLayout());

        v_panel.setBorder(new javax.swing.border.TitledBorder("Display Number of Reservated Seats"));
        v_panel.setPreferredSize(new Dimension(360, 250));
        v_entries.setLayout(new GridBagLayout());

        v_entries.setBorder(new javax.swing.border.TitledBorder("View Entries"));
        v_entries.setPreferredSize(new Dimension(320, 120));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        
        
        
        //eisagwgh olwn twn movie names sto combo box movies_combo
        v_m_h = new SQL();//adikeimeno SQL gia klish methodwn
        
        
        
		temp_results = new String[m_n][4];
		
		m_n = v_m_h.movie_number();//klish methodou movie_number() ths r_form
		temp_results = v_m_h.view_m_h();

        for(int i=0;i<m_n;i++)
       	{
       		System.out.println(temp_results[i][0]+"/"+temp_results[i][1]+"/"+temp_results[i][2]+" seats left");
       		System.out.println();
       	}
        
        
        String[] m_titles = new String[m_n];
        
        for(int i=0;i<m_n;i++){
        	m_titles[i] = temp_results[i][0];
    	}
        v_combo = new JComboBox(m_titles);
        v_combo.setSelectedIndex(0);
		v_combo.addActionListener(this);
       //telos eisagwghs movie names    
        
        
        v_entries.add(v_combo, gridBagConstraints);

        v_movie.setText("Choose Movie:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        v_entries.add(v_movie, gridBagConstraints);

        v_hall.setText("Hall:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        v_entries.add(v_hall, gridBagConstraints);

        hall.setText("hall name");
        hall.setText(temp_results[0][1]);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        v_entries.add(hall, gridBagConstraints);

        r_seats.setText("Reserved Seats:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        v_entries.add(r_seats, gridBagConstraints);

        //r_seats_taken.setText("Reserved Seats Taken");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        v_entries.add(r_seats_taken, gridBagConstraints);

        from.setText("from");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        v_entries.add(from, gridBagConstraints);

        //r_seats_all.setText("jLabel7");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        v_entries.add(r_seats_all, gridBagConstraints);

        v_panel.add(v_entries, new GridBagConstraints());

        v_actions.setLayout(new GridBagLayout());

        v_actions.setBorder(new javax.swing.border.TitledBorder("Actions"));
        v_actions.setPreferredSize(new Dimension(320, 70));
        v_exit.setText("Exit");
        
        v_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	v_frame.dispose();//closes v_frame after button is pressed
            }//actionPerformed
		});
		
        v_actions.add(v_exit, new GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        v_panel.add(v_actions, gridBagConstraints);

        getContentPane().add(v_panel, new GridBagConstraints());

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-500)/2, 400, 300);
    }//initComponents()

	public void actionPerformed(ActionEvent evt){
		if("comboBoxChanged".equals(evt.getActionCommand())){
			
			String t = "";//temp string
			
			t = temp_results[(v_combo.getSelectedIndex())][2];//has number of free seats
			hall.setText(temp_results[(v_combo.getSelectedIndex())][1]);//name of hall				
			r_seats_all.setText(temp_results[(v_combo.getSelectedIndex())][3]);//number of all seats
			
			r_seats_free = Integer.parseInt(t);//free seats
			t = temp_results[(v_combo.getSelectedIndex())][3]; //has number of all seats
			int all_seats = 0;
			all_seats = Integer.parseInt(t);
			int z = all_seats-r_seats_free;
			
			t="";
			t+=z;	

			r_seats_taken.setText(t);
		
		}//if
	}//actionPerformed


}//class
