package com.wjh.test;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Test1 {
    public static void main(String[] args) {
        JFrame jFrame=new JFrame();
        jFrame.setSize(603,680);
        jFrame.setTitle("事件演示");
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        JButton jtb=new JButton("点我");
        jtb.setBounds(0,0,100,50);
        jtb.addActionListener(new MyActionLinstener());
        jFrame.getContentPane().add(jtb);

        jFrame.setVisible(true);

    }
}
