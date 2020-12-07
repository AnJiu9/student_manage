package com.j.sm.component;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName ImgFrame
 * @Description 自定义背景窗体
 * @Author orange
 * @Date 2020-12-08 00:58
 **/

public class ImgFrame extends JFrame {

    public ImgFrame() {
        setTitle("自定义背景窗体");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("/Users/mqxu/Desktop/Documents/student-manager/src/main/resources/img/student.jpg");
        JLabel label = new JLabel();
        label.setIcon(imageIcon);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(label, new Integer(10));

        this.setLayeredPane(layeredPane);
        JPanel jPanel = new JPanel();
        jPanel.setOpaque(true);
        JLabel infoLabel = new JLabel("学生信息");
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 16);
        infoLabel.setBounds(200, 50, 200, 100);
        infoLabel.setForeground(new Color(63, 98, 131));
        infoLabel.setFont(titleFont);
        jPanel.add(label);
        this.setContentPane(jPanel);
    }

    public static void main(String[] args) {
        new ImgFrame();
    }
}
