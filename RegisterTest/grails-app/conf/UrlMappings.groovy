class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/testexecution/$id"(controller: "testExecution") {
			action = [GET: "show", PUT: "update", DELETE: "delete", POST: "save"]
		}
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
