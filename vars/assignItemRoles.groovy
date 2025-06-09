def call(List<String> userList = null, List<String> groupList = null ,String itemName = null) {
    def role = new org.test.assignRoles.assignItemRoles()
    role.assignUsersToItemRole(userList, groupList ,itemName)


}
