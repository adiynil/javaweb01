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
		
		System.out.println("����findAllScore������ʼ...");
		v = cd.findAllScore();
		System.out.println("���Ϊ��");
		for(Score i : v)
			System.out.println(i.getCid()+" "+i.getSid()+" "+i.getScore());
		
		System.out.println("����addScore������ʼ...");
		System.out.println("������score��Ĭ����ӵ���s02ѡ�Ŀ�c01�ķ�������");
		Score c1 = new Score();
		c1.setScore(rdr.nextInt());
		c1.setCid("c01");
		c1.setSid("s02");
		System.out.println("���Ϊ��"+cd.addScore(c1));
		
		System.out.println("findAllScore������ʼ...");
		v = cd.findAllScore();
		System.out.println("���Ϊ��");
		for(Score i : v)
			System.out.println(i.getSid()+" "+i.getCid()+" "+i.getScore());
		
		System.out.println("����deleteScore������ʼ...");
		System.out.println("������Ҫɾ����sid��");
		String sid = rdr.next();
		System.out.println("������"+sid+"�����ſγ̳ɼ���cid)");
		String cid = rdr.next();
		System.out.println("���Ϊ��"+cd.deleteScore(cid,sid));
		
		System.out.println("findAllScore������ʼ...");
		v = cd.findAllScore();
		System.out.println("���Ϊ��");
		for(Score i : v)
			System.out.println(i.getSid()+" "+i.getCid()+" "+i.getScore());
		
		System.out.println("����updateScore������ʼ...(Ĭ�ϸ��³ɼ�Ϊ100)");
		System.out.println("������Ҫ���µ�sid��");
		sid = rdr.next();
		System.out.println("������"+sid+"�����ſγ̳ɼ���cid)");
		cid = rdr.next();
		Score c2 = new Score();
		c2.setCid(cid);
		c2.setSid(sid);
		c2.setScore(100);
		System.out.println("���Ϊ��"+cd.updateScore(c2));
		
		System.out.println("findAllScore������ʼ...");
		v = cd.findAllScore();
		System.out.println("���Ϊ��");
		for(Score i : v)
			System.out.println(i.getSid()+" "+i.getCid()+" "+i.getScore());
		
		rdr.close();
	}

}
