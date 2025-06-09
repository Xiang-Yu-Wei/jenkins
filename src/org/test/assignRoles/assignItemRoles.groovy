package org.test.assignRoles

import com.michelin.cio.hudson.plugins.rolestrategy.*
import hudson.security.Permission
import jenkins.model.Jenkins
import com.synopsys.arc.jenkins.plugins.rolestrategy.RoleType

class assignItemRoles implements Serializable {

    def assignUsersToItemRole(List<String> userList = null , List<String> groupList = null , String itemRoleName = null) {
        Jenkins jenkins = Jenkins.get()
        def rbas = jenkins.getAuthorizationStrategy()
        Set<Permission> permissions = new HashSet<>()
        permissions.add(Jenkins.ADMINISTER)
        Role adminRole = new Role(itemRoleName, permissions)
        def ItemRoleMap = rbas.getRoleMap(RoleType.Project)


        userList?.each { username ->
            ItemRoleMap.assignRole(adminRole, new PermissionEntry(AuthorizationType.USER, username))
        }
        groupList?.each { groupname ->
            ItemRoleMap.assignRole(adminRole, new PermissionEntry(AuthorizationType.GROUP, groupname))
        }

        jenkins.setAuthorizationStrategy(rbas)
        jenkins.save()
    }


    }




