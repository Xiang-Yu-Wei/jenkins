def call(List<String> users, String roleName = null , String itemRoleName = null) {
    def role = new org.test.adduser.adduser()
    role.assignUsersToRole(users, roleName ,itemRoleName)

}
