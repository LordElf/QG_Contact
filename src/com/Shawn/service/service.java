package com.Shawn.service;

import com.Shawn.GUI.*;

import javax.swing.*;


public class service {

    public static void main(String[] args){
        try{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new WelcomePage().setVisible(true);
                }
            });
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
