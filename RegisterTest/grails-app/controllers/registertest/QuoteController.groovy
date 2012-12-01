package registertest

class QuoteController {

	def index = { redirect(action: list) }
	
	def scaffold = true;
}
