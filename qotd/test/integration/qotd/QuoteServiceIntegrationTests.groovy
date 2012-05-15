package qotd

import static org.junit.Assert.*
import org.junit.*

class QuoteServiceIntegrationTests {

	def quoteService
	void testStaticQuote() {
		def staticQuote = quoteService.getStaticQuote()
		assertEquals("Anonymous", staticQuote.author)
		assertEquals("Real Programmers Don't eat quiche", staticQuote.content)
	}
}
