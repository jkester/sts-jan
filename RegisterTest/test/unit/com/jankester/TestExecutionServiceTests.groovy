package com.jankester



import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin;
import grails.validation.ValidationException;

import org.junit.*


/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
class TestExecutionServiceTests {

	def testExecutionService;

	@Before
	void setup() {
		testExecutionService = new TestExecutionService();	
	}
		
    void testFindByVersion() {
		Date start = new Date();   
		mockDomain(TestExecution,[
				[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
				[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
				[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
				[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
			]);
		Date end = new Date();  
		assert TestExecution.list().size() == 4;
		def r11RC1List = testExecutionService.findForVersion('2011.R11.RC1');
		println "Found ${r11RC1List.size()} elements"
		assert r11RC1List.size() == 3,'Should find 3 elements for version 2011.R11.RC1'
		
		def dateList = testExecutionService.findBetweenExecDates(start,end);
		assert dateList.size() == 4,'All four domain should be between dates'
		
		println "Tested service methods"
    }
	
	void testFindAll() {
		Date start = new Date();
		mockDomain(TestExecution,[
				[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
				[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
				[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
				[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
			]);
		Date end = new Date();
		assert TestExecution.list().size() == 4;
		def allList = testExecutionService.findAll();
		println "Found ${allList.size()} elements"
		assert allList.size() == 4,'Should find 4 elements'
		
		println "Tested all."
	}
	
	void testFindById() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 4;
		def obj1 = testExecutionService.findById(1);
		println "Found ${obj1} ${obj1.testScript}"
		assert obj1.id == 1,'Should find object with id 1'
		
		println "Tested by id."

	}
	
	void testFindByIdNotExisting() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 1;
		def obj1 = testExecutionService.findById(2);
		println "Result of find by non existing id: $obj1"
		assert obj1 == null,'Should not find object with id 2'
		
		println "Tested by non existing id."
	}
	
	void testRemoveById() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 4,'Initial list should be 4 big.';
		def obj1 = testExecutionService.removeById(1);
		println "Deleted ${obj1} "
		assert obj1 == 1,'Should get id of deleted object'
		assert TestExecution.list().size() == 3,'After delete, list should only have 3 items.';
		
		println "Tested remove by id."
	}
	
	void testRemoveByNonExistingId() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 4,'Initial list should be 4 big.';
		def obj1 = testExecutionService.removeById(5);
		println "Id of deleted object: ${obj1} "
		assert obj1 == null,'Returned id of deleted object should be null'
		assert TestExecution.list().size() == 4,'After delete, list should still have 4 items.';
		
		println "Tested remove by id."
	}
	
	void testSaveNew() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 3,'Initial list should be 3 big.';
		TestExecution te = new TestExecution(host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date())
		def o = testExecutionService.save(te);
		assert TestExecution.list().size() == 4,'After save, we should have one object more.';
		println "New object=$o"
		assert o.id==4, 'Expecting new object with id 4.'
		
		println "Tested save new."
	}
	
	void testSaveExisting() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 3,'Initial list should be 3 big.';
		TestExecution te = TestExecution.get(3)
		te.browser = 'iexplorer';
		def o = testExecutionService.save(te);
		assert TestExecution.list().size() == 3,'After save of existing, we should still have three.';
		println "Returned object=$o and browser=${o.browser}"
		assert o.id==3, 'Expecting object with id 3.'
		assert o.browser == 'iexplorer','Browser shoulb be iexplorer.'
		
		println "Tested save existing."
	}
	
	void testSaveNull() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 3,'Initial list should be 3 big.';
		try {
			def o = testExecutionService.save(null);
			fail('We should not reach this line but land in catch because of null object in save.')
		}
		catch (RuntimeException e) {
			println e.getMessage();
			assert e.message =~ 'Cannot call.*null.*','Exception message should contain warning about null'
		}
		assert TestExecution.list().size() == 3,'After attempt of save of null object, we should still have three.';
		
		println "Tested save null."
	}
	
	void testSaveNewWithErrors() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 3,'Initial list should be 3 big.';
		TestExecution te = new TestExecution(host:null,testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date())
		try {
			def o = testExecutionService.save(te);
			fail('We should not reach this line but land in catch because of validation error.')
		}
		catch (ValidationException e) {
			println e.getMessage();
			assert e.message =~ 'Field error.*host.*null.*','Exception message should contain warning about host and null'
		}
		assert TestExecution.list().size() == 3,'After failed save, we should still have three objects.';
		
		println "Tested save new with validation errors."
	}
	
	void testSaveExistingWithErrors() {
		mockDomain(TestExecution,[
			[host:'localhost',testScript:'poiundo.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'zoom.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date()],
			[host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R12.RC1',testDate:new Date()]
		]);
		assert TestExecution.list().size() == 3,'Initial list should be 3 big.';
		TestExecution te = TestExecution.get(3)
		te.host = null;
		try {
			def o = testExecutionService.save(te);
			fail('We should not reach this line but land in catch because of validation error.')			
		}
		catch (ValidationException e) {
			println e.getMessage();
			assert e.message =~ 'Field error.*host.*null.*','Exception message should contain warning about host and null'
		}
		assert TestExecution.list().size() == 3,'After failed save, we should still have three objects.';
		
		println "Tested save existing with validation errors."
	}
	
	
}
