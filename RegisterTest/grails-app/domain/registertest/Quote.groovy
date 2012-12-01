package registertest

class Quote {

	String author;
	String statement;
	
    static constraints = {
		author(blank:false)
		statement(maxSize:1000, blank:false)		
    }
}
