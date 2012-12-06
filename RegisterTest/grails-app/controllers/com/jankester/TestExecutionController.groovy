package com.jankester

class TestExecutionController {
	def index = { redirect(action: home) }
	def home = { render "<h1>Real Programmers do not each quiche!</h1>" }
	
	def show = { render "<h1>Show me this object</h1>" }
	
	def update = {}
	
	def delete = {}
	
	def save = {}

	def scaffold = true;
}
