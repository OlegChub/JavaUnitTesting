pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("Build") {
            steps {
                echo "Building the app ..."
                bat 'mvn test-compile'
            }
        }

        stage("Docker") {
                    agent {
                                    docker { image 'node:16.13.1-alpine' }
                                }
                                steps {
                                    bat 'node --version'
                                }
                }

        stage("Unit testing") {
            steps {
                echo "Starting unit tests ..."
                bat 'mvn clean -Dtest=ShoppingCartTest test'
                echo "Unit testing is finished"
            }
        }

        stage("API testing") {
                    steps {
                        echo "Starting API tests ..."
                        bat 'mvn clean -Dtest=APITests test surefire-report:report'
                        echo "API testing is finished"

                    }
                }

    }
    post {
                success {
                                    junit '**/target/surefire-reports/TEST-*.xml'
                                }
                }
}