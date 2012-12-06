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
}
