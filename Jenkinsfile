pipeline {
    agent any
    tools {
        maven "maven_3.8.6"
    }
    stages {
        stage("checkout repo") {
            steps {
                git branch:"master",
                credentialsId:"git_credentials",
                url:"https://github.com/avoevodin81/cucumber-tutorial.git"
            }
        }
        stage("initialize") {
            steps {
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
            }
        }
        stage("build") {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage("run ui tests") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "mvn clean test -DCUCUMBER_FILTER_TAGS=${suite} -Dbrowser=${browser}"
                }
            }
        }
        stage("reports") {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/**', fingerprint: true
        }
    }
}