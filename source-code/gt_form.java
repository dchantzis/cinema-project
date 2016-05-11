import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class gt_form extends javax.swing.JFrame {
	//CHARGE OF THE TICKETS 
	private int charge = 7; 
	// Variables declaration - do not modify//
	private JFrame gt_frame = null;
	
    private JPanel gt_actions = null;
    private JPanel gt_code_panel = null;
    private JPanel gt_direct_panel = null;
    private JPanel gt_panel = null;
    
    private JLabel gt_charge_label = null;
    private JLabel gt_choose_movie = null;
    private JLabel gt_code = null;
	private JLabel gt_direct_charge = null;
	private JLabel gt_direct_seat_no = null;
	private JLabel gt_direct_show_charge = null;
	private JLabel gt_seat_no_label = null;
    private JLabel gt_show_charge = null;
    private JLabel gt_show_movie_name = null;
    private JLabel gt_show_seat_no = null;
    private JLabel gt_movie_label = null;
    
    private JTextField gt_codeFld = null;
    private JTextField gt_direct_seatsFld = null;
    
    private JButton gt_code_generate = null;
	private JButton gt_exit = null;
    private JButton gt_findR = null;
    private JButton gt_direct_generate = null;


    
    private JComboBox gt_moviesCombo = null;
    String temp_movie_title ="";
    
    private SQL v_m = null; //adikeimeno tupou SQL gia emfanish onomatos tainias kai aithousas

	private String[][] temp_results;//
 	private int m_n;//plithos tainiwn sto pinaka movie
	
	
    // End of variables declaration//
    
    /** Creates new form gt_form */
    public gt_form() {
        
		gt_frame = new JFrame();//main frame for view all reservations
		gt_panel = new JPanel();//
		gt_panel.setLayout(new GridBagLayout());
		
		//initialize and add all components
        initComponents();
        
        // Add the panel to the frame.
		gt_frame.getContentPane().add(gt_panel, BorderLayout.CENTER);
				
        
        gt_frame.setVisible(true);
        gt_frame.setLocation(500,200);//child_location to be changed!!!
        //gt_frame.setAlwaysOnTop(true);
        
        gt_frame.pack();
        gt_frame.show();
    }
    

    private void initComponents() {//GEN-BEGIN:initComponents
    
        java.awt.GridBagConstraints gridBagConstraints;


        gt_code_panel = new JPanel();
        gt_code = new JLabel();
        gt_codeFld = new JTextField(10);
        gt_movie_label = new JLabel();
        gt_show_movie_name = new JLabel();
        gt_charge_label = new JLabel();
        gt_findR = new JButton();
        gt_seat_no_label = new JLabel();
        gt_show_seat_no = new JLabel();
        gt_show_charge = new JLabel();
        gt_code_generate = new JButton();
        gt_direct_panel = new JPanel();
        gt_choose_movie = new JLabel();
        gt_moviesCombo = new JComboBox();
        gt_direct_seat_no = new JLabel();
        gt_direct_seatsFld = new JTextField(10);
        gt_direct_charge = new JLabel();
        gt_direct_show_charge = new JLabel();
        gt_direct_generate = new JButton();
        gt_actions = new JPanel();
        gt_exit = new JButton();

        getContentPane().setLayout(new GridBagLayout());

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gt_panel.setLayout(new GridBagLayout());

        gt_panel.setBorder(new javax.swing.border.TitledBorder("Generate Tickets"));
        gt_panel.setPreferredSize(new Dimension(360, 450));
        gt_code_panel.setLayout(new GridBagLayout());

        gt_code_panel.setBorder(new javax.swing.border.TitledBorder("By Code"));
        gt_code_panel.setPreferredSize(new Dimension(320, 170));
        gt_code.setText("Enter Code:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_code, gridBagConstraints);

        //gt_codeFld.setText("jTextField");
        gt_codeFld.setPreferredSize(new Dimension(100, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_codeFld, gridBagConstraints);

        gt_movie_label.setText("Movie:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_movie_label, gridBagConstraints);

        //gt_show_movie_name.setText("movie_name");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_show_movie_name, gridBagConstraints);

        gt_charge_label.setText("Charge:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_charge_label, gridBagConstraints);

        gt_findR.setText("find");

        gt_findR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (gt_codeFld.getText().equals("")){JOptionPane.showMessageDialog(gt_frame, "Pease type a code.");}
				else {
					reservation temp_reserver;
					SQL	found = new SQL();
					int temp_code =Integer.parseInt(gt_codeFld.getText());
					
					//GETTING THE reservation
					temp_reserver = found.findit(temp_code);
					
					//Checking if it's NULL
					if (temp_reserver==null){
						JOptionPane.showMessageDialog(gt_frame, "Code incorrect or not found.");
						
						//update the fields IF exist
            			gt_show_movie_name.setText("");
            			gt_show_seat_no.setText("");
            			gt_show_charge.setText("");
            		}
            		else{
            			//update the fields IF exist
            			String nose = "";
            			nose+=temp_reserver.getNo_seats();
            			String noseG = "";
            			noseG+=(temp_reserver.getNo_seats()*charge);
            			noseG+=" euros";
            			
            			gt_show_movie_name.setText(found.getMovie(temp_reserver.getP_id()));
            			gt_show_seat_no.setText(nose);
            			gt_show_charge.setText(noseG);
            			
           			}
            	}            
            }//actionPerformed
		});

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_findR, gridBagConstraints);

        gt_seat_no_label.setText("Number of seats:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_seat_no_label, gridBagConstraints);

        //gt_show_seat_no.setText("seat_no");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_show_seat_no, gridBagConstraints);

        //gt_show_charge.setText("jLabel1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_show_charge, gridBagConstraints);

        gt_code_generate.setText("Generate ");

        gt_code_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (gt_codeFld.getText().equals("")){JOptionPane.showMessageDialog(gt_frame, "Pease type a code.");}
            	else if (gt_show_movie_name.getText().equals("") && !gt_codeFld.getText().equals("")){JOptionPane.showMessageDialog(gt_frame, "Please press the find button.");}	
            	else{
            		Object[] options = {" Yes ", " No "};
                    int n = JOptionPane.showOptionDialog(gt_frame,
                                    "Generate tickets?",
                                    "A Follow-up Question",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                    if (n == JOptionPane.YES_OPTION) {

					reservation temp_reserver2;
					SQL	found2 = new SQL();
					int temp_code =Integer.parseInt(gt_codeFld.getText());
					
					//GETTING THE reservation
					temp_reserver2 = found2.findit(temp_code);
            			//CHECKING SEATS
   						//if (found2.CheckSeats(temp_reserver2)){
   						
   							//send the object to a query
   							int tmpSeats2 = temp_reserver2.getNo_seats();
   							String tmpMovie2 = found2.from_p_id_to_hall(temp_reserver2.getP_id()); 
   							
   							//GENERATE
  							found2.generate(tmpSeats2, tmpMovie2);
  						
							//DELETE OLD RESERVATION
							found2.cancel_r(temp_reserver2);//Calling the method
							
						//update the fields IF exist
            			gt_show_movie_name.setText("");
            			gt_show_seat_no.setText("");
            			gt_show_charge.setText("");
                    	//}
                    }
                    else{gt_codeFld.setText("");}	
            	}
            }//actionPerformed
		});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_code_panel.add(gt_code_generate, gridBagConstraints);

        gt_panel.add(gt_code_panel, new GridBagConstraints());

        gt_direct_panel.setLayout(new GridBagLayout());

        gt_direct_panel.setBorder(new javax.swing.border.TitledBorder("Direct"));
        gt_direct_panel.setPreferredSize(new java.awt.Dimension(320, 170));
        gt_choose_movie.setText("Choose Movie:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_direct_panel.add(gt_choose_movie, gridBagConstraints);

        gt_moviesCombo.setPreferredSize(new Dimension(100, 20));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);

        
       	//eisagwgh olwn twn movie names sto combo box movies_combo
        v_m = new SQL();//adikeimeno SQL gia klish methodwn
		temp_results = new String[m_n][4];
		
		m_n = v_m.movie_number();//klish methodou movie_number() ths r_form
		temp_results = v_m.view_m_h();

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
        gt_moviesCombo = new JComboBox(m_titles);
        gt_moviesCombo.setSelectedIndex(0);

        gt_moviesCombo.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(ActionEvent evt){
				if("comboBoxChanged".equals(evt.getActionCommand())){
					temp_movie_title=(temp_results[(gt_moviesCombo.getSelectedIndex())][0]);
					System.out.println(temp_movie_title);
				}//if
			}//actionPerformed
		});
      
        
        gt_direct_panel.add(gt_moviesCombo, gridBagConstraints);

        gt_direct_seat_no.setText("Number of seats:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_direct_panel.add(gt_direct_seat_no, gridBagConstraints);

        //gt_direct_seatsFld.setText("jTextField1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_direct_panel.add(gt_direct_seatsFld, gridBagConstraints);

        gt_direct_charge.setText("Charge:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_direct_panel.add(gt_direct_charge, gridBagConstraints);

        //gt_direct_show_charge.setText("jLabel1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_direct_panel.add(gt_direct_show_charge, gridBagConstraints);

        gt_direct_generate.setText("Generate");

        gt_direct_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				if (gt_direct_seatsFld.getText().equals("")){JOptionPane.showMessageDialog(gt_frame, "Pease type a number of seats.");}
				else{
            		Object[] options = {" Yes ", " No "};
                    int n = JOptionPane.showOptionDialog(gt_frame,
                                    "Generate tickets?",
                                    "A Follow-up Question",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                    if (n == JOptionPane.YES_OPTION) {
                    	SQL directSQL = new SQL();
                    	int tempSEATS = Integer.parseInt(gt_direct_seatsFld.getText());
                    	
                    	//CHECK FREE SEATS
                    	if (!directSQL.CheckSeats(tempSEATS, temp_movie_title)){
							JOptionPane.showMessageDialog(gt_frame, "Too many seats!");
                		}
                		else{
                     		
                    	//GENERATE TICKETS AND DO THE SEATS	
                    	directSQL.generate(tempSEATS, temp_movie_title);
                    	
                    	//MAKE THE CHARGE(tempSEATS, temp_movie_title);
                    	int temp_CHA = Integer.parseInt(gt_direct_seatsFld.getText());
                    	temp_CHA = temp_CHA*charge;
                    	String temp_CHARGE = "";
                    	temp_CHARGE+=temp_CHA;
                    	temp_CHARGE+=" euros";
                    	
                    	gt_direct_show_charge.setText(temp_CHARGE);
                    	
                    	//EMPTY THE FIELDS
                    	gt_direct_seatsFld.setText("");               		
                		}
                    }	
				}
				
            }//actionPerformed
		});   
		     
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        gt_direct_panel.add(gt_direct_generate, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gt_panel.add(gt_direct_panel, gridBagConstraints);

        gt_actions.setLayout(new GridBagLayout());

        gt_actions.setBorder(new javax.swing.border.TitledBorder("Actions"));
        gt_actions.setPreferredSize(new Dimension(320, 70));
        gt_exit.setText("Exit");
        
        gt_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	gt_frame.dispose();//closes v_frame after button is pressed
            }//actionPerformed
		});
		
        gt_actions.add(gt_exit, new GridBagConstraints());

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gt_panel.add(gt_actions, gridBagConstraints);

        getContentPane().add(gt_panel, new GridBagConstraints());

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-500)/2, 400, 500);
    }//GEN-END:initComponents
    

    
}//class
