includeTargets << grailsScript("_GrailsInit")

target(main: "The description of the script goes here!") {

	def restBuilder = classLoader.loadClass("grails.plugins.rest.client.RestBuilder").newInstance()
	def resp = restBuilder.rest.get('http://www.environmentlocations.org/registertest/rest/testexecution/')
	resp.json instanceof net.sf.json.JSONObject
	resp.json.name == 'acegi'
}

setDefaultTarget(main)
