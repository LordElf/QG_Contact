package com.Shawn.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame{
    private ImageIcon QG = new ImageIcon(getClass().getResource("/images/QG.png"));
    private JButton Loginbtn, Registerbtn;
    // create an object to deliver this WelcomePage to other windows, which can operate this window in other objects
    private Login Login = new Login(this);
    private Register Register = new Register();

    public WelcomePage(){
        CreateView();

        //make window hide application on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set title
        setTitle("QG contact");
        //set display size
        setSize(846, 360);
        //Center the frame to the middle of the screen
        setLocationRelativeTo(null);
        //setIconImage(QGIcon);
        ImageIcon QGIcon = new ImageIcon(getClass().getResource("/images/QGIcon.png"));
        setIconImage(QGIcon.getImage());
        //enable resize
        setResizable(true);
    }

    private void CreateView() {
        JPanel MainPanel = new JPanel();
        getContentPane().add(MainPanel);

        JPanel picsPanel = new JPanel();
        MainPanel.add(picsPanel);
        picsPanel.add(new JLabel(QG));

        JPanel WelComePanel = new JPanel(new GridBagLayout());
        WelComePanel.setPreferredSize(new Dimension(300,150));
        MainPanel.add(WelComePanel);
        GridBagConstraints c = new GridBagConstraints();

        JLabel sign1 = new JLabel("Welcome to QG Contacts");
        sign1.setForeground(Color.WHITE);
        JLabel sign2 = new JLabel("Login or Register to get started");
        sign2.setForeground(Color.LIGHT_GRAY);
        sign2.setFont(new Font(sign2.getFont().getName(),sign2.getFont().getStyle(),10));
        sign1.setFont(new Font(sign2.getFont().getName(),sign2.getFont().getStyle(),20));
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridy = 0;
        WelComePanel.add(sign1,c);
        c.gridy++;
        WelComePanel.add(sign2,c);

        WelComePanel.setBackground(Color.DARK_GRAY);
        MainPanel.setBackground(Color.DARK_GRAY);
        picsPanel.setBackground(Color.DARK_GRAY);

        Loginbtn = new JButton("  Login  ");
        Registerbtn = new JButton("Register");
        c.gridx = 0;
        c.gridy++;
        c.weighty = 2;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = 1;
        WelComePanel.add(Registerbtn,c);
        c.gridx++;
        WelComePanel.add(Loginbtn,c);

        Loginbtn.setBackground(Color.DARK_GRAY);
        Loginbtn.setForeground(Color.WHITE);
        Registerbtn.setBackground(Color.DARK_GRAY);
        Registerbtn.setForeground(Color.WHITE);

        //add button listener
        Loginbtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                 Login.setVisible(true);
                            }
                        });
                    }
                }
        );

        Registerbtn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                Register.setVisible(true);
                            }
                        });
                    }
                }
        );
    }
}
