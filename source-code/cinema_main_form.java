//MAIN
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class cinema_main_form extends javax.swing.JFrame {
		
	// Variables declaration - do not modify//
    private JFrame main_frame = null;
    
    private JButton r_new = null;
    private JButton r_cancel = null;
    private JButton r_view = null;
    private JButton g_tickets = null;
    private JButton m_exit = null;
    
    private JPanel main_panel = null;
    private JPanel re_panel = null;
    private JPanel ma_panel = null;
    
    // End of variables declaration//

    /** Creates new form cinema_main_form */
    public cinema_main_form() {
 		
 		main_frame = new JFrame();//main frame
		main_panel = new JPanel();//kedriko panel
		
		// Add the panel to the frame.
		main_frame.getContentPane().add(main_panel, BorderLayout.CENTER);
    	
        initComponents();
	
		// Exit when the window is closed.
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        re_panel = new javax.swing.JPanel();//reservation panel
        ma_panel = new javax.swing.JPanel();//main action panel

        //re_panel buttons
        r_new = new javax.swing.JButton();//new reservation button
        r_cancel = new javax.swing.JButton();//cancel reservation button
        r_view = new javax.swing.JButton();//view reservations button

        //ma_panel buttons
 		g_tickets = new javax.swing.JButton();//generate tickets button
        m_exit = new javax.swing.JButton();//main exit button


        getContentPane().setLayout(new java.awt.GridBagLayout());

        //setTitle("main");
        //setAlwaysOnTop(true);//set main frame to be always on top
        main_panel.setBorder(new javax.swing.border.TitledBorder("Main"));
        main_panel.setPreferredSize(new java.awt.Dimension(610, 405));
        re_panel.setLayout(new java.awt.GridBagLayout());

        re_panel.setBorder(new javax.swing.border.TitledBorder("Reservation"));
        re_panel.setPreferredSize(new java.awt.Dimension(590, 250));
        r_new.setText("New Reservation");
        r_new.setPreferredSize(new java.awt.Dimension(180, 40));

        //ACTION NEW RESERVation
        r_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){

            	r_form f = new r_form();
            }
		});
        //END ACTION

        //ACTION cancel reservation
        r_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){

            	c_form c = new c_form();
            }
		});
        //END cancel reservation

        //ACTION view all reservations
        r_view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){

            	v_form v = new v_form();
            }
		});
        //END view all reservations

        //ACTION generate tickets
        g_tickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e){

            	gt_form gt = new gt_form();
            }
		});
        //END generate tickets


        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
       	re_panel.add(r_new, gridBagConstraints);

        r_cancel.setText("Cancel Reservation");
        r_cancel.setPreferredSize(new java.awt.Dimension(180, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        re_panel.add(r_cancel, gridBagConstraints);

        r_view.setText("Display Reservations");
        r_view.setPreferredSize(new java.awt.Dimension(180, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        re_panel.add(r_view, gridBagConstraints);

        main_panel.add(re_panel);

        ma_panel.setLayout(new java.awt.GridBagLayout());

        ma_panel.setBorder(new javax.swing.border.TitledBorder("Actions"));
        //ma_panel.setInheritsPopupMenu(true);
        ma_panel.setPreferredSize(new java.awt.Dimension(590, 120));
        g_tickets.setText("Generate Tickets");
        g_tickets.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ma_panel.add(g_tickets, gridBagConstraints);

        m_exit.setText("EXIT");
        m_exit.setPreferredSize(new java.awt.Dimension(150, 30));
        m_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        ma_panel.add(m_exit, gridBagConstraints);

        main_panel.add(ma_panel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(main_panel, gridBagConstraints);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-640)/2, (screenSize.height-480)/2, 640, 480);
    }//GEN-END:initComponents



    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed



	public static void main(String[] args)  {
		
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cinema_main_form().setVisible(true);
            }
        });
    }



}
