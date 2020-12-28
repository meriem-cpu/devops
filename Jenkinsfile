pipeline {
    agent any
    stages {
        stage('Poll') {
            steps{
                checkout scm
            }
        }
        stage('Build And Unit Test') {
            steps{
                sh 'mvn clean verify -DskipTests=false -Ddockerfile.skip';
                junit '**/frontend/target/surefire-reports/*.xml'
                archive 'frontend/target/*.jar'
            }
        }
        stage ('Static Code Analysis'){
            steps{
                sh 'mvn clean verify sonar:sonar -Ddockerfile.skip -Dsonar.projectName=reservation-service -Dsonar.projectKey=reservation-service -Dsonar.projectVersion=$BUILD_NUMBER';
            }

        }
        stage ('Publish'){
            steps{
                script{
                    def server = Artifactory.server 'Default Artifactory Server'
                    def uploadSpec = """{
                        "files": [
                            {
                                "pattern": "frontend/target/frontend-0.0.1-SNAPSHOT.jar",
                                "target": "reservation-service/${BUILD_NUMBER}/",
                                "props": "Integration-Tested=Yes;Performance-Tested=No"
                            }
                        ]
                    }"""
                    server.upload(uploadSpec)
                }
            }
        }
        stage('Build And Publish Docker Container'){
            steps{
                sh 'sudo mvn clean verify -Dskip.dockerfile.push';
            }
        }
                stage('Run The Docker Container Locally'){
            steps{
                sh 'sudo docker run -p 5000:8090 bahkuyt/reservation-service:0.0.1-SNAPSHOT &';
            }
        }
    }
    post {
        success {
            emailext (
              subject: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
              body: """<p>SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
              recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
        failure {
          emailext (
              subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
              body: """<p>FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
                <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>&QUOT;</p>""",
              recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}
