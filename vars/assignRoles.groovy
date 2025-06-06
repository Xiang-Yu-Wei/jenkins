def call(List<String> users, String roleName) {
    def role = new org.test.adduser.adduser()
    role.assignUsersToRole(users, roleName)
}
