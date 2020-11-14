package com.j.sm.frame;

import javax.swing.*;

/**
 * @ClassName MainFrame
 * @Description
 * @Author orange
 * @Date 2020-11-14 23:44
 **/

public class MainFrame extends JFrame {
    private JPanel mainPanel;

    public MainFrame() {
        this.setTitle("MainFrame");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
