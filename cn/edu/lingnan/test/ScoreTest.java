package cn.edu.lingnan.test;

import java.util.Scanner;
import java.util.Vector;

import cn.edu.lingnan.dao.ScoreDAO;
import cn.edu.lingnan.dto.Score;

public class ScoreTest {
	public static void main(String[] args) {
		ScoreDAO cd = new ScoreDAO();
		Scanner rdr = new Scanner(System.in);
		Vector<Score> v= null;
		
		System.out.println("测试findAllScore方法开始...");
		v = cd.findAllScore();
		System.out.println("结果为：");
		for(Score i : v)
			System.out.println(i.getCid()+" "+i.getSid()+" "+i.getScore());
		
		System.out.println("测试addScore方法开始...");
		System.out.println("请输入score（默认添加的是s02选的课c01的分数）：");
		Score c1 = new Score();
		c1.setScore(rdr.nextInt());
		c1.setCid("c01");
		c1.setSid("s02");
		System.out.println("结果为："+cd.addScore(c1));
		
		System.out.println("findAllScore方法开始...");
		v = cd.findAllScore();
		System.out.println("结果为：");
		for(Score i : v)
			System.out.println(i.getSid()+" "+i.getCid()+" "+i.getScore());
		
		System.out.println("测试deleteScore方法开始...");
		System.out.println("请输入要删除的sid：");
		String sid = rdr.next();
		System.out.println("请输入"+sid+"的哪门课程成绩（cid)");
		String cid = rdr.next();
		System.out.println("结果为："+cd.deleteScore(cid,sid));
		
		System.out.println("findAllScore方法开始...");
		v = cd.findAllScore();
		System.out.println("结果为：");
		for(Score i : v)
			System.out.println(i.getSid()+" "+i.getCid()+" "+i.getScore());
		
		System.out.println("测试updateScore方法开始...(默认更新成绩为100)");
		System.out.println("请输入要更新的sid：");
		sid = rdr.next();
		System.out.println("请输入"+sid+"的哪门课程成绩（cid)");
		cid = rdr.next();
		Score c2 = new Score();
		c2.setCid(cid);
		c2.setSid(sid);
		c2.setScore(100);
		System.out.println("结果为："+cd.updateScore(c2));
		
		System.out.println("findAllScore方法开始...");
		v = cd.findAllScore();
		System.out.println("结果为：");
		for(Score i : v)
			System.out.println(i.getSid()+" "+i.getCid()+" "+i.getScore());
		
		rdr.close();
	}

}
