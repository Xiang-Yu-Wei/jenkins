package org.test.adduser

import com.michelin.cio.hudson.plugins.rolestrategy.*
import hudson.security.Permission
import jenkins.model.Jenkins
import com.synopsys.arc.jenkins.plugins.rolestrategy.RoleType

class adduser implements Serializable {

    def assignUsersToRole(List<String> userList, String roleName) {
        Jenkins jenkins = Jenkins.get()
        def rbas = jenkins.getAuthorizationStrategy()
        def globalRoleMap = rbas.getRoleMap(RoleType.Global)

        Set<Permission> permissions = new HashSet<>()
        permissions.add(Jenkins.ADMINISTER)
        Role adminRole = new Role(roleName, permissions)

        def role = globalRoleMap.getRole(roleName)
        if (role == null) {
            println("找不到 $roleName 自动创建")
            globalRoleMap.addRole(adminRole)
            role = globalRoleMap.getRole(roleName)
            if (role == null) {
                throw new RuntimeException("角色创建失败")
            }
        }

        userList.each { username ->
            println("给用户 '${username}' 分配角色 '${roleName}'")
            globalRoleMap.assignRole(role, new PermissionEntry(AuthorizationType.USER, username))
        }

        jenkins.setAuthorizationStrategy(rbas)
        jenkins.save()
    }
}
