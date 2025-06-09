@Library('jenkins') _

pipeline {
    agent any

    stages {
        stage('Clone CSV from Git') {
            steps {
                git url: 'https://github.com/Xiang-Yu-Wei/jenkins.git', branch: 'main'
            }
        }

        stage('Assign Roles') {
            steps {
                script {
                    def csvFile = readFile('UserList.csv')
                    def lines = csvFile.readLines()
                  //  def header = lines[0].split(',').collect { it.trim() }
                    lines.drop(1).each { line ->
                        def columns = line.split(',').collect { it.trim() }
                        def user = columns[0]
                        def roleName = columns[1]
                        def itemRole = columns.length > 2 ? columns[2] : ""

                    def userList = [user]
                    assignRoles(userList,roleName,itemRole)
                    println ("给用户 '${user}' 分配全局角色 '${roleName}' 分配项目角色'${itemRole}'")
                }
            }
        }
    }
}
}