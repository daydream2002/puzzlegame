package com.wjh.ui;

import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener{
    ArrayList<User> allusers=new ArrayList<>();
    JButton login = new JButton("登录");

    JButton register = new JButton("注册");
    JTextField usernamet = new JTextField();
    JTextField passwordt = new JPasswordField();
    JTextField codet = new JTextField();
    JLabel rightCode = new JLabel(getCode());
    public LoginJFrame() {
        initJFrame();
        initView();
        initData();
        this.setVisible(true);
    }

    private void initData() {
        getUser();
    }

    private void getUser(){
    List<String> list = FileUtil.readUtf8Lines("D:\\desktop\\java\\puzzlegame\\src\\com\\wjh\\ui\\userinfo.txt");
    for (String s : list) {
        String[] split = s.split("&");
        String username = split[0].split("=")[1];
        String password = split[1].split("=")[1];
        allusers.add(new User(username,password));
    }
}
    private void initView() {
        JLabel username = new JLabel("用户名");
        JLabel password = new JLabel("密码");
        JLabel code = new JLabel("验证码");
        username.setBounds(130, 100, 50, 30);
        password.setBounds(130, 150, 50, 30);
        login.addMouseListener(this);
        register.addMouseListener(this);
        code.setBounds(130, 200, 50, 30);
        usernamet.setBounds(200, 100, 100, 30);
        passwordt.setBounds(200, 150, 100, 30);
        codet.setBounds(200, 200, 100, 30);
        rightCode.setBounds(320, 200, 50, 30);
        this.add(rightCode);
        login.setBounds(100,300,100,30);
        register.setBounds(260,300,100,30);
        this.add(login);
        this.add(register);
        this.add(username);
        this.add(password);
        this.add(code);
        this.add(passwordt);
        this.add(codet);
        this.add(usernamet);


    }

    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图 登录");
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    private String getCode(){
        char[] chars = new char[26 * 2 + 10];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            chars[i]=(char) ('a'+i);
        }
        for (int i = 26; i < 26+26; i++) {
            chars[i]=(char) ('A'+i-26);
        }
        for (int i = 52; i < 10+52; i++) {
            chars[i]=(char) ('0'+i-52);
        }
        Random rd = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(chars[rd.nextInt(26*2+10)]);
        }
        return sb.toString();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source==login){
            if (!rightCode.getText().equals(codet.getText())) {
                System.out.println("验证码错误");

                showJDalog("验证码错误");
            } else {
                User user = new User(usernamet.getText(), passwordt.getText());
                System.out.println(user);
                System.out.println(allusers);
                if (allusers.contains(user)) {
                    System.out.println("登录成功");
                    this.setVisible(false);
                    showJDalog("欢迎"+usernamet.getText());
                    new GameJFrame();
                } else {
                    showJDalog("用户名或密码错误");
                }
            }
        }else if (source==register){
            System.out.println("注册");
            this.setVisible(false);
            new RegisterJFrame(allusers);

        }
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
