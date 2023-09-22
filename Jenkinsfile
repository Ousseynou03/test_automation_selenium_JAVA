pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                echo 'Building..'
                sh "mvn clean -Dbrowser=chrome"
            }
        }
        stage('Build') {
            steps {
                echo 'Versioning..'
                sh "mvn clean -X -Dbrowser=chrome"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh "mvn clean install test -Dbrowser=chrome"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                sh "mvn clean package -Dbrowser=chrome"
            }
        }
    }
}