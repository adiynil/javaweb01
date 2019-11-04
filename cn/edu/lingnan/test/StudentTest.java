package cn.edu.lingnan.test;

import java.util.Scanner;
import java.util.Vector;
import cn.edu.lingnan.dao.StudentDAO;
import cn.edu.lingnan.dto.Student;

public class StudentTest {

	public static void main(String[] args) {
		StudentDAO st = new StudentDAO();
		Scanner rdr = new Scanner(System.in);
		
		System.out.println("测试findAllStudent方法开始...");
		Vector<Student> v1 = st.findAllStudent();
		System.out.println("结果为：");
		for(Student i : v1)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		System.out.println("测试findStudentByNameAndPwd方法开始...");
		System.out.println("查找sname为lisi，passdord为lisi的用户...");
		System.out.println("结果为："+st.findStudentByNameAndPwd("lisi", "lisi"));
		
		System.out.println("测试insertInfoToStudent方法开始...");
		System.out.println("请输入sid（默认sname和password都为test）：");
		Student s1 = new Student();
		s1.setSid(rdr.next());
		s1.setSname("test");
		s1.setPassword("test");
		s1.setSuperuser(2);
		System.out.println("结果为："+st.insertInfoToStudent(s1));
		
		System.out.println("findAllStudent方法开始...");
		Vector<Student> v2 = st.findAllStudent();
		System.out.println("结果为：");
		for(Student i : v2)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		System.out.println("测试deleteStu方法开始...");
		System.out.println("请输入要删除的sid：");
		System.out.println("结果为："+st.deleteStu(rdr.next()));
		
		System.out.println("findAllStudent方法开始...");
		Vector<Student> v3 = st.findAllStudent();
		System.out.println("结果为：");
		for(Student i : v3)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		System.out.println("测试updateStu方法开始...");
		System.out.println("请输入要更新的sid（默认把sname和password更新为default：");
		Student s2 = new Student();
		s2.setSid(rdr.next());
		s2.setSname("default");
		s2.setPassword("default");
		System.out.println("结果为："+st.updateStu(s2));
		
		System.out.println("findAllStudent方法开始...");
		Vector<Student> v4 = st.findAllStudent();
		System.out.println("结果为：");
		for(Student i : v4)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		rdr.close();
				
	}

}
