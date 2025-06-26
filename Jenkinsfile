pipeline {
	agent any 
	parameters {
		choice (name: 'SUITE', choices:['testng-smoke.xml','testng-regression.xml','testng-sanity.xml' ], description: 'Chọn suite cần chạy')
	}
	stages {
		 stage('Info') {
            steps {
                echo "🚀 Đang chạy trên nhánh: ${env.BRANCH_NAME}"
            }
        }
		stage ('Clean workspace'){
			step{
				cleanWs()
			}
		}
		stage ('checkout') {
			step {
				checkout scm
			}
		}
		stage ('Run test'){
		stage('Run Tests') {
            steps {
                script {
                    if (isUnix()) {
                        // Linux/macOS
                        if (env.BRANCH_NAME == 'dev') {
                            sh "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                        } else if (env.BRANCH_NAME == 'staging') {
                            sh "mvn clean test -DsuiteXmlFile=testng-regression.xml"
                        } else if (env.BRANCH_NAME == 'prod') {
                            sh "mvn clean test -DsuiteXmlFile=testng-full.xml"
                        } else {
                            sh "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                        }
                    } else {
                        // Windows
                        if (env.BRANCH_NAME == 'dev') {
                            bat "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                        } else if (env.BRANCH_NAME == 'staging') {
                            bat "mvn clean test -DsuiteXmlFile=testng-regression.xml"
                        } else if (env.BRANCH_NAME == 'prod') {
                            bat "mvn clean test -DsuiteXmlFile=testng-full.xml"
                        } else {
                            bat "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                        }
                    }
                }
            }
        }
    }
	post {
		always {
			 allure includeProperties = true, jdk:'', result:[[path: 'allure-results']]
		}
	}

