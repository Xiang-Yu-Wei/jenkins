package org.test.adduser

import com.michelin.cio.hudson.plugins.rolestrategy.*
import hudson.security.Permission
import jenkins.model.Jenkins
import com.synopsys.arc.jenkins.plugins.rolestrategy.RoleType

class adduser implements Serializable {

    def assignUsersToRole(List<String> userList, List<String> groupList = null ,String roleName = null , String itemRoleName = null) {
        Jenkins jenkins = Jenkins.get()
        def rbas = jenkins.getAuthorizationStrategy()
        def globalRoleMap = rbas.getRoleMap(RoleType.Global)
        if (roleName == null || roleName.trim() == ''){
            if (itemRoleName == null || itemRoleName.trim() == '') {
                jenkins.setAuthorizationStrategy(rbas)
                jenkins.save()
            }else{
                def ItemRoleMap = rbas.getRoleMap(RoleType.Project)
                Set<Permission> permissions = new HashSet<>()
                permissions.add(Jenkins.ADMINISTER)
                def itemRole = ItemRoleMap.getRole(itemRoleName)
                userList.each { username ->
                    ItemRoleMap.assignRole(itemRole,new PermissionEntry(AuthorizationType.USER, username))
                }
                groupList.each { groupname ->
                    ItemRoleMap.assignRole(itemRole,new PermissionEntry(AuthorizationType.GROUP, groupname))
                }

                jenkins.setAuthorizationStrategy(rbas)
                jenkins.save()
            }

        }else{
            if (itemRoleName == null || itemRoleName.trim() == '') {

                Set<Permission> permissions = new HashSet<>()
                permissions.add(Jenkins.ADMINISTER)
                Role adminRole = new Role(roleName, permissions)
                def role = globalRoleMap.getRole(roleName)

                userList.each { username ->
                    globalRoleMap.assignRole(role, new PermissionEntry(AuthorizationType.USER, username))
                }
                groupList.each { groupname ->
                    globalRoleMap.assignRole(role,new PermissionEntry(AuthorizationType.GROUP, groupname))
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
                    globalRoleMap.assignRole(role, new PermissionEntry(AuthorizationType.USER, username))
                    ItemRoleMap.assignRole(itemRole,new PermissionEntry(AuthorizationType.USER, username))
                }
                groupList.each { groupname ->
                    globalRoleMap.assignRole(role, new PermissionEntry(AuthorizationType.GROUP, groupname))
                    ItemRoleMap.assignRole(itemRole,new PermissionEntry(AuthorizationType.GROUP, groupname))
                }

                jenkins.setAuthorizationStrategy(rbas)
                jenkins.save()
            }


        }


        }
    }




