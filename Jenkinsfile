pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage("Build") {
            steps {
                sh 'gradle --version'
                echo "Building the app ..."
                sh 'mvn test-compile'
            }
        }

        stage("Unit testing") {
            steps {
                echo "Starting unit tests ..."
                sh 'mvn clean -Dtest=ShoppingCartTest test'
                echo "Unit testing is finished"
            }
        }

        stage("API testing") {
                    steps {
                        echo "Starting API tests ..."
                        sh 'mvn clean -Dtest=APITests test surefire-report:report'
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