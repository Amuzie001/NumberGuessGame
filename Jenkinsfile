pipeline {
    agent any

    environment {
        APP_NAME   = "NumberGuessGame"
        WAR_FILE   = "target/NumberGuessGame.war"
        DEPLOY_DIR = "/opt/tomcat/webapps" // Adjust if Tomcat is elsewhere
        TOMCAT_URL = "http://16.171.113.59:8080/${APP_NAME}"
    }

    options {
        // Keep last 10 builds
        buildDiscarder(logRotator(numToKeepStr: '10'))
        // Fail fast if any stage fails
        skipDefaultCheckout(false)
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                git branch: 'main', url: 'https://github.com/Amuzie001/NumberGuessGame.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project with Maven...'
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Code Quality') {
            steps {
                echo 'Running code quality checks...'
                // Uncomment after SonarQube is configured in Jenkins
                // sh 'mvn sonar:sonar -Dsonar.projectKey=NumberGuessGame'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the WAR file to Tomcat...'
                // Ensure Jenkins has write permissions to Tomcat
                sh """
                    cp ${WAR_FILE} ${DEPLOY_DIR}/
                    sudo systemctl restart tomcat
                """
            }
        }

        stage('Verify Deployment') {
            steps {
                echo 'Checking if the application is running...'
                script {
                    try {
                        sh "curl -s -o /dev/null -w '%{http_code}' ${TOMCAT_URL} | grep 200"
                        echo 'Deployment verified successfully!'
                    } catch (Exception e) {
                        error 'Deployment verification failed!'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
