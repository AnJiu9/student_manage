package com.j.sm.frame;

import com.j.sm.entity.Admin;
import com.j.sm.factory.ServiceFactory;
import com.j.sm.task.CarouselThread;
import com.j.sm.utils.ResultEntity;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName AdminLoginFrame
 * @Description
 * @Author orange
 * @Date 2020-11-14 23:06
 **/

public class AdminLoginFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel loginPanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton resetBtn;
    private JPanel titlePanel;


    private JLabel bgLabel;

    public AdminLoginFrame() {
        this.setTitle("AdminLoginFrame");
        this.setContentPane(mainPanel);
        bgLabel = new JLabel();
        titlePanel.add(bgLabel);
        CarouselThread ct = new CarouselThread();
        ct.setBgLabel(bgLabel);
        new Thread(ct).start();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 1080);
        this.setLocationRelativeTo(null);
        this.setVisible(true);



        loginBtn.addActionListener(e -> {
            //获得输入的账号和密码，注意密码框组件的使用方法
            String account = accountField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            ResultEntity resultEntity = ServiceFactory.getAdminServiceInstance().adminLogin(account, password);

            JOptionPane.showMessageDialog(mainPanel, resultEntity.getMessage());
            if (resultEntity.getCode() == 0) {
                this.dispose();
//                System.out.println(((Admin)resultEntity.getData()).getAdminName());
                Admin admin = (Admin) resultEntity.getData();
                new MainFrame(admin.getAdminName());
            } else {
                accountField.setText("");
                passwordField.setText("");
            }
        });


        resetBtn.addActionListener(e -> {
            accountField.setText("");
            passwordField.setText("");
        });
    }

    public static void main(String[] args) {

        new AdminLoginFrame();
    }
}
