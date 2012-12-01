package com.jankester

class TestExecution {

	String host;
	String testScript;
	String browser;
	String testUrl;
	String appVersion;
	Date testDate;
	
    static constraints = {
		host(blank:false)
		testScript(blank:false)
		browser(blank:false)
		testUrl(blank:false)
		appVersion(blank:false)
		testDate(nullable:true)
    }
}
