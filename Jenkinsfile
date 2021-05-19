pipeline {
    agent any

   environment {
        wildfly_host = "172.20.1.1"
        wildfly_port = "9990"
        wildfly_username = "wildflyAdmin"
        wildfly_password = "inpi@2019"
   }

    tools {
        maven "M3"
    }

    stages {
        stage('Geração do pacote') {
            steps {
                sh "mvn clean install"
            }
        }
    }
}
