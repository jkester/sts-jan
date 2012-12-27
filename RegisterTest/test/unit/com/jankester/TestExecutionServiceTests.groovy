package com.jankester



import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin;

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
	
}
