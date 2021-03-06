package integration;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import otherclasses.Principal;
import otherclasses.School;
import otherclasses.Student;
import otherclasses.StudyFee;
import paymentapp.CreditCard;
import paymentapp.CreditCardType;
import paymentapp.PaymentApp.ResponseCode;

public class PaymentTest {
	public static School school = new School("Nacka","770302-7131","J","S");
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int year = 2016;
		
		Student student1 = new Student(school,"770302-7131","Jane","S");
		Student student2 = new Student(school,"800101-0001","Alice","S");
		Student student3 = new Student(school,"800102-0000","Sara","S");
		
		StudyFee fee= new StudyFee(2016, 20000f);
		Principal principal = school.getPrincipal();
		principal.applyStudyFees(fee);
		
		assertEquals("Unexpexted number of student that have not paid ",3, principal.getListOfStudentsWhoHaveNotPaidByYear(year).size());
		System.out.println("\n---------------------------------------------------------\n");
		student1.payStudyFee(year, new CreditCard("Jane S",CreditCardType.Visa,"1234123412341234",LocalDateTime.now().plusDays(1L),"123"));
		assertEquals("Unexpexted number of student that have not paid ",2, principal.getListOfStudentsWhoHaveNotPaidByYear(year).size());
		ResponseCode response = student2.payStudyFee(year, new CreditCard("Jane S",CreditCardType.Visa,"1111222233334444",LocalDateTime.now().plusDays(1L),"123"));
		assertEquals("Unexpexted number of student that have not paid ",ResponseCode.BarredCard, response);

	}

}
