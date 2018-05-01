package com.Shawn.GUI;

import com.Shawn.model.GetInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UIPage extends JFrame{
    private JButton btnInsert, btnDelete, btnUpdate;
    private JTextField txtid, txtName, txtAddress, txtQG_Group, txtGrade, txtPhone, txtClass, txtEmail, txtDorm;
    private JTable ResultTable;

    public UIPage(Connection conn, String usr){
        createView(conn, usr);
        //set title
        setTitle("QG Contact");
        //set display size
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(846, 360));
        //Center the frame to the middle of the screen
        setLocationRelativeTo(null);
        //setIconImage
        ImageIcon QGIcon = new ImageIcon(getClass().getResource("/images/QGIcon.png"));
        setIconImage(QGIcon.getImage());
        //enable resize
        setResizable(true);
    }

    private void createView(Connection conn, String usr) {
        JPanel MainPanel = new JPanel(new FlowLayout());
        MainPanel.setBorder(new EmptyBorder(10, 10, 10,10));
        getContentPane().add(MainPanel);

        //create the edit panel
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.weightx = 3;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.LINE_START;
        MainPanel.add(editPanel);
        editPanel.add(new JLabel("id:"), c);
        c.gridy++;
        txtid = new JTextField(25);
        editPanel.add(txtid,c);
        c.gridy++;
        editPanel.add(new JLabel("MemberName: "), c);
        c.gridy++;
        txtName = new JTextField(25);
        editPanel.add(txtName, c);
        c.gridy++;
        editPanel.add(new JLabel("QG Group: "), c);
        c.gridy++;
        txtQG_Group = new JTextField(25);
        editPanel.add(txtQG_Group,c);
        c.gridy++;
        editPanel.add(new JLabel("Grade: "), c);
        c.gridy++;
        txtGrade = new JTextField(25);
        editPanel.add(txtGrade, c);
        c.gridy++;
        editPanel.add(new JLabel("Class: "), c);
        c.gridy++;
        txtClass = new JTextField(25);
        editPanel.add(txtClass, c);
        c.gridy++;
        editPanel.add(new JLabel("Phone number: "), c);
        c.gridy++;
        txtPhone = new JTextField(25);
        editPanel.add(txtPhone, c);
        c.gridy++;
        editPanel.add(new JLabel("Email: "), c);
        c.gridy++;
        txtEmail = new JTextField(25);
        editPanel.add(txtEmail, c);
        c.gridy++;
        editPanel.add(new JLabel("Dorm: "), c);
        c.gridy++;
        txtDorm = new JTextField(25);
        editPanel.add(txtDorm, c);
        c.gridy++;
        editPanel.add(new JLabel("Family Adress: "), c);
        c.gridy++;
        txtAddress = new JTextField(25);
        editPanel.add(txtAddress, c);
        c.gridy++;
        //add buttons in edit panel
        c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        btnDelete = new JButton("Delete");
        btnInsert = new JButton("Insert");
        btnUpdate = new JButton("Update");
        editPanel.add(btnDelete,c);
        c.gridx++;
        editPanel.add(btnInsert,c);
        c.gridx++;
        editPanel.add(btnUpdate,c);

        // create JTable
        String columnsName[] ={"id",
                                "username",
                                "MemberName",
                                "QG_Group",
                                "Grade",
                                "Class",
                                "Phone_num",
                                "Email",
                                "Dorm",
                                "Family_Address"};
        //create a panel to hold table
        JPanel TablePanel = new JPanel(new BorderLayout());
        MainPanel.add(TablePanel);
        ResultTable = new JTable();
        ResultTable.setModel(new DefaultTableModel(
                new Object[][]{

                }, columnsName
        ));
        TablePanel.add(ResultTable.getTableHeader(),BorderLayout.PAGE_START);
        TablePanel.add(ResultTable, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(ResultTable);
        TablePanel.add(scrollPane);
        ResultTable.setFillsViewportHeight(true);
        TablePanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        show_Table(conn);

        btnInsert.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String query = "INSERT INTO `qg`.`qg_contact` (`id`, `username`, `MemberName`, `QG_Group`, `grade`, `class`, `phone_num`, `email`, `dorm`, `family_Address`) VALUES ('"
                                + Integer.parseInt(txtid.getText()) + "','" + usr + "','" + txtName.getText() + "','" + txtQG_Group.getText() + "','" + txtGrade.getText() + "','" + txtClass.getText() + "','" +
                                Integer.parseInt(txtPhone.getText()) + "','" + txtEmail.getText() + "','" + txtDorm.getText() + "','" + txtAddress.getText() + "');";
                        executeSQlQuery(query, "inserted", conn);
//                            conn.prepareStatement(query).executeUpdate();
                    }
                }
        );

        btnUpdate.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String query = "UPDATE `qg`.`qg_contact` SET `MemberName` = '" + txtName.getText() + "', `QG_Group` = '" + txtQG_Group.getText() + "', `grade` = '" +
                                txtGrade.getText() + "', `class` = '" + txtClass.getText() + "', `phone_num` = '" +
                                Integer.parseInt(txtPhone.getText()) + "', `email` = '" + txtEmail.getText() + "',`dorm` = '" + txtDorm.getText() + "',`family_Address` = '" +
                                txtAddress.getText() +"' WHERE (`id` = '" + Integer.parseInt(txtid.getText())+"');";
                        executeSQlQuery(query, "Updated", conn);
                    }
                }
        );
//UPDATE `qg`.`qg_contact` SET `MemberName` = 'Kanafd', `QG_Group` = 'back-endv', `grade` = 'Juniorf', `class` = 'CS4v', `phone_num` = '1234244', `email` = 'nonefd', `dorm` = 'non', `family_Address` = 'n' WHERE (`id` = '2');

        btnDelete.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String query = "DELETE FROM `QG_Contact` WHERE id = " + Integer.parseInt(txtid.getText()) + ";";
                        executeSQlQuery(query, "Deleted", conn);
                    }
                }
        );
    }

    //Display Data In JTable
    private void show_Table(Connection conn) {
        ArrayList<GetInfo> list = getUsersList(conn);
        DefaultTableModel model = (DefaultTableModel)ResultTable.getModel();
        Object[] row = new Object[10];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getUsername();
            row[2] = list.get(i).getMemberName();
            row[3] = list.get(i).getQG_Group();
            row[4] = list.get(i).getGrade();
            row[5] = list.get(i).getCLASS();
            row[6] = list.get(i).getPhone_num();
            row[7] = list.get(i).getEmail();
            row[8] = list.get(i).getDorm();
            row[9] = list.get(i).getFamily_Address();

            model.addRow(row);
        }
    }


    //This Method Populate An ArrayList Of User With Data From Mysql Database And Return This ArrayList
    public ArrayList<GetInfo> getUsersList(Connection conn)
    {
        ArrayList<GetInfo> usersList = new ArrayList<GetInfo>();

        String query = "SELECT * FROM  `QG_Contact` GROUP BY id";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            GetInfo user;
            while(rs.next())
            {
                user = new GetInfo(rs.getInt("id"),rs.getString("username"),rs.getString("MemberName"),
                        rs.getString("QG_Group"), rs.getString("Grade"), rs.getString("CLASS"),
                        rs.getInt("phone_num"),rs.getString("Email"),rs.getString("Dorm"),rs.getString("Family_Address"));
                usersList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void executeSQlQuery(String query, String message, Connection conn)
    {
        Statement st;
        try{
            st = conn.createStatement();
            if(st.executeUpdate(query) == 1)
            {
                // refresh JTable data
                DefaultTableModel model = (DefaultTableModel)ResultTable.getModel();
                model.setRowCount(0);
                show_Table(conn);

                JOptionPane.showMessageDialog(null, "Data "+message+" Successfully");
            }else{
                JOptionPane.showMessageDialog(null, "Data Not "+message);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
