package com.jankester

class TestExecutionController {
	def index = { redirect(action: home) }
	def home = { render "<h1>Real Programmers do not each quiche!</h1>" }

	def scaffold = true;
}
