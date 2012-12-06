import grails.util.Environment;

import java.util.Date;

class BootStrap {

	def init = { servletContext ->

		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				createExamples()
				break;
			case Environment.PRODUCTION:
				println "No special configuration required"
				break;
		}
	}
	def destroy = {
	}

	def createExamples() {
		new com.jankester.TestExecution(host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()).save();
		new com.jankester.TestExecution(host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()).save();
		new com.jankester.TestExecution(host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()).save();
		new com.jankester.TestExecution(host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()).save();		
		new registertest.Quote(author:'hopkins',statement:'again').save();
	}
}
