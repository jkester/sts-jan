package com.jankester

import grails.converters.JSON
import grails.converters.XML

class RestTestExecutionController {

	def testExecutionService;

	def show = {
		def item = testExecutionService.findById(params.id);
		if (!item) {
			withFormat renderNotFound;
		}
		else {
			withFormat {
				html { render item as JSON }
				xml { render item as XML }
				json { render item as JSON }
			}
		}
	}

	def update = {}

	def delete = {
		//http://www.springminutes.com/2011/06/grails-controllers-and-rest-part-2.html
	}

	def save = {}

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def exampleList = testExecutionService.findAll();
		withFormat {
			html { render exampleList as JSON }
			xml { render exampleList as XML }
			json { render exampleList as JSON }
		}
	}

	def renderNotFound = {
		html {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cow.label', default: 'Cow'), params.id])}"
			redirect(action: "list")
		}
		xml {
			response.status = 404
			render "TestExecution not found."
		}
		json  {
			response.status = 404
			render "TestExecution not found."
		}
	}
}
