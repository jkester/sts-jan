package com.jankester



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TestExecution)
class TestExecutionTests {

	void testConstraints() {
		def existingTE = new TestExecution(host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:new Date())
		mockForConstraintsTests(TestExecution, [existingTE])

		// validation should fail if both properties are null
		def testExec = new TestExecution()
		assert !testExec.validate() 
		//println "test execution errors: ${testExec.errors}"
		println "host error: ${testExec.errors['host']}"
		assert "nullable" == testExec.errors["host"]
		assert "nullable" == testExec.errors["testScript"]
		assert "nullable" == testExec.errors["browser"]
		assert "nullable" == testExec.errors["testUrl"]
		assert "nullable" == testExec.errors["appVersion"]		
		println "Tested null for TestExecution"
		
		testExec = new TestExecution(host:'',testScript:'',browser:'',testUrl:'',appVersion:'',testDate:null)
		assert !testExec.validate()
		//println "test execution errors: ${testExec.errors}"
		println "host error: ${testExec.errors['host']}"
		assert "blank" == testExec.errors["host"]
		assert "blank" == testExec.errors["testScript"]
		assert "blank" == testExec.errors["browser"]
		assert "blank" == testExec.errors["testUrl"]
		assert "blank" == testExec.errors["appVersion"]		
		println "Tested blank for TestExecution"


		// Validation should pass! 
		testExec = new TestExecution(host:'localhost',testScript:'doublelines.groovy',browser:'firefox',testUrl:'coulthard',appVersion:'2011.R11.RC1',testDate:null) 
		assert testExec.validate()	
		println "Tested positive for TestExecution"
	}

}
