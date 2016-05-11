import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class r_form extends javax.swing.JFrame implements ActionListener{
	
    private JFrame r_frame = null;
    
    private JPanel new_r = null;
    private JPanel r_fields = null;
    private JPanel m_fields = null;
    private JPanel r_actions = null;
    
    private JButton ok_button = null;
    private JButton exit_button = null;
    
    private JComboBox movies_combo = null;
    
    private JLabel f_name = null;
    private JLabel l_name = null;
    private JLabel phone = null;
    private JLabel c_movie = null;
    private JLabel c_hall = null;
    private JLabel hall = null; 
    private JLabel no_seats = null;
    private JLabel number_of_seats_left = null;
    private JLabel no_seats_left = null;
    

    private JTextField f_n_txtFld = null;
    private JTextField l_n_txtFld = null;
    private JTextField phone_txtFld = null;
    private JTextField no_seats_txtFld = null;
    
    private SQL v_m_h = null; //adikeimeno tupou SQL gia emfanish onomatos tainias kai aithousas
    private SQL insert_res = null;//adikeimeno tupou SQL gia eisagwgh reservation
    
 	private String[][] temp_results;//
 	private int m_n;//plithos tainiwn sto pinaka movie

    
    /** Creates new form cinema_main_form */
    public r_form() {

		
		r_frame = new JFrame();//main frame for reservation
		new_r = new javax.swing.JPanel();//new reservation panel
		new_r.setLayout(new GridBagLayout());
		
		//initialize and add all components
        initComponents();
        
        // Add the panel to the frame.
		r_frame.getContentPane().add(new_r, BorderLayout.CENTER);
				
        //r_frame.setVisible(true);
        r_frame.setLocation(500,200);//child_location to be changed!!!
        //r_frame.setAlwaysOnTop(true);
        
        r_frame.pack();
        r_frame.show();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    public void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
		

        r_fields = new JPanel();//reservation fields panel
        m_fields = new JPanel();//movie fields panel
        r_actions = new JPanel();//reservation actions 
        
        f_name = new JLabel();//first name label
        l_name = new JLabel();//last name label
        phone = new JLabel();//phone label
        
        f_n_txtFld = new JTextField(10);//first name txt field
        l_n_txtFld = new JTextField(10);//last name txt field
        phone_txtFld = new JTextField(10);//phone txt field

        movies_combo = new JComboBox();//movies combo box
        c_movie = new JLabel();//choose movie label
        c_hall = new JLabel();
        hall = new JLabel();//hall label
        no_seats_txtFld = new JTextField(10);///number of seats txt field
        no_seats = new JLabel();//number of seats label
        
        number_of_seats_left = new JLabel();//number of seats left label(doesn't change)
        no_seats_left = new JLabel();//number of seats left label (changes)

        ok_button = new JButton();//ok button
        exit_button = new JButton();//exit button
        
        

        

        getContentPane().setLayout(new GridBagLayout());

        //setTitle("new_r");
        //setAlwaysOnTop(true);
        new_r.setLayout(new GridBagLayout());

        new_r.setBorder(new javax.swing.border.TitledBorder("New Reservation"));
        new_r.setPreferredSize(new Dimension(360, 450));
        r_fields.setLayout(new GridBagLayout());

        r_fields.setBorder(new javax.swing.border.TitledBorder("Reservator Fields"));
        r_fields.setPreferredSize(new Dimension(320, 150));
        f_name.setText("First Name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        r_fields.add(f_name, gridBagConstraints);

        l_name.setText("Last Name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        r_fields.add(l_name, gridBagConstraints);

        phone.setText("Phone");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        r_fields.add(phone, gridBagConstraints);

        //f_n_txtFld.setText("enter first name");
        f_n_txtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        r_fields.add(f_n_txtFld, gridBagConstraints);

        //l_n_txtFld.setText("enter last name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        r_fields.add(l_n_txtFld, gridBagConstraints);

        //phone_txtFld.setText("enter phone");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        r_fields.add(phone_txtFld, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        new_r.add(r_fields, gridBagConstraints);

        m_fields.setLayout(new GridBagLayout());

        m_fields.setBorder(new javax.swing.border.TitledBorder("Movie Fields"));
        m_fields.setPreferredSize(new Dimension(320, 150));
        movies_combo.setPreferredSize(new Dimension(100, 20));
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
       			System.out.println(temp_results[i][0]+"/"+temp_results[i][1]);
       			System.out.println();
       	}
        
        String[] m_titles = new String[m_n];
        
        for(int i=0;i<m_n;i++){
        	m_titles[i] = temp_results[i][0];
    	}
        //String[] m_titles = {temp_results[0][0],temp_results[1][0],temp_results[2][0],temp_results[3][0],temp_results[4][0],temp_results[5][0]};
        movies_combo = new JComboBox(m_titles);
        movies_combo.setSelectedIndex(0);
        
        
        movies_combo.addActionListener(this);
        

        
        
        m_fields.add(movies_combo, gridBagConstraints);

        c_movie.setText("Choose Movie");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        m_fields.add(c_movie, gridBagConstraints);

        
      	c_hall.setText("Hall:");
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        m_fields.add(c_hall, gridBagConstraints);
        
        hall.setText("hall name:");
        hall.setText(temp_results[0][1]);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        m_fields.add(hall, gridBagConstraints);

        //no_seats_txtFld.setText("number of seats");
        no_seats_txtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        m_fields.add(no_seats_txtFld, gridBagConstraints);

        no_seats.setText("Enter Number of Seats");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        m_fields.add(no_seats, gridBagConstraints);
		
		
		number_of_seats_left.setText("Seats left in hall");
		gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        m_fields.add(number_of_seats_left, gridBagConstraints);
		
		
        //no_seats_left.setText("Seats left in hall");
        no_seats_left.setText(temp_results[0][2]);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        m_fields.add(no_seats_left, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        new_r.add(m_fields, gridBagConstraints);

        r_actions.setLayout(new GridBagLayout());

        r_actions.setBorder(new javax.swing.border.TitledBorder("Actions"));
        r_actions.setPreferredSize(new Dimension(320, 70));
        ok_button.setText("OK");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        
        
        //ok_button action
        ok_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
			

  				if (f_n_txtFld.getText().equals("") || l_n_txtFld.getText().equals("") || phone_txtFld.getText().equals("")){
  						JOptionPane.showMessageDialog(r_frame, "Missing Reservation field \n found.");
  				}
  				else if(no_seats_txtFld.getText().equals("")){
  						JOptionPane.showMessageDialog(r_frame, "Please, enter a number \n of seats.");
  				}
  				else{
  					
  					Object[] options = {" Yes ", " No "};
                    int n = JOptionPane.showOptionDialog(r_frame,
                                    "Do you want to \n create this reservation?",
                                    "A Follow-up Question",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
  					
  					if (n == JOptionPane.YES_OPTION) {
  						insert_res = new SQL();
  					
            			//Gets the value
            			String f_name="";
            			f_name += f_n_txtFld.getText();
            			String l_name="";
            			l_name += l_n_txtFld.getText();        	
            			int phone_no = Integer.parseInt(phone_txtFld.getText());
            			int s_number = Integer.parseInt(no_seats_txtFld.getText());//number of seats
            			String movie_name="";
            			movie_name += (temp_results[movies_combo.getSelectedIndex()][0]);
            			int movie_id = insert_res.get_movie_id(movie_name);//get movie id
            			int plays_id = insert_res.get_plays_id(movie_id);//get p_id
  					
  						//Create Object
   						reservation r_obj = new reservation(plays_id,f_name,l_name,phone_no,s_number);
   						
   						//CHECKING SEATS
   						if (insert_res.CheckSeats(r_obj)){
   						
   							//send the object to a query
  							insert_res.create_r(r_obj);
  						
  							//RETRUN THE CODE 
  							JOptionPane.showMessageDialog(r_frame, "The reservation code is \n \n "+insert_res.get_r_id(r_obj));
  							//update the fields IF exist
            				f_n_txtFld.setText("");
            				l_n_txtFld.setText("");
            				phone_txtFld.setText("");
            				no_seats_txtFld.setText("");
            				
            				
            				//
        //eisagwgh olwn twn movie names sto combo box movies_combo
        v_m_h = new SQL();//adikeimeno SQL gia klish methodwn
		temp_results = new String[m_n][4];
		
		m_n = v_m_h.movie_number();//klish methodou movie_number() ths r_form
		temp_results = v_m_h.view_m_h();
        
        String[] m_titles = new String[m_n];
        
        for(int i=0;i<m_n;i++){
        	m_titles[i] = temp_results[i][0];
    	}
        movies_combo = new JComboBox(m_titles);
        movies_combo.setSelectedIndex(0);
        movies_combo.addActionListener(this);
            				//
  						}
  						else{JOptionPane.showMessageDialog(r_frame, "Too many seats.");}
  					}
  					else{
  					
  					//update the fields IF exist
            		f_n_txtFld.setText("");
            		l_n_txtFld.setText("");
            		phone_txtFld.setText("");
            		no_seats_txtFld.setText("");
            		}

  				}
            	
            }//actionPerformed
		});


        r_actions.add(ok_button, gridBagConstraints);

        exit_button.setText("Exit");
      

        exit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	r_frame.dispose();//closes r_frame after button is pressed
            }//actionPerformed
		});


        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        r_actions.add(exit_button, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        new_r.add(r_actions, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(new_r, gridBagConstraints);

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-500)/2, 400, 500);
        

        
    }//initComponents()



	public void actionPerformed(ActionEvent evt){
		if("comboBoxChanged".equals(evt.getActionCommand())){
			hall.setText(temp_results[(movies_combo.getSelectedIndex())][1]);
			no_seats_left.setText(temp_results[(movies_combo.getSelectedIndex())][2]);
		}//if
	}//actionPerformed
					
	
    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
            System.exit(0);
        // TODO add your handling code here:
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        
    
    
    
}//class