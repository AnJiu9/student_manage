package com.j.sm.frame;

import com.j.sm.component.CustomPanel;
import com.j.sm.entity.Admin;
import com.j.sm.entity.Clazz;
import com.j.sm.entity.Department;
import com.j.sm.factory.ServiceFactory;
import com.j.sm.task.TimeThread;
import com.j.sm.utils.AliOSSUtil;
import com.j.sm.utils.FormatUtil;
import com.j.sm.vo.StudentVo;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.ws.FaultAction;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MainFrame
 * @Description
 * @Author orange
 * @Date 2020-11-16 00:06
 **/



public class MainFrame extends JFrame {
    private final String adminName;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JButton 院系管理Button;
    private JButton 班级管理Button;
    private JButton 学生管理Button;
    private JButton 奖惩管理Button;
    private JPanel departmentPanel;
    private JPanel classPanel;
    private JPanel studentPanel;
    private JPanel rewardPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel addDepPanel;
    private JTextField depNameField;
    private JButton 选择图片button;
    private JLabel logoLabel;
    private JPanel toolBarPanel;
    private JButton 新增院系button;
    private JButton 切换显示button;
    private JPanel contentPanel;
    private JButton 新增button;
    private JComboBox<Department> depComboBox;
    private JTextField classNameField;
    private JButton 新增班级Button;
    private JPanel treePanel;
    private JPanel classContentPanel;
    private JComboBox<Department> departmentJBox;
    private JComboBox<Clazz> clazzJBox;
    private JTextField searchField;
    private JButton 搜索Button;
    private JButton 新增学生Button;
    private JButton 批量导入Button;
    private JPanel tablePanel;
    private JLabel timeLabel;
    private JButton 重置Button;

    /**
     * 自定义变量
     */
    private final CardLayout c;
    private String uploadFileUrl;
    private File file;
    private List<StudentVo> students;
    /**
     * 选择的院系id
     */
    private int departmentId = 0;

    private JLabel idLabel;
    private JLabel avatar;
    private JTextField depName;
    private JTextField className;
    private JTextField stuName;
    private JLabel gender;
    private JLabel birthday;
    private JTextField phone;
    private JTextField address;

    public MainFrame(String adminName) {
        //时间线程
        TimeThread timeThread = new TimeThread();
        timeThread.setTimeLabel(timeLabel);
        timeThread.start();

        this.adminName = adminName;
        init();

//        studentVo = StudentVo.builder()
//                .departmentName("院系")
//                .className("班级")
//                .studentName("姓名")
//                .avatar("https://student-management-img.oss-cn-hangzhou.aliyuncs.com/logo/20201123180501.JPG")
//                .gender((short) 2)
//                .birthday(new Date())
//                .phone("18922209810")
//                .address("江苏省南京市")
//                .build();

        c = new CardLayout();
        centerPanel.setLayout(c);
        centerPanel.add("1", departmentPanel);
        centerPanel.add("2", classPanel);
        centerPanel.add("3", studentPanel);
        centerPanel.add("4", rewardPanel);


        院系管理Button.addActionListener(e -> {
            c.show(centerPanel, "1");
            showDepartments();
        });

        班级管理Button.addActionListener(e -> {
            c.show(centerPanel, "2");
            showClazz();
        });

        学生管理Button.addActionListener(e -> {
            c.show(centerPanel, "3");
            //学生数据填充表格
            showStudents(ServiceFactory.getStudentServiceInstance().getAll());

            //初始化院系下拉框数据，第一条数据为显示提示信息
            departmentJBox.addItem(Department.builder().departmentName("请选择院系").build());
            List<Department> departments = ServiceFactory.getDepartmentServiceInstance().selectAll();
            for (Department department : departments) {
                departmentJBox.addItem(department);
            }

            //两个下拉框初始化提示数据，因为里面元素都是对象，所以这样进行了处理
            Department tip1 = new Department();
            tip1.setDepartmentName("请选择院系");
            departmentJBox.addItem(tip1);
            Clazz tip2 = new Clazz();
            tip2.setClassName("请选择班级");
            clazzJBox.addItem(tip2);

            //初始化院系下拉框数据
            List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
            for (Department department : departmentList) {
                departmentJBox.addItem(department);
            }

            //初始化班级下拉框数据
            List<Clazz> clazzes = ServiceFactory.getClazzServiceInstance().getAll();
            for (Clazz clazz : clazzes) {
                clazzJBox.addItem(clazz);
            }

            //院系下拉框监听，选中哪项，表格中显示该院系所有学生，并级联查出该院系的所有班级，更新到班级下拉框
            departmentJBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        int index = departmentJBox.getSelectedIndex();
                        //排除第一项，那是提示信息，不能作为查询依据
                        if (index != 0) {
                            departmentId = departmentJBox.getItemAt(index).getId();
                            //获取该院系的学生并显示在表格中
                            List<StudentVo> studentList = ServiceFactory.getStudentServiceInstance().getByDepId(departmentId);
                            showStudents(studentList);
                            //二级联动—班级下拉框的内容随着选择院系的不同而变化
                            List<Clazz> clazzes = ServiceFactory.getClazzServiceInstance().getClazzByDepId(departmentId);
                            //一定要先移除之前的数据，否则下拉框内容会叠加
                            clazzJBox.removeAllItems();
                            Clazz tip = new Clazz();
                            tip.setClassName("请选择班级");
                            clazzJBox.addItem(tip);
                            for (Clazz clazz : clazzes) {
                                clazzJBox.addItem(clazz);
                            }
                        }
                    }
                }
            });

            //班级下拉框监听，可以根据选中的班级显示所有学生
            clazzJBox.addItemListener(e1 -> {
                if (e1.getStateChange() == ItemEvent.SELECTED) {
                    int index = clazzJBox.getSelectedIndex();
                    if (index != 0) {
                        int classId = clazzJBox.getItemAt(index).getId();
                        List<StudentVo> studentList = ServiceFactory.getStudentServiceInstance().getByClassId(classId);
                        showStudents(studentList);
                    }
                }
            });

            //右侧个人信息面板
            CustomPanel stuInfoPanel = new CustomPanel("/Users/orange/Documents/school/java/stuInfo_bg.png");
            stuInfoPanel.setPreferredSize(new Dimension(300, getHeight()));
            stuInfoPanel.setLayout(null);
            stuInfoPanel.repaint();
            studentPanel.add(stuInfoPanel, BorderLayout.EAST);

            //学号
            JLabel title = new JLabel("学生信息");
            title.setFont(new Font("微软雅黑", Font.BOLD, 18));
            title.setForeground(new Color(57, 59, 60));
            title.setBounds(110,50,100,50);

            //头像
            JLabel avatar = new JLabel("<html><img src='https://student-management-img.oss-cn-hangzhou.aliyuncs.com/logo/20201123180501.JPG 'width=50 height=50/></html>");
            avatar.setBounds(120,110,50,50);

            //院系
            JTextField depName = new JTextField("未选择");
            depName.setBounds(50,170,200,40);

            //班级
            JTextField className = new JTextField("未选择");
            className.setBounds(50,220,200,40);

            //姓名
            JTextField stuName = new JTextField("未选择");
            stuName.setBounds(50,270,200,40);

            //性别
            JLabel gender = new JLabel("未选择");
            gender.setBounds(50,320,200,40);

            //生日
            JLabel birthday = new JLabel("未选择");
            birthday.setBounds(50,370,200,40);

            //电话
            JTextField phone = new JTextField("未选择");
            phone.setBounds(500,420,200,40);

            //地址
            JTextField address = new JTextField("未选择");
            address.setBounds(50,470,200,40);

            //编辑按钮
             JButton editBtn = new JButton("编辑");
            editBtn.setBounds(60, 520, 90, 40);
            //删除按钮
            JButton delBtn = new JButton("删除");
            delBtn.setBounds(160, 520, 90, 40);

            delBtn.addActionListener(ee -> {
                ServiceFactory.getStudentServiceInstance().deleteById(idLabel.getText());
                JOptionPane.showMessageDialog(null, "删除成功");
                showStudents(ServiceFactory.getStudentServiceInstance().getAll());
                reset();
            });

            stuInfoPanel.add(title);
            stuInfoPanel.add(avatar);
            stuInfoPanel.add(depName);
            stuInfoPanel.add(className);
            stuInfoPanel.add(stuName);
            stuInfoPanel.add(gender);
            stuInfoPanel.add(birthday);
            stuInfoPanel.add(phone);
            stuInfoPanel.add(address);
            stuInfoPanel.add(editBtn);
            stuInfoPanel.add(delBtn);


        });

        搜索Button.addActionListener(e -> {
            students = ServiceFactory.getStudentServiceInstance().getByKeywords(searchField.getText().trim());
            showStudents(students);
            searchField.setText("");
        });

        奖惩管理Button.addActionListener(e -> {
            c.show(centerPanel, "4");
        });
        showDepartments();

        //左侧边栏切换效果
        新增院系button.addActionListener(e -> {
            addDepPanel.setBorder(BorderFactory.createTitledBorder("新增院系"));
            //获取左侧面板的可见性
            boolean visible = addDepPanel.isVisible();
            //不可见
            if (!visible) {
                //向右侧展开，背景色变化、可见
                leftPanel.setPreferredSize(new Dimension(180, this.getHeight() - 100));
                addDepPanel.setBackground(new Color(215, 232, 245));
                addDepPanel.setVisible(true);
            } else {
                //向左侧收起，背景色还原、不可见
                leftPanel.setPreferredSize(new Dimension(60, this.getHeight() - 100));
//                addDepPanel.setBackground(new Color(114, 140, 153));
                addDepPanel.setVisible(false);
            }

            leftPanel.revalidate();
        });

        depNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                depNameField.setText("");
            }
        });

        选择图片button.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            //默认打开路径
            fileChooser.setCurrentDirectory(new File("/Users/orange/Documents/school/java/sm-logo"));
            //对话框显示的范围在centerPanel内
            int result = fileChooser.showOpenDialog(centerPanel);
            if (result == JFileChooser.APPROVE_OPTION) {
                //选中文件
                file = fileChooser.getSelectedFile();
                String name = file.getAbsolutePath();
                //创建icon对象
                URL url;
                ImageIcon icon = new ImageIcon(name);
                logoLabel.setPreferredSize(new Dimension(150, 150));
                //图片强制缩放成和JLabel一样大小
                icon = new ImageIcon(icon.getImage().getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(), Image.SCALE_DEFAULT));
                logoLabel.setIcon(icon);
            }
        });

        新增button.addActionListener(e -> {
            //上传文件到OSS并返回url
            uploadFileUrl = AliOSSUtil.ossUpload(file);
            //创建Department对象，并设置相应属性
            Department department = new Department();
            department.setDepartmentName(depNameField.getText().trim());
            department.setLogo(uploadFileUrl);
            //调用service实现新增功能
            int n = ServiceFactory.getDepartmentServiceInstance().addDepartment(department);
            if (n == 1) {
                JOptionPane.showMessageDialog(centerPanel, "新增院系成功");
                //新增成功后，将侧边栏隐藏
                leftPanel.setPreferredSize(new Dimension(60, this.getHeight()));
                addDepPanel.setVisible(false);
                //刷新界面数据
                showDepartments();
                //清空文本框
                depNameField.setText("");
                logoLabel.setIcon(null);
            } else {
                JOptionPane.showMessageDialog(centerPanel, "新增院系失败");
            }
        });

        //获得下拉框中的院系id
        depComboBox.addActionListener(e -> {
            //得到选中项的索引
            int index = depComboBox.getSelectedIndex();
            //按照索引取出项，就是一个department对象，然后取出其id
            departmentId = depComboBox.getItemAt(index).getId();
        });

        //新增班级
        新增班级Button.addActionListener(e -> {
            Clazz clazz = new Clazz();
            clazz.setDepartmentId(departmentId);
            clazz.setClassName(classNameField.getText().trim());
            int n = ServiceFactory.getClazzServiceInstance().addClazz(clazz);
            if (n == 1) {
                JOptionPane.showMessageDialog(centerPanel, "新增班级成功");
                classNameField.setText("");
                showClazz();
            } else {
                JOptionPane.showMessageDialog(centerPanel, "新增班级失败");
            }
        });

        重置Button.addActionListener(e -> {
            reset();
        });


        新增学生Button.addActionListener(e -> {
//            new AddStudentFrame(MainFrame.this);
//            MainFrame.this.setEnabled(true);
        });
    }

    private void reset() {
        //还原表格数据
        List<StudentVo> studentList = ServiceFactory.getStudentServiceInstance().getAll();
        showStudents(studentList);
        //院系下拉框还原
        departmentJBox.setSelectedIndex(0);
        //班级下拉框还原
        clazzJBox.setSelectedIndex(0);
        Clazz tip2 = new Clazz();
        clazzJBox.addItem(tip2);
        List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getAll();
        for (Clazz clazz : clazzList) {
            clazzJBox.addItem(clazz);
        }
        //右侧个人信息显示区域数据还原
        avatar.setText("<html><img src='https://bpic.588ku.com/element_origin_min_pic/19/04/09/e3330d623cad123abc8545573a86cc38.jpg' width=50 height=50/></html>");
        idLabel.setText("未选择");
        depName.setText("未选择");
        className.setText("未选择");
        stuName.setText("未选择");
        gender.setText("未选择");
        birthday.setText("未选择");
        address.setText("");
        phone.setText("");
        searchField.setText("");
    }



    public void init() {
        setTitle("管理员：" + this.adminName);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1110, 900);
        setExtendedState(MAXIMIZED_BOTH);
//        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * 显示所有院系信息
     */
    private void showDepartments() {
        //移除原有数据
        contentPanel.removeAll();
        //从service层获取到所有院系列表
        List<Department> departmentList = ServiceFactory.getDepartmentServiceInstance().selectAll();
        //获取总数
        int len = departmentList.size();
        //根据院系总数算出行数(每行4个)
        int row = len % 4 == 0 ? len / 4 : len / 4 + 1;
        //创建一个网格布局，每行4列，指定水平和垂直间距
        GridLayout gridLayout = new GridLayout(row, 4, 15, 15);
        contentPanel.setLayout(gridLayout);
        for (Department department : departmentList) {
            //给每个院系对象创建一个面板
            JPanel depPanel = new JPanel();
            //设置合适大小
            depPanel.setPreferredSize(new Dimension(220, 290));
            depPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
            //将院系名称设置给面板标题
//            depPanel.setBorder(BorderFactory.createTitledBorder(department.getDepartmentName()));
            JLabel nameLabel = new JLabel(department.getDepartmentName());
            //新建一个JLabel标签，用来放置院系logo，并指定大小
            JLabel logoLabel = new JLabel("<html><img src='" + department.getLogo() + "' width='319',height='210'/></html>");
            //占位空白标签
            JLabel blankLabel = new JLabel();
            blankLabel.setPreferredSize(new Dimension(200, 30));
            //删除按钮
            JButton delBtn = new JButton("删除");
            //院系名称加入院系面板
            depPanel.add(nameLabel);
            //图标标签加入院系面板
            depPanel.add(logoLabel);
            //按钮加入院系面板
            depPanel.add(delBtn);
            //院系面板加入主体内容面板
            contentPanel.add(depPanel);
            //刷新主体内容面板
            contentPanel.revalidate();

            //删除
            delBtn.addActionListener(e -> {
                String nameLabelText = nameLabel.getText();
                ServiceFactory.getDepartmentServiceInstance().remove(nameLabelText);
                showDepartments();
            });
        }
    }

    public void showClazz() {
        List<Department> departments = ServiceFactory.getDepartmentServiceInstance().selectAll();
        showCombobox(departments);
        showTree(departments);
        showClazz(departments);
    }

    private void showCombobox(List<Department> departments) {
        for (Department department : departments) {
            depComboBox.addItem(department);
        }
    }

    private void showTree(List<Department> departments) {
        treePanel.removeAll();
        //左侧树组件到根结点
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("南京工业职业技术大学");
        for (Department department : departments) {
            //院系名称作为一级叶子结点
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(department.getDepartmentName());
            //加入根结点，构成一棵树
            root.add(group);
            List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(department.getId());
            for (Clazz clazz : clazzList) {
                //班级结点加入对应到院系结点
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(clazz.getClassName());
                group.add(node);
            }
        }

        //以root为根生成树
        final JTree tree = new JTree(root);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        treePanel.add(tree, BorderLayout.CENTER);
        treePanel.revalidate();
    }

    private void showClazz(List<Department> departments) {
        classContentPanel.removeAll();
        classContentPanel.setLayout(new GridLayout(0, 5, 15, 15));
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 16);
        for (Department department : departments) {
            JPanel depPanel = new JPanel();
            depPanel.setPreferredSize(new Dimension(120, 150));
            depPanel.setBackground(new Color(63, 98, 131));
            depPanel.setLayout(new BorderLayout());
            JLabel depNameLabel = new JLabel(department.getDepartmentName());
            depNameLabel.setFont(titleFont);
            depNameLabel.setForeground(new Color(255, 255, 255));
            depPanel.add(depNameLabel, BorderLayout.NORTH);
            List<Clazz> clazzList = ServiceFactory.getClazzServiceInstance().getClazzByDepId(department.getId());
            DefaultListModel<Clazz> listModel = new DefaultListModel<>();
            for (Clazz clazz : clazzList) {
                listModel.addElement(clazz);
            }
            JList<Clazz> jList = new JList<>(listModel);
            jList.setBackground(new Color(167, 186, 199));
            JScrollPane scrollPane = new JScrollPane(jList);
            depPanel.add(scrollPane, BorderLayout.CENTER);
            classContentPanel.add(depPanel);

            //对每个JList增加弹出菜单
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem modifyItem = new JMenuItem("修改");
            JMenuItem deleteItem = new JMenuItem("删除");
            jPopupMenu.add(modifyItem);
            jPopupMenu.add(deleteItem);
            jList.add(jPopupMenu);

            jList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //选中项对下标
                    int index = jList.getSelectedIndex();
                    //点击鼠标右键
                    if (e.getButton() == 3) {
                        //在鼠标位置弹出菜单
                        jPopupMenu.show(jList, e.getX(), e.getY());
                        //取出选中项的班级对象数据
                        Clazz clazz = jList.getModel().getElementAt(index);
                        //对"删除"菜单项添加事件监听
                        deleteItem.addActionListener(e1 -> {
                            int choice = JOptionPane.showConfirmDialog(depPanel, "确定删除吗？");
                            //点击"确定"
                            if (choice == 0) {
                                //根据当前班级的id删除
                                int n = ServiceFactory.getClazzServiceInstance().deleteClazz(clazz.getId());
                                if (n == 1) {
                                    //从List数据模型中移除当前项，先从视觉上看到删除效果
                                    listModel.remove(index);
                                    //走后端重新调用下数据
                                    showTree(ServiceFactory.getDepartmentServiceInstance().selectAll());
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    private void showStudents(List<StudentVo> students) {
//        CustomPanel stuInfoPanel = new CustomPanel("/Users/orange/Documents/school/java/stuInfo_bg.JPG");
//        stuInfoPanel.setPreferredSize(new Dimension(300,getHeight()));
//        JLabel title = new JLabel("学  生  信  息");
//        title.setFont(new Font("微软雅黑",Font.BOLD,16));
//        title.setForeground(new Color(253, 255, 249));
//        stuInfoPanel.add(title);
//        stuInfoPanel.repaint();
//        studentPanel.add(stuInfoPanel,BorderLayout.EAST);

//        //获得学生列表数据
//        List<StudentVo> students = ServiceFactory.getStudentServiceInstance().getAll();

        //移除表格
        tablePanel.removeAll();
        //创建表格对象
        JTable table = new JTable();
        //创建表格数据类型，并设置给表格
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        //设置表头内容
        model.setColumnIdentifiers(new String[]{"学号", "院系", "班级", "姓名", "性别", "地址", "手机号", "出生日期", "头像"});
        //遍历list，生成Object数组，数组中的每个元素就是一行记录
        for (StudentVo student : students) {
            Object[] object = new Object[]{
                    student.getId(), student.getDepartmentName(), student.getClassName(), student.getStudentName(), FormatUtil.formatGender(student.getGender()), student.getAddress(), student.getPhone(), student.getBirthday(), student.getAvatar()
            };
            //添加到数据模型
            model.addRow(object);
        }
        //设置最后一列不显示在表格中（头像）
        TableColumn tc = table.getColumnModel().getColumn(8);
        tc.setMaxWidth(0);
        tc.setMinWidth(0);
        //获得表格的表头
        JTableHeader header = table.getTableHeader();
        //表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        //设置表头字体
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("楷体", Font.PLAIN, 16));
        //设置表格行高
        table.setRowHeight(35);
        //表格背景色
        table.setBackground(new Color(223, 241, 234));
        //表格内容居中
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        //表格加入滚动面板，并设置水平和垂直方向均可按需滚动
        Component table1;
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tablePanel.add(scrollPane);
        tablePanel.revalidate();

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            StudentVo studentVo = StudentVo.builder()
                    .className(table.getValueAt(row,2).toString())
                    .studentName(table.getValueAt(row,3).toString())
                    .phone(table.getValueAt(row,6).toString())
                    .address(table.getValueAt(row,5).toString())
                    .build();
            System.out.println(studentVo);
        });


    }


    public static void main(String[] args) {
        new MainFrame("一只橘子");

    }
}
