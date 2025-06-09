import com.michelin.cio.hudson.plugins.rolestrategy.*
import hudson.security.Permission
import jenkins.model.Jenkins
import com.synopsys.arc.jenkins.plugins.rolestrategy.RoleType

def call(List<String> userList = null, List<String> groupList = null ,String globleName = null) {

    Jenkins jenkins = Jenkins.get()
    def rbas = jenkins.getAuthorizationStrategy()
    Set<Permission> permissions = new HashSet<>()
    permissions.add(Jenkins.ADMINISTER)
    Role adminRole = new Role(globleName, permissions)
    def globalRoleMap = rbas.getRoleMap(RoleType.Global)

    userList?.each { username ->
        globalRoleMap.assignRole(adminRole, new PermissionEntry(AuthorizationType.USER, username))
    }
    groupList?.each { groupname ->
        globalRoleMap.assignRole(adminRole,new PermissionEntry(AuthorizationType.GROUP, groupname))
    }

    jenkins.setAuthorizationStrategy(rbas)
    jenkins.save()



}
