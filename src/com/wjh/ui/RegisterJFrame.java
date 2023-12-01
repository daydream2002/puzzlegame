package com.wjh.ui;

import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class RegisterJFrame extends JFrame implements MouseListener {
    ArrayList<User> allusers;
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField repassword = new JTextField();
    JButton submit = new JButton("提交");
    JButton reset = new JButton("重置");

    public RegisterJFrame(ArrayList<User> arrayList) {
        this.allusers = arrayList;
        initFrame();
        initView();
        this.setVisible(true);

    }

    private void initView() {
        JLabel name = new JLabel("用户名");
        JLabel psd = new JLabel("密码");
        JLabel repsd = new JLabel("再次输入密码");

        name.setBounds(120, 100, 50, 30);
        psd.setBounds(120, 150, 50, 30);
        repsd.setBounds(120, 200, 100, 30);

        submit.addMouseListener(this);
        reset.addMouseListener(this);

        username.setBounds(200, 100, 100, 30);
        password.setBounds(200, 150, 100, 30);
        repassword.setBounds(200, 200, 100, 30);

        submit.setBounds(100, 300, 100, 30);
        reset.setBounds(260, 300, 100, 30);
        this.add(submit);
        this.add(reset);
        this.add(username);
        this.add(password);
        this.add(repassword);
        this.add(psd);
        this.add(repsd);
        this.add(name);

    }

    private void initFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图 注册");
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    private void showJDalog(String str) {
        JDialog jDialog = new JDialog(new JFrame(),"",true);
        JLabel label = new JLabel(str);
        jDialog.setSize(300,150);
        jDialog.setLocationRelativeTo(null);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        jDialog.add(label);
        jDialog.setAlwaysOnTop(true);
        jDialog.setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source==submit){
            if (username.getText().isEmpty() || password.getText().isEmpty() || password.getText().isEmpty()){
                showJDalog("用户名或密码不能为空");
            }else if (password.getText().equals(reset.getText())){
                showJDalog("两次密码不一致");
            }else if (containsname(username.getName())){
                showJDalog("用户名已存在");
            }else {
                User user = new User(username.getText(), password.getText());
                allusers.add(user);
                FileUtil.writeLines(allusers,"D:\\desktop\\java\\puzzlegame\\src\\com\\wjh\\ui\\userinfo.txt", StandardCharsets.UTF_8);
                showJDalog("注册成功");
                this.setVisible(false);
                new LoginJFrame();
            }

        }else if (source==reset){
            username.setText("");
            password.setText("");
            repassword.setText("");
        }

    }
private boolean containsname(String name){
    for (User user : allusers) {
        if (Objects.equals(user.username, name))
            return true;
    }
    return false;

}
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
