package registertest

import org.apache.log4j.Logger;





import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Quote)
class QuoteTests {


	void testConstraints() {
		def existingQuote = new Quote( author: "Jenkins", statement: "Stop it")
		mockForConstraintsTests(Quote, [existingQuote])

		// validation should fail if both properties are null def book = new Book()
		def quote = new Quote()
		assert !quote.validate() 
		println "author error: ${quote.errors['author']}"
		println "statement error: ${quote.errors['statement']}"
		assert "nullable" == quote.errors["author"]
		assert "nullable" == quote.errors["statement"] 

		// So let's demonstrate the unique and minSize constraints
		//quote = new Quote(title: "Misery", author: "JK") 
		//assert !quote.validate() 
		//assert "unique" == quote.errors["title"].code 
		//assert "minSize" == quote.errors["author"].code

		// Validation should pass! 
		quote = new Quote(statement: "The Shining", author: "Stephen King") 
		assert quote.validate()
	
		println "Validated Quote validations"
	}
}