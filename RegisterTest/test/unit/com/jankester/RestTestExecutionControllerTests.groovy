package com.jankester



import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RestTestExecutionController)
@Mock([TestExecutionService])
@TestMixin(DomainClassUnitTestMixin)
class RestTestExecutionControllerTests {
	
	
	void testListAll() {
		response.format = "json";
		request.method ="GET";

		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
			
		def control = mockFor(TestExecutionService)
		control.demand.findAll(1..1) { -> TestExecution.list() }
		controller.testExecutionService = control.createMock()
		controller.list()
		
		println "Retrieving json response from controller: ${response.json}"
  
		assert response.json.size() == 4,'Expecting to see 4 TestExecution Objects'

		println "Tested list all of controller."
	}
	
	void testShow() {
		response.format = "json";
		request.method ="GET";
		params.id = 1
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);			
		def control = mockFor(TestExecutionService)
		control.demand.findById(1..1) {def id -> TestExecution.get(id) }
		controller.testExecutionService = control.createMock()
		controller.show()
		
		println "Retrieving json response from controller: ${response.json}"		
		assert response.json.id == 1,'Expecting to see json object with id=1'	  
		println "Tested show of rest controller."
	}
	
	void testShowNonExisting() {
		response.format = "json";
		request.method ="GET";
		params.id = 2
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		def control = mockFor(TestExecutionService)
		control.demand.findById(1..1) {def id -> TestExecution.get(id) }
		controller.testExecutionService = control.createMock()
		controller.show()
		
		println "Retrieving json response from controller: ${response.json}"
		println "Response status=${response.status}"
		assert response.status == 404,'Expecting to get 404 for id not found'
		println "Tested show non existing id of rest controller."
	}
	
	void testDelete() {
		response.format = "json";
		request.method ="DELETE";
		params.id = 1
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 1,'Initial list has 1 item.'
		def control = mockFor(TestExecutionService)
		control.demand.removeById(1..1) {def id -> TestExecution.get(id).delete(); return id;}
		controller.testExecutionService = control.createMock()
		controller.delete()
		assert TestExecution.list().size() == 0,'After delete, list has 0 items.'
		
		println "Response status=${response.status}"		
		assert response.status == 204,'Expecting to see 204 after succesfull delete'
		println "Tested delete of rest controller."
	}
	
	void testDeleteNonExisting() {
		response.format = "json";
		request.method ="DELETE";
		params.id = 2

		def control = mockFor(TestExecutionService)
		control.demand.removeById(1..1) {def id -> return null;}
		controller.testExecutionService = control.createMock()
		controller.delete()
		
		println "Response status=${response.status}"
		assert response.status == 404,'Expecting to see 404 after deleting non existing entry'
		println "Tested delete for non existing id of rest controller."
	}
}
