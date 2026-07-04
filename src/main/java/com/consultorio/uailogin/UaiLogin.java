/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.consultorio.uailogin;

import VistaVentas.frmLogin;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class UaiLogin {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Usando la buena práctica con SwingUtilities
        SwingUtilities.invokeLater(() -> {
            // Suponiendo que tienes una clase llamada frmLogin
            frmLogin login = new frmLogin();
            login.setVisible(true);
        });

    }
}
