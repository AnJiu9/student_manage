package com.j.sm.frame;

import com.eltima.components.ui.DatePicker;
import com.j.sm.component.ImgPanel;
import com.j.sm.entity.Clazz;
import com.j.sm.entity.Student;
import com.j.sm.factory.ServiceFactory;
import com.j.sm.utils.AliOSSUtil;
import com.j.sm.utils.FormatUtil;
import com.j.sm.vo.StudentVo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName AddStudentFrame
 * @Description
 * @Author orange
 * @Date 2020-12-09 22:04
 **/

public class AddStudentFrame extends JFrame{
    private ImgPanel rootPanel;
    private JTextField idField;
    private JTextField nameField;
    private JComboBox<Clazz> classComboBox;
    private JRadioButton 男radioButton;
    private JRadioButton 女radioButton;
    private JTextField addressField;
    private JTextField phoneField;
    private JButton 新增button;
    private JLabel closeLabel;
    private JPanel datePanel;
    private JLabel avatarLabel;
    private JPanel panel1;
    private String uploadFileUrl;
    private Integer classId;
    private File file;
    private final MainFrame mainFrame;
    private Image image;


    public AddStudentFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
//        image = new ImageIcon("/Users/orange/Pictures/sbz.jpeg").getImage();
//        JPanel jPanel = new ImgPanel(image);
//        jPanel.setBounds(0 , 0, 400,400 );
//        this.setTitle("新增学生界面");
//        this.getContentPane().add(jPanel);
        setContentPane(rootPanel);
        rootPanel.setFileName("sbz.jpeg");
        rootPanel.repaint();
        setUndecorated(true);
        setTitle("新增学生界面");
        setSize(400,600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //班级选择
        Clazz tip = new Clazz();
        tip.setClassName("请选择班级");
        classComboBox.addItem(tip);
        List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getAll();
        for (Clazz clazz : clazzList) {
            classComboBox.addItem(clazz);
        }

        //关闭窗体
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddStudentFrame.this.dispose();
            }
        });

        //头像按钮
        avatarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("/Users/orange/Documents/school/java/stu_avatar"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                    icon.setImage(icon.getImage().getScaledInstance(150, 150, 150));
                    avatarLabel.setIcon(icon);
                }
            }
        });


        //班级复选框
        classComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int index = classComboBox.getSelectedIndex();
                if (index != 0) {
                    classId = classComboBox.getItemAt(index).getId();
                }
            }
        });

        //单选按钮
        ButtonGroup group = new ButtonGroup();
        group.add(男radioButton);
        group.add(女radioButton);

        //出生日期
        DatePicker datePicker = getDatePicker();
        datePanel.add(datePicker);
        datePanel.revalidate();


        新增button.addActionListener(e -> {
            String gender = null;
            if (男radioButton.isSelected()) {
                gender = "男";
            }
            if (女radioButton.isSelected()) {
                gender = "女";
            }
            Student student = new Student();
            student.setId(idField.getText());
            student.setClassId(classId);
            student.setStudentName(nameField.getText());
            student.setAvatar(AliOSSUtil.ossUpload(file));
            student.setGender(FormatUtil.formatGender(gender));
            student.setBirthday((Date) datePicker.getValue());
            student.setAddress(addressField.getText());
            student.setPhone(phoneField.getText());
            int n = ServiceFactory.getStudentServiceInstance().insertStudent(student);
            if (n == 1) {
                JOptionPane.showMessageDialog(rootPanel, "新增成功");
                AddStudentFrame.this.dispose();
                List<StudentVo> studentList = ServiceFactory.getStudentServiceInstance().getAll();
                mainFrame.showStudents(studentList);
            }
        });
        新增button.setBackground(new Color(134, 219, 71));
        新增button.setForeground(new Color(0, 0, 0));
    }


    private static DatePicker getDatePicker() {
        final DatePicker datePicker;
        // 格式
        String defaultFormat = "yyyy-MM-dd";
        // 当前时间
        Date date = new Date();
        // 字体
        Font font = new Font("Times New Roman", Font.PLAIN, 18);
        Dimension dimension = new Dimension(200, 30);
        int[] hilightDays = {1, 3, 5, 7};
        int[] disabledDays = {4, 6, 5, 9};
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datePicker = new DatePicker(date, defaultFormat, font, dimension);
        //设置起始位置
        // datePicker.setLocation(137, 83);
        //也可用setBounds()直接设置大小与位置
        //datePicker.setBounds(137, 83, 177, 24);
        // 设置一个月份中需要高亮显示的日子
        datePicker.setHightlightdays(hilightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datePicker.setDisableddays(disabledDays);
        // 设置国家
        datePicker.setLocale(Locale.CHINA);
        // 设置时钟面板可见
        // datePicker.setTimePanleVisible(true);
        return datePicker;
    }

}
