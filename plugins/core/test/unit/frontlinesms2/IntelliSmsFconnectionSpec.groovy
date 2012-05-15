package frontlinesms2

import grails.plugin.spock.UnitSpec

class IntelliSmsFconnectionSpec extends UnitSpec {
	def 'creating a sendOnly IntelliSmsFconnection validates'() {
		setup:
			mockDomain(IntelliSmsFconnection)
			def intellismsConn
		when:"send property not set"
			intellismsConn = new IntelliSmsFconnection(name:"test", username:"test", password:"****")
		then:
			!intellismsConn.validate()
		when:"username and password not set"
			intellismsConn = new IntelliSmsFconnection(name:"test", send:true)
		then:
			!intellismsConn.save()
			intellismsConn.hasErrors()
		when:
			intellismsConn = new IntelliSmsFconnection(send:true, name:"test", username:"test", password:"****")
		then:
			intellismsConn.save()
	}
	
	def 'creating a receiveOnly IntelliSmsFconnection validates'() {
		setup:
			mockDomain(IntelliSmsFconnection)
			def intellismsConn
		when:"receive property not set"
			intellismsConn = new IntelliSmsFconnection(name:"test", serverName:"imap.gmail.com", serverPort:"993", emailUserName:"test",emailPassword:"****", receiveProtocol:EmailReceiveProtocol.IMAP)
		then:
			!intellismsConn.validate()
		when:"email fields not set"
			intellismsConn = new IntelliSmsFconnection(receive:true, name:"test", username:"test", password:"****")
		then:
			!intellismsConn.save()
			intellismsConn.hasErrors()
		when:
			intellismsConn =  new IntelliSmsFconnection(name:"test", receive:true, serverName:"imap.gmail.com", serverPort:"993", emailUserName:"test",emailPassword:"****", receiveProtocol:EmailReceiveProtocol.IMAP)
		then:
			intellismsConn.save()
	}
	
	def 'creating a send and receive IntelliSmsFconnection validates'() {
		setup:
			mockDomain(IntelliSmsFconnection)
			def intellismsConn
		when:"receive property not set"
			intellismsConn = new IntelliSmsFconnection(receive: true, send:true) 
		then:
			!intellismsConn.validate()
		when:"email fields not set"
			intellismsConn = new IntelliSmsFconnection(receive:true, name:"test", username:"test", password:"****")
		then:
			!intellismsConn.save()
			intellismsConn.hasErrors()
		when:
			intellismsConn =  new IntelliSmsFconnection(name:"test", receive:true, serverName:"imap.gmail.com", serverPort:"993", emailUserName:"test",emailPassword:"****", receiveProtocol:EmailReceiveProtocol.IMAP, send:true, password:"***")
		then:
			!intellismsConn.save()
		when:
			intellismsConn =  new IntelliSmsFconnection(name:"test", receive:true, serverName:"imap.gmail.com", serverPort:"993", emailUserName:"test",emailPassword:"****", receiveProtocol:EmailReceiveProtocol.IMAP, send:true, username:"test", password:"***")
		then:
			intellismsConn.save()
	}

	def "getNonnullableConfigFields should return a list of nonnullable fields"() {
		setup:
			MetaClassModifiers.addMapMethods()
			mockForConstraintsTests(IntelliSmsFconnection)
		when:
			def configFields = IntelliSmsFconnection.configFields
			def conn = Fconnection.getNonnullableConfigFields(IntelliSmsFconnection)
		then:
			conn instanceof List
	}
		
}
