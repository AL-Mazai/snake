package com.zzw.snake;

import javax.swing.*;

public class StartGames {
    public static void main(String[] args) {
        //1.绘制静态窗口JFrame
        JFrame frame = new JFrame("zzw的java贪吃蛇");//取名
        frame.setBounds(10,10,900,720);//设置界面的大小
        frame.setResizable(false);//窗口大小不可以改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口关闭事件，游戏可以关闭
        //2.面板 JPanel 可以加入到JFrame
        frame.add(new GamePanel());

        frame.setVisible(true);//让窗口能够展现出来
    }
}
