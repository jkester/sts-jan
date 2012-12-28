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
			id = null;
		}
		else {
			log.info "Going to delete object id=$id"
			o.delete(flush:true);
		}
		return id;
	}
	
	def save(TestExecution o) {
		log.info "Saving object with id=${o?.id}"
		if (o == null) {
			throw new RuntimeException('Cannot call save(TestExecution o) with null object.')
		}
		o.save(flush: true, failOnError: true);
		return o;
	}
}
