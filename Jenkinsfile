pipeline {
    //指定在哪个jenkins集群节点中执行
    agent any
    //声明全局变量,方便后面使用
    environment{
        key='value'
    }
    stages {
        stage('拉起git仓库代码') {
            steps {
                //checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jiangyunhua/testjenkins.git']]])
                echo '拉起git仓库代码成功2'
            }
        }
        stage('通过maven构建项目') {
            steps {
                echo '通过maven构建项目成功'
            }
        }
        stage('通过sonarqube做代码质量检测') {
            steps {
                echo '通过sonarqube做代码质量检测成功'
            }
        }
        stage('通过docker制作自定义镜像') {
            steps {
                echo '过docker制作自定义镜像成功'
            }
        }
        stage('将自定义镜像推送到harbor') {
            steps {
                echo '将自定义镜像推送到harbor成功'
            }
        }
        stage('通过publish over ssh 通知目标服务器') {
            steps {
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
