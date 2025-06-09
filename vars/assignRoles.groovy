def call(List<String> userList = null, List<String> groupList = null ,String roleName = null , String itemRoleName = null) {
    def role = new org.test.adduser.adduser()
    role.assignUsersToRole(userList, groupList ,roleName ,itemRoleName)

}
