package cn.edu.lingnan.test;

import java.util.Scanner;
import java.util.Vector;

import cn.edu.lingnan.dao.CourseDAO;
import cn.edu.lingnan.dto.Course;

public class CourseTest {
	public static void main(String[] args) {
		CourseDAO cd = new CourseDAO();
		Scanner rdr = new Scanner(System.in);
		Vector<Course> v= null;
		
		System.out.println("����findAllCourse������ʼ...");
		v = cd.findAllCourse();
		System.out.println("���Ϊ��");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		System.out.println("����addCourse������ʼ...");
		System.out.println("������cid��Ĭ��cnameΪtest����");
		Course c1 = new Course();
		c1.setCid(rdr.next());
		c1.setCname("test");
		System.out.println("���Ϊ��"+cd.addCourse(c1));
		
		System.out.println("findAllcourse������ʼ...");
		v = cd.findAllCourse();
		System.out.println("���Ϊ��");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		System.out.println("����deleteCourse������ʼ...");
		System.out.println("������Ҫɾ����cid��");
		System.out.println("���Ϊ��"+cd.deleteCourse(rdr.next()));
		
		System.out.println("findAllcourse������ʼ...");
		v = cd.findAllCourse();
		System.out.println("���Ϊ��");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		System.out.println("����updateCourse������ʼ...");
		System.out.println("������Ҫ���µ�cid��Ĭ�ϰ�cname����Ϊdefault��");
		Course c2 = new Course();
		c2.setCid(rdr.next());
		c2.setCname("default");
		System.out.println("���Ϊ��"+cd.updateCourse(c2));
		
		System.out.println("findAllcourse������ʼ...");
		v = cd.findAllCourse();
		System.out.println("���Ϊ��");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		rdr.close();
	}

}
