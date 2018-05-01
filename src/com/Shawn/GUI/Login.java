package com.Shawn.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import com.Shawn.DAO.*;

public class Login extends JFrame{
    private JButton btnConfirm, btnBack, btnLogin;
    private JTextField username;
    private JPasswordField password;
    public static final String LOGIN_TITLE = "Login";
    private Dimension LabelD = new Dimension(330,20);

    private ImageIcon QG = new ImageIcon(getClass().getResource("/images/QG.png"));



    public Login(WelcomePage WP){
        CreateView(WP);

        //make window hide application on close
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //set title
        setTitle(LOGIN_TITLE);
        //set display size
        setSize(330, 440);
        //Center the frame to the middle of the screen
        setLocationRelativeTo(null);
        //setIconImage(QGIcon);
        ImageIcon QGIcon = new ImageIcon(getClass().getResource("/images/QGIcon.png"));
        setIconImage(QGIcon.getImage());
        //disable resize
        setResizable(false);
    }
    // Init
    private void CreateView(WelcomePage WP) {
        //create a panel to contain all elements
        JPanel MainPanel = new JPanel();
        getContentPane().add(MainPanel);

        //this panel is to hold the back button &show the page message
        JPanel BackPanel = new JPanel(new BorderLayout());
        MainPanel.add(BackPanel);

        btnBack = new JButton("< back");
        btnBack.setForeground(Color.WHITE);
        btnBack.setOpaque(true);
        JLabel Welcomes = new JLabel("    Welcomes back to QG contacts" );
        Welcomes.setForeground(Color.WHITE);
        BackPanel.add(btnBack, BorderLayout.WEST);
        BackPanel.add(Welcomes, BorderLayout.CENTER);
        BackPanel.setPreferredSize(LabelD);

        JPanel PicsPanel = new JPanel();
        MainPanel.add(PicsPanel);
        JPanel FormPanel = new JPanel(new GridBagLayout());
        MainPanel.add(FormPanel);

        PicsPanel.add(new JLabel(QG));

        JPanel btnPanel = new JPanel(new BorderLayout());
        MainPanel.add(btnPanel);



        btnBack.setBackground(Color.GRAY);
        PicsPanel.setBackground(Color.GRAY);
        BackPanel.setBackground(Color.GRAY);
        MainPanel.setBackground(Color.GRAY);
        FormPanel.setBackground(Color.GRAY);


        //add input forms to the panel
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        // add the intro labels
        JLabel nameLabel =new JLabel("username: ");
        FormPanel.add(nameLabel, c);
        nameLabel.setForeground(Color.WHITE);
        c.gridy++;
        JLabel passwordLabel = new JLabel("Password: ");
        FormPanel.add(passwordLabel, c);
        passwordLabel.setForeground(Color.WHITE);
        c.gridx++;

        c.gridy=0;
        username = new JTextField(20);
        password = new JPasswordField(20);
        FormPanel.add(username, c);
        c.gridy++;
        FormPanel.add(password, c);
        username.setSize(LabelD);
        username.setBackground(Color.DARK_GRAY);
        username.setForeground(Color.WHITE);


        password.setBackground(Color.DARK_GRAY);
        password.setForeground(Color.WHITE);

        btnConfirm = new JButton("Login");
        btnConfirm.setBackground(Color.DARK_GRAY);
        btnConfirm.setOpaque(true);
        btnConfirm.setForeground(Color.MAGENTA);

        //add listener to Login button
        btnConfirm.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // connect to database ;
                        try{
                            String pass = new String(password.getPassword());
                            Connection conn = DataBase.get_connect(username.getText(),pass);
                            if(conn == null)
                                JOptionPane.showMessageDialog(null, "incorrect username or password",
                                        "Login ERROR", JOptionPane.ERROR_MESSAGE);
                            else{
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Login.this.dispose();
//                                        WP.dispose();
                                        new UIPage(conn, username.getText()).setVisible(true);
                                    }
                                });
                            }
                        } catch(Exception e1) {
                            System.err.println("Connection ERROR");
                        }
                    }
                }
        );
        btnBack.addActionListener(e -> this.dispose());

        btnPanel.setPreferredSize(LabelD);
        btnPanel.add(btnConfirm);
    }



}

