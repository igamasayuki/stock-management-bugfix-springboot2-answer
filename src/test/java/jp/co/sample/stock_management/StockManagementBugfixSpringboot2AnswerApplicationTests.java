package jp.co.sample.stock_management;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.sample.stock_management.repository.BookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockManagementBugfixSpringboot2AnswerApplicationTests {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void testFindAll() {
		
		bookRepository.findAll().forEach(System.out::println);
		
	}

}
