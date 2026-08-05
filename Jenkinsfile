pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    stages {
        stage('Environment') {
            steps {
                sh 'chmod +x gradlew'
                sh 'java -version'
                sh './gradlew --version'
            }
        }

        stage('Smoke Tests') {
            steps {
                sh './gradlew clean smoketests --no-daemon --stacktrace'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: 'build/test-results/smoketests/*.xml'
        }
    }
}