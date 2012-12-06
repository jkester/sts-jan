package com.jankester

class TestExecutionService {

    def findForVersion(String appVersion) {
		return TestExecution.findAllByAppVersion(appVersion);
    }
	
	def findBetweenExecDates(Date start, Date end) {
		return TestExecution.findAllByTestDateBetween(start, end)
	}
}
