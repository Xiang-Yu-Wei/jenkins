package org.test.adduser

import com.michelin.cio.hudson.plugins.rolestrategy.*
import hudson.security.Permission
import jenkins.model.Jenkins
import com.synopsys.arc.jenkins.plugins.rolestrategy.RoleType

class adduser implements Serializable {

    def assignUsersToRole(List<String> userList, String roleName , String itemRoleName = null) {
        Jenkins jenkins = Jenkins.get()
        def rbas = jenkins.getAuthorizationStrategy()
        def globalRoleMap = rbas.getRoleMap(RoleType.Global)
        if (itemRoleName == null || itemRoleName.trim() == '') {

            Set<Permission> permissions = new HashSet<>()
            permissions.add(Jenkins.ADMINISTER)
            Role adminRole = new Role(roleName, permissions)
            def role = globalRoleMap.getRole(roleName)

            userList.each { username ->
                println("给用户 '${username}' 分配角色 '${roleName}'")
                globalRoleMap.assignRole(role, new PermissionEntry(AuthorizationType.USER, username))
            }

            jenkins.setAuthorizationStrategy(rbas)
            jenkins.save()

        } else {

            def ItemRoleMap = rbas.getRoleMap(RoleType.Project)


            Set<Permission> permissions = new HashSet<>()
            permissions.add(Jenkins.ADMINISTER)
            Role adminRole = new Role(roleName, permissions)

            def role = globalRoleMap.getRole(roleName)

            def itemRole = ItemRoleMap.getRole(itemRoleName)
            userList.each { username ->
                println("给用户 '${username}' 分配角色 '${roleName}'")
                globalRoleMap.assignRole(role, new PermissionEntry(AuthorizationType.USER, username))
                ItemRoleMap.assignRole(itemRole,new PermissionEntry(AuthorizationType.USER, username))
            }

            jenkins.setAuthorizationStrategy(rbas)
            jenkins.save()
        }


        }
    }




