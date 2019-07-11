package com.nihaojewelry.quartz.learn;

/**
 * <p>
 *程序入口
 * </p>
 *
 * @author aodeng-低调小熊猫
 * @since 19-7-11
 */
public class LearnMain {
    public static void main(String[] args) {
        //创建一个老王
        Person laoWang=new LaoWang();
        //老王买了一件夹克
        laoWang=new Jacket(laoWang);
        //老王买了一个帽子
        laoWang=new Hat(laoWang);
        laoWang.show();
/*
        控制台输出：
        老王我现在消费0.0
        老王我现在又消费了一件夹克100，累计用了100.0
        老王我现在又消费了一件帽子200，累计用了300.0*/
    }
}
