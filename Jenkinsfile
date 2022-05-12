pipeline {
    //指定在哪个jenkins集群节点中执行
    agent any
    //声明全局变量,方便后面使用
    environment{
        harborUser='admin'
        harborPwd='Harbor12345'
        harborUrl='192.168.1.135:80'
        harborRepo='repo'

    }
    stages {
        stage('拉取git仓库代码') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '${tag}']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jiangyunhua/testjenkins.git']]])
                echo '拉取git仓库代码成功2'
            }
        }
        stage('通过maven构建项目') {
            steps {
                sh '/var/jenkins_home/maven/bin/mvn clean package -DskipTests'
                echo '通过maven构建项目成功'
            }
        }
        stage('通过sonarqube做代码质量检测') {
            steps {
                sh '/var/jenkins_home/sonar-scanner/bin/sonar-scanner  -Dsoanr.sources=./ -Dsonar.projectname=pipeline-test  -Dsonar.login=05c38c247c233e5771e55a061676cfa26319fa5a  -Dsonar.projectKey=pipeline-test   -Dsonar.java.binaries=./target'
                echo '通过sonarqube做代码质量检测成功'
            }
        }
        stage('通过docker制作自定义镜像') {
            steps {
                sh 'mv target/*.jar docker/'
                sh 'docker build -t mytest:$tag docker/'
                echo '过docker制作自定义镜像成功'
            }
        }
        stage('将自定义镜像推送到harbor') {
            steps {
                sh 'docker login -u ${harborUser} -p ${harborPwd}  ${harborUrl}'
                sh 'docker tag  mytest:$tag  192.168.1.135:80/repo/mytest:$tag'
                sh 'docker push 192.168.1.135:80/${harborRepo}/mytest:$tag'
                echo '将自定义镜像推送到harbor成功'
            }
        }
        stage('通过publish over ssh 通知目标服务器') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'client-146', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''cd /usr/local/test/shell
                chmod a+x deploy.sh
                deploy.sh  192.168.1.135:80 repo mytest $tag  $port''', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'shell/*.sh')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                echo '通过publish over ssh 通知目标服务器成功'
            }
        }
        stage('通知成员') {
            steps {
                echo '通知成员'
            }
        }

    }
}
