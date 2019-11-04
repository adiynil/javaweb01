package cn.edu.lingnan.test;

import java.util.Scanner;
import java.util.Vector;
import cn.edu.lingnan.dao.StudentDAO;
import cn.edu.lingnan.dto.Student;

public class StudentTest {

	public static void main(String[] args) {
		StudentDAO st = new StudentDAO();
		Scanner rdr = new Scanner(System.in);
		
		System.out.println("����findAllStudent������ʼ...");
		Vector<Student> v1 = st.findAllStudent();
		System.out.println("���Ϊ��");
		for(Student i : v1)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		System.out.println("����findStudentByNameAndPwd������ʼ...");
		System.out.println("����snameΪlisi��passdordΪlisi���û�...");
		System.out.println("���Ϊ��"+st.findStudentByNameAndPwd("lisi", "lisi"));
		
		System.out.println("����insertInfoToStudent������ʼ...");
		System.out.println("������sid��Ĭ��sname��password��Ϊtest����");
		Student s1 = new Student();
		s1.setSid(rdr.next());
		s1.setSname("test");
		s1.setPassword("test");
		s1.setSuperuser(2);
		System.out.println("���Ϊ��"+st.insertInfoToStudent(s1));
		
		System.out.println("findAllStudent������ʼ...");
		Vector<Student> v2 = st.findAllStudent();
		System.out.println("���Ϊ��");
		for(Student i : v2)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		System.out.println("����deleteStu������ʼ...");
		System.out.println("������Ҫɾ����sid��");
		System.out.println("���Ϊ��"+st.deleteStu(rdr.next()));
		
		System.out.println("findAllStudent������ʼ...");
		Vector<Student> v3 = st.findAllStudent();
		System.out.println("���Ϊ��");
		for(Student i : v3)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		System.out.println("����updateStu������ʼ...");
		System.out.println("������Ҫ���µ�sid��Ĭ�ϰ�sname��password����Ϊdefault��");
		Student s2 = new Student();
		s2.setSid(rdr.next());
		s2.setSname("default");
		s2.setPassword("default");
		System.out.println("���Ϊ��"+st.updateStu(s2));
		
		System.out.println("findAllStudent������ʼ...");
		Vector<Student> v4 = st.findAllStudent();
		System.out.println("���Ϊ��");
		for(Student i : v4)
		System.out.println(i.getSid()+" "+i.getSname()+" "+i.getPassword()
			+" "+i.getSuperuser());
		
		rdr.close();
				
	}

}
