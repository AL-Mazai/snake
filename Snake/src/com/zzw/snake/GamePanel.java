package com.zzw.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int time = 150;//初始延迟时间
    int length;//蛇长度
    int[] snakeX = new int[600];//蛇X坐标
    int[] snakeY = new int[500];//蛇Y坐标
    String direction;//R:右，L：左，U：上，D：下
    boolean isStart = false;//游戏是否开始
    Timer timer = new Timer(time,this);//定时器:蛇的速度
    int foodX ;//食物横坐标
    int foodY ;//食物纵坐标
    Random random = new Random();//用来随机生成食物坐标
    boolean isFail = false;//死亡判断：默认不失败
    int score;//积分
    int foodNum = 1;

    //构造器
    public GamePanel(){
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();//让时间动起来
    }

    /**
     * 初始化蛇的状态
     */
    public void init(){
        length = 3;//初始长度
        snakeX[0] = 100;snakeY[0] = 100;//头部坐标
        snakeX[1] = 75;snakeY[1] = 100;//第一个身体坐标
        snakeX[2] = 50;snakeY[2] = 100;//第二个身体坐标
        direction = "R";
        //随机初始化食物坐标
        foodX  = 25 + 25 * random.nextInt(34);
        foodY  = 75 + 25 * random.nextInt(24);
        score = 0;
    }

    /**
     * 画板：画界面，画蛇
     * Graphics：画笔
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        this.setBackground(Color.WHITE);//设置背景颜色
        //Data.header.paintIcon(this,g,25,11);//绘制头部的广告栏
        g.fillRect(25,25,850,650);//绘制游戏区域
        //画头部
        if(direction.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if(direction.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        //画头部
        for (int i = 1; i < length ; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);//蛇的身体长度通过length来控制
        }
        //画食物
        Data.food.paintIcon(this,g,foodX ,foodY );
        //Data.food1.paintIcon(this,g,foodX ,foodY );
        //画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("宋体",Font.BOLD,30));
        g.drawString("长度" + length,750,65);
        g.drawString("分数" + score,750,100);
        //提示游戏是否开始
        if(isStart == false){
            g.setColor(Color.WHITE);//设置画笔颜色
            g.setFont(new Font("宋体",Font.BOLD,40));//设置字体
            g.drawString("按下空格开始游戏",300,300);
        }
        //失败提醒
        if(isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("宋体", Font.BOLD, 34));
            g.drawString("游戏失败，按下空格重新开始开始游戏", 200, 300);
        }
    }

    /**
     * 接收键盘的输入：监听:定义小蛇走向
     * 键盘按下，未释放
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //获取键盘按下的键盘是哪个键
        int keyCode = e.getKeyCode();
        //如果按下的是空格键
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail){//失败，游戏再来一遍
                isFail = false;
                init();//重新初始化游戏
            }else{
                isStart = !isStart;
            }
            repaint();//刷新界面
        }
        //键盘控制走向
        if(keyCode == KeyEvent.VK_RIGHT){
            direction = "R";
        }else if(keyCode == KeyEvent.VK_LEFT){
            direction = "L";
        }else if(keyCode == KeyEvent.VK_DOWN){
            direction = "D";
        }else if(keyCode == KeyEvent.VK_UP){
            direction = "U";
        }
    }

    /**
     * 通过此方法控制小蛇的移动
     * 定时器：监听时间
     * 帧：执行定时操作
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且游戏没有结束
        if(isStart && isFail == false){
            //身体移动
            for (int i = length - 1; i > 0; i--) {//除了脑袋，身体移动
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //通过控制方向让头部移动
            if(direction.equals("R")){
                snakeX[0] += 25;//头部移动
                //边界判断
                if(snakeX[0] > 850){
                    snakeX[0] = 25;
                }
            }else if(direction.equals("L")){
                snakeX[0] -= 25;//头部移动
                //边界判断
                if(snakeX[0] < 25){
                    snakeX[0] = 850;
                }
            }else if(direction.equals("D")){
                snakeY[0] += 25;//头部移动
                //边界判断
                if(snakeY[0] > 650){
                    snakeY[0] = 75;
                }
            }else if(direction.equals("U")){
                snakeY[0] -= 25;//头部移动
                //边界判断
                if(snakeY[0] < 75){
                    snakeY[0] = 650;
                }
            }
            //如果小蛇的头部和食物坐标重合
            for (int i = 0; i < length; i++) {
                if(snakeX[0] == foodX && snakeY[0] == foodY ){
                    //长度增加1
                    length++;
                    //增加分数
                    score += 10;
                    //重新生成食物
                    foodX = 25 + 25 * random.nextInt(34);
                    foodY = 75 + 25 * random.nextInt(24);
                }
            }

            //结束判断
//            for (int i = 2; i < length; i++) {
//                if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
//                    isFail = true;
//                }
//            }
            //刷新界面
            repaint();
        }
        timer.start();//让时间动起来
    }

    //以下方法用不到
    /**
     * 键盘按下，弹起：敲击
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 释放某个键
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
