pipeline {
    agent any

    tools {
        jdk 'jdk17'       // Must match the JDK name in Jenkins Global Tool Configuration
        maven 'maven3'    // Must match the Maven name in Jenkins Global Tool Configuration
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
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Code Quality') {
            steps {
                echo 'Running SonarQube analysis...'
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                sh 'echo "Deploy step goes here"'
            }
        }

        stage('Verify Deployment') {
            steps {
                echo 'Verifying deployment...'
                sh 'echo "Verification step goes here"'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check the logs for details.'
        }
        always {
            echo 'Cleaning up workspace...'
            // cleanWs()   // Uncomment this only after installing "Workspace Cleanup Plugin"
        }
    }
}
