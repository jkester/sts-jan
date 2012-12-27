package com.jankester

class TestExecutionService {

    def findForVersion(String appVersion) {
		return TestExecution.findAllByAppVersion(appVersion);
    }
	
	def findBetweenExecDates(Date start, Date end) {
		return TestExecution.findAllByTestDateBetween(start, end)
	}
	
	def findAll() {
		return TestExecution.findAll()
	}
	
	def findById(def id) {
		return TestExecution.findById(id);
	}
	
	def removeById(def id) {
		def o = TestExecution.findById(id);
		if (o == null) {
			log.error("Did not find object with id=$id");
		}
		else {
			o.delete(flush:true);
		}
		return o?.id;
	}
}
