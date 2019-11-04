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
		
		System.out.println("测试findAllCourse方法开始...");
		v = cd.findAllCourse();
		System.out.println("结果为：");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		System.out.println("测试addCourse方法开始...");
		System.out.println("请输入cid（默认cname为test）：");
		Course c1 = new Course();
		c1.setCid(rdr.next());
		c1.setCname("test");
		System.out.println("结果为："+cd.addCourse(c1));
		
		System.out.println("findAllcourse方法开始...");
		v = cd.findAllCourse();
		System.out.println("结果为：");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		System.out.println("测试deleteCourse方法开始...");
		System.out.println("请输入要删除的cid：");
		System.out.println("结果为："+cd.deleteCourse(rdr.next()));
		
		System.out.println("findAllcourse方法开始...");
		v = cd.findAllCourse();
		System.out.println("结果为：");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		System.out.println("测试updateCourse方法开始...");
		System.out.println("请输入要更新的cid（默认把cname更新为default：");
		Course c2 = new Course();
		c2.setCid(rdr.next());
		c2.setCname("default");
		System.out.println("结果为："+cd.updateCourse(c2));
		
		System.out.println("findAllcourse方法开始...");
		v = cd.findAllCourse();
		System.out.println("结果为：");
		for(Course i : v)
			System.out.println(i.getCid()+" "+i.getCname());
		
		rdr.close();
	}

}
