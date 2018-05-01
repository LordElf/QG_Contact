package com.Shawn.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import com.Shawn.DAO.*;
import com.mysql.cj.protocol.Resultset;

public class Register extends JFrame{
    private JButton btnLogin, btnBack;
    private JTextField username;
    private JPasswordField password;
    public static final String LOGIN_TITLE = "Register";
    private Dimension LabelD = new Dimension(330,20);

    //set Icon

    public Register(){
        CreateView();

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
    private void CreateView() {
        JPanel MainPanel = new JPanel();
        getContentPane().add(MainPanel);

        JPanel backPanel = new JPanel(new BorderLayout());
        MainPanel.add(backPanel);

        GridBagConstraints c = new GridBagConstraints();
        GridBagLayout gridbag = new GridBagLayout();

        btnBack = new JButton("< back");
        btnBack.setForeground(Color.WHITE);
        btnBack.setOpaque(true);
        JLabel Welcomes = new JLabel("                 Register" );
        Welcomes.setForeground(Color.WHITE);
        backPanel.setPreferredSize(LabelD);
        backPanel.add(btnBack, BorderLayout.WEST);
        backPanel.add(Welcomes, BorderLayout.CENTER);

        JPanel FormPanel = new JPanel(new GridBagLayout());
        MainPanel.add(FormPanel);
        FormPanel.setPreferredSize(new Dimension(300,250));

        btnBack.setBackground(Color.GRAY);
        backPanel.setBackground(Color.GRAY);
        MainPanel.setBackground(Color.GRAY);
        FormPanel.setBackground(Color.GRAY);


        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 300;
        c.anchor = GridBagConstraints.LINE_START;
        username = new JTextField(20);
        password = new JPasswordField(20);
        c.insets = new Insets(10,0,0,0);
        // add the intro labels
        JLabel nameLabel =new JLabel("pick a username: ");
        gridbag.setConstraints(nameLabel,c);
        FormPanel.add(nameLabel, c);
        nameLabel.setForeground(Color.LIGHT_GRAY);
        c.gridy++;
        gridbag.setConstraints(username,c);
        FormPanel.add(username, c);
        c.gridy++;
        JLabel passwordLabel = new JLabel("Enter Password: ");
        gridbag.setConstraints(passwordLabel,c);
        FormPanel.add(passwordLabel, c);
        passwordLabel.setForeground(Color.lightGray);
        c.gridy++;

        gridbag.setConstraints(password,c);
        FormPanel.add(password, c);
        username.setSize(LabelD);
        username.setBackground(Color.DARK_GRAY);
        username.setForeground(Color.WHITE);

        password.setBackground(Color.DARK_GRAY);
        password.setForeground(Color.WHITE);

        c.gridy++;
        c.ipady = 40;
        FormPanel.add(new JLabel("Register to get QG contact! "), c);

        btnLogin = new JButton("Register");
        btnLogin.setBackground(Color.DARK_GRAY);
        btnLogin.setOpaque(true);
        btnLogin.setForeground(Color.MAGENTA);

        //add listener to Login button
        JPanel btnPanel = new JPanel(new BorderLayout());
        MainPanel.add(btnPanel);
        btnPanel.add(btnLogin);
        btnPanel.setPreferredSize(LabelD);

        btnBack.addActionListener(e -> this.dispose());
        btnLogin.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String pass = new String(password.getPassword());
                            String user = new String(username.getText());
                            Statement stmt = null;
                            String query = "CREATE USER '"+ user +"'@'localhost' IDENTIFIED BY '"
                                    + pass +"';";
                            String query2 = "GRANT ALL PRIVILEGES ON QG.* TO '"+ user +"'@'localhost';";
                            String query3 = "FLUSH PRIVILEGES;";
                            try{
                                // *cannot use ==
                                if(pass.equals("") || user.equals("") ) {
                                    JOptionPane.showMessageDialog(null, "username or password cannot be empty",
                                            "sign up ERROR", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                //stmt = conn.createStatement();
                                //ResultSet rs = stmt.executeQuery(query);
                                Connection conn = DataBase.get_connect("root", "123456");
                                PreparedStatement post = conn.prepareStatement(query),
                                        post2 = conn.prepareStatement(query2),
                                        post3 = conn.prepareStatement(query3);
                                post.executeUpdate();
                                post2.executeUpdate();
                                post3.executeUpdate();
                                System.out.println("SUCCESSFULLY CREATED");
                                JOptionPane.showMessageDialog(null, "SUCCEEDED",
                                        "SUCCEEDED", JOptionPane.INFORMATION_MESSAGE);
                                conn.close();
                            }catch(SQLException e1){
                                System.out.println("SQLException: " + e1.getMessage());
                                System.out.println("SQLState: " + e1.getSQLState());
                                System.out.println("VendorError: " + e1.getErrorCode());

                            }catch(Exception e1){
                                JOptionPane.showMessageDialog(null, "some errors occurred in MySQL",
                                        "sign up ERROR", JOptionPane.ERROR_MESSAGE);
                            } finally {
                                if (stmt != null) { stmt.close(); }

                            }

                        } catch(Exception e1){
                            System.out.println(e1);
                            return;
                        }
                    }
                }
        );
    }



}

