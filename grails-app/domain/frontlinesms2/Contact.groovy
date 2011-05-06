package frontlinesms2

class Contact {
	String name
	String address

	static constraints = {
		name(blank: false)
		address(unique: true, nullable: true)
	}

	def beforeDelete = {
		GroupMembership.deleteFor(this)
	}

	def getGroups() {
  		GroupMembership.findAllByContact(this).collect { it.group }
	}

	def setGroups(groups) {
		this.groups.each() { GroupMembership.remove(this, it) }
		groups.each() { GroupMembership.create(this, it) }
	}

	def addToGroups(Group g, flush=false) {
		GroupMembership.create(this, g, flush)
	}

	def removeFromGroups(Group g, flush=false) {
		GroupMembership.remove(this, g, flush)
	}

	boolean isMemberOf(Group group) {
	   GroupMembership.countByContactAndGroup(this, group) > 0
	}
}
