package com.zzw.snake;

import javax.swing.*;
import java.net.URL;

//存放素材：图片等等
public class Data {

//    //顶部的图片 URL：定位图片的地址  ImageIcon：图片
//    public static URL headreUrl = Data.class.getResource("/picture/header.png");
//    public static ImageIcon header = new ImageIcon(headreUrl);

    //头部
    public static URL upUrl = Data.class.getResource("/picture/headzzw.png");
    public static URL downUrl = Data.class.getResource("/picture/headzzw.png");
    public static URL leftUrl = Data.class.getResource("/picture/headzzw.png");
    public static URL rightUrl = Data.class.getResource("/picture/headzzw.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);

    //身体
    public static URL bodyUrl = Data.class.getResource("/picture/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);

    //食物
    public static URL foodUrl = Data.class.getResource("/picture/foodlxy.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
    public static URL foodUrl1 = Data.class.getResource("/picture/food.png");
    public static ImageIcon food1 = new ImageIcon(foodUrl1);
}
