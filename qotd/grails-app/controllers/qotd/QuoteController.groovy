package qotd

class QuoteController {

	def index = {  redirect(action: home)  }

	def home = {  render "<h1>Real Programmers do not eat Quiche</h1>"  }

	def scaffold = true

	def quoteService

	def random = {
		def randomQuote = quoteService.getRandomQuote()
		[ quote : randomQuote ]
	}

	def ajaxRandom = {
		def randomQuote = quoteService.getRandomQuote()
		render "<q>${randomQuote.content}</q>" +
				"<p>${randomQuote.author}</p>"
	}
}
