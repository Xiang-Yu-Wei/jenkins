def call(List<String> userList = null, List<String> groupList = null ,String roleName = null) {
    def role = new org.test.assignRoles.assignGlobleRoles()
    role.assignUsersToGlobleRole(userList, groupList ,roleName)


}
