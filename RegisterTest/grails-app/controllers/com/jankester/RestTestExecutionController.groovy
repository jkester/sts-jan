package com.jankester

import grails.converters.JSON
import grails.converters.XML

class RestTestExecutionController {

	def testExecutionService;

	def show = {
		def item = testExecutionService.findById(params.id);
		if (!item) {
			log.error "Did not find ${params.id}"
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
		def deletedId = testExecutionService.removeById(params.id);
		log.info "Delete service returned $deletedId"
		if (deletedId == null) {
			log.error "Did not find ${params.id}"
			withFormat renderNotFound;
		}
		else {
			log.info "succesfully deleted $deletedId"
			render status: 204
		}
	}

	def save = {
		def jsonO = request.JSON;
		def o;
		println "Retrieving json request $jsonO"
		if (params.id) {
			log.info "Doing an update for id $params.id"
			TestExecution te = testExecutionService.findById(params.id);
			if (!te) {
				log.error "Did not find ${params.id}"
				withFormat renderNotFound;
			}
			if (jsonO.host) te.host = jsonO.host;
			if (jsonO.testscript) te.testScript = jsonO.testscript;
			if (jsonO.browser) te.browser = jsonO.browser;
			if (jsonO.testurl) te.testUrl = jsonO.testurl;
			if (jsonO.appversion) te.appVersion = jsonO.appversion;
			if (!te.validate()) {
				log.error "No proper json object supplied: ${te.errors}"
				withFormat renderWrongFormat;
			}
			else {
				o = testExecutionService.save(te);
				log.info "Saved existing object with id=${o?.id}"
			}
		}
		else {
			log.info "Creating a new object"
			TestExecution te = new TestExecution(host:jsonO.host,testScript:jsonO.testscript,browser:jsonO.browser,testUrl:jsonO.testurl,appVersion:jsonO.appversion,testDate:new Date())
			if (!te.validate()) {
				log.error "No proper json object supplied: ${te.errors}"
				withFormat renderWrongFormat;
			}
			else {
				o = testExecutionService.save(te);
				log.info "Saved new object with id=${o?.id}"
			}
		}
		withFormat {
			html { render o as JSON }
			xml { render o as XML }
			json { render o as JSON }
		}
		
	}

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
	
	def renderWrongFormat = {
		html {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cow.label', default: 'Cow'), params.id])}"
			redirect(action: "list")
		}
		xml {
			response.status = 409
			render "Supplied POST object was not correct."
		}
		json  {
			response.status = 409
			render "Supplied POST object was not correct."
		}
	}
	
	
}
