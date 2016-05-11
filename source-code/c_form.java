import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class c_form extends javax.swing.JFrame{
    
	
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JFrame c_frame = null;
	
    private JPanel c_actions = null;
    private JPanel c_option = null;
    private JPanel c_panel = null;
	private JPanel c_fields = null;
    
    private JButton c_cancel_it = null;
    private JButton c_find = null;
    private JButton c_exit = null;
    
    private JTextField c_codeFld = null;

    private JLabel c_code = null;
	private JLabel c_f_name = null;
    private JLabel c_l_name = null;
    private JLabel c_movie_title = null;
    private JLabel c_no_seats = null;
	private JLabel c_phone = null;
    private JLabel c_show_f_name = null;
    private JLabel c_show_l_name = null;
    private JLabel c_show_movie_title = null;
    private JLabel c_show_no_seats = null;
    private JLabel c_show_phone = null;
    
	private reservation return_r;

    // End of variables declaration//GEN-END:variables
	
	
	
    public c_form() {
        
		
		c_frame = new JFrame();//main frame for reservation
		c_panel = new JPanel();//new reservation panel
		c_panel.setLayout(new GridBagLayout());
		
		//initialize and add all components
        initComponents();
        
        // Add the panel to the frame.
		c_frame.getContentPane().add(c_panel, BorderLayout.CENTER);
				
        c_frame.setVisible(true);
        c_frame.setLocation(500,200);//child_location to be changed!!!
        //c_frame.setAlwaysOnTop(true);
        
        c_frame.pack();
        c_frame.show();
    }
    

    public void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;


        c_option = new JPanel();
        c_code = new JLabel();
        c_codeFld = new JTextField(10);
        c_find = new JButton();
        c_fields = new JPanel();
        c_f_name = new JLabel();
        c_l_name = new JLabel();
        c_phone = new JLabel();
        c_movie_title = new JLabel();
        c_no_seats = new JLabel();
        c_show_f_name = new JLabel();
        c_show_l_name = new JLabel();
        c_show_phone = new JLabel();
        c_show_movie_title = new JLabel();
        c_show_no_seats = new JLabel();
        c_actions = new JPanel();
        c_cancel_it = new JButton();
        c_exit = new JButton();

        getContentPane().setLayout(new GridBagLayout());

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        c_panel.setLayout(new GridBagLayout());

        c_panel.setBorder(new javax.swing.border.TitledBorder("Cancel Reservation"));
        c_panel.setPreferredSize(new Dimension(360, 450));
        c_option.setLayout(new GridBagLayout());

        c_option.setBorder(new javax.swing.border.TitledBorder("Find Reservation"));
        c_option.setPreferredSize(new Dimension(320, 150));
        c_code.setText("Enter Code:");
        c_code.setPreferredSize(new Dimension(70, 15));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_option.add(c_code, gridBagConstraints);

        //c_codeFld.setText("jTextField1");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_option.add(c_codeFld, gridBagConstraints);

        c_find.setText("Find");
        c_find.setPreferredSize(new Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_option.add(c_find, gridBagConstraints);
        
         c_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){
				if (c_codeFld.getText().equals("")){JOptionPane.showMessageDialog(c_frame, "Type a code,Please.");}
				else{
            		SQL found = new SQL();
            		String temp_movie;
            		
            		//Checking in class sql
            		int temp = Integer.parseInt(c_codeFld.getText());
            		return_r = found.findit(temp);
					if (return_r==null){
						JOptionPane.showMessageDialog(c_frame, "Code incorrect or not found.");
						//update the fields IF exist
            			c_show_f_name.setText("");
            			c_show_l_name.setText("");
            			c_show_phone.setText("");
            			c_show_movie_title.setText("");
            			c_show_no_seats.setText("");
					}
					else{
            			//CASTING
            			String temp_phone = "";
            			temp_phone+=return_r.getPhone();
            			String temp_seats= "";
            			temp_seats+=return_r.getNo_seats();
            	
            	
            			//update the fields IF exist
            			c_show_f_name.setText(return_r.getF_name());
            			c_show_l_name.setText(return_r.getL_name());
            			c_show_phone.setText(temp_phone);
            			c_show_movie_title.setText(found.from_p_id_to_hall(return_r.getP_id()));
            			c_show_no_seats.setText(temp_seats);
					}
				}
            	
            }
		});

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        c_panel.add(c_option, gridBagConstraints);

        c_fields.setLayout(new GridBagLayout());

        c_fields.setBorder(new javax.swing.border.TitledBorder("Cancel Reservation Fields"));
        c_fields.setPreferredSize(new Dimension(320, 150));
        c_f_name.setText("First Name:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_f_name, gridBagConstraints);

        c_l_name.setText("Last Name:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_l_name, gridBagConstraints);

        c_phone.setText("Phone Number:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_phone, gridBagConstraints);

        c_movie_title.setText("Movie Title:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_movie_title, gridBagConstraints);

        c_no_seats.setText("Number of seats:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_no_seats, gridBagConstraints);

        //c_show_f_name.setText("jLabel7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_show_f_name, gridBagConstraints);

        //c_show_l_name.setText("jLabel8");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_show_l_name, gridBagConstraints);

        //c_show_phone.setText("jLabel9");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_show_phone, gridBagConstraints);

        //c_show_movie_title.setText("jLabel10");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_show_movie_title, gridBagConstraints);

        //c_show_no_seats.setText("jLabel11");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        c_fields.add(c_show_no_seats, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        c_panel.add(c_fields, gridBagConstraints);

        c_actions.setBorder(new javax.swing.border.TitledBorder("Actions"));
        c_actions.setPreferredSize(new Dimension(320, 70));
        c_cancel_it.setText(" Cancel It");
        c_actions.add(c_cancel_it);
        
        c_cancel_it.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

				if(c_show_f_name.getText().equals("")){JOptionPane.showMessageDialog(c_frame, "Pease type a code");}
				else{
					
					Object[] options = {" Yes ", " No "};
                    int n = JOptionPane.showOptionDialog(c_frame,
                                    "Do you want to \n cancel this reservation?",
                                    "A Follow-up Question",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                    if (n == JOptionPane.YES_OPTION) {
                        SQL delete = new SQL();//SQL Obj
						delete.cancel_r(return_r);//Calling the method
		
						c_frame.dispose();//job done!!!, quitting	
                    }
                }
                    //update the fields IF exist
            		c_show_f_name.setText("");
            		c_show_l_name.setText("");
            		c_show_phone.setText("");
            		c_show_movie_title.setText("");
            		c_show_no_seats.setText("");
				//
            }//actionPerformed
		});

        c_exit.setText("Exit");
        
        c_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	c_frame.dispose();//closes r_frame after button is pressed
            }//actionPerformed
		});
        
        c_actions.add(c_exit);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        c_panel.add(c_actions, gridBagConstraints);

        getContentPane().add(c_panel, new GridBagConstraints());

        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-400)/2, (screenSize.height-500)/2, 400, 500);
    }//GEN-END:initComponents
    


    

    
}//class
