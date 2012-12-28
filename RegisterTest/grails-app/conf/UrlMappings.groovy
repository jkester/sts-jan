class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/rest/testexecution/$id"(controller: "restTestExecution") {
			action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
		}
		"/rest/testexecution/"(controller: "restTestExecution") {
			action = [GET: "list", POST: "save"]
		}
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
