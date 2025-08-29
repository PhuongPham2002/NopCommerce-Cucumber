pipeline {
	agent any 
	parameters {
		choice (name: 'SUITE', choices:['testng-smoke.xml','testng-regression.xml','testng-sanity.xml' ], description: 'Chọn suite cần chạy')
		choice(name:'BROWSER',choices:['chrome','firefox','edge']), description:'Choose browser to run test')
		choice(name: 'TEST_ENV', choices: ['dev','staging','production'], description: 'Choose testing environment to run test')
		string(name: 'TAGS', defaultValue: '', description: 'Filter theo Cucumber tags, vd: @Login hoặc @Smoke')
		booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Chạy headless mod')

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
                            sh "mvn clean test -Dcucumber.filter.tags=@Smoke -Dbrowser=chrome -DtestEnv=dev"
                        } else if (env.BRANCH_NAME == 'staging') {
                            sh "mvn clean test -Dcucumber.filter.tags=@Regression -Dbrowser=chrome -DtestEnv=dev"
                        } else if (env.BRANCH_NAME == 'prod') {
                            sh "mvn clean test -Dcucumber.filter.tags=@Full -Dbrowser=chrome -DtestEnv=dev"
                        } else {
                            sh "mvn clean test -Dcucumber.filter.tags=@Smoke -Dbrowser=chrome -DtestEnv=dev"
                        }
                    } else {
                        // Windows
                        if (env.BRANCH_NAME == 'dev') {
                            bat "mvn clean test -Dcucumber.filter.tags=@Smoke -Dbrowser=chrome -DtestEnv=dev"
                        } else if (env.BRANCH_NAME == 'staging') {
                            bat "mvn clean test -Dcucumber.filter.tags=@Regression -Dbrowser=chrome -DtestEnv=dev"
                        } else if (env.BRANCH_NAME == 'prod') {
                            bat "mvn clean test -Dcucumber.filter.tags=@Full -Dbrowser=chrome -DtestEnv=dev"
                        } else {
                            bat "mvn clean test -Dcucumber.filter.tags=@Smoke -Dbrowser=chrome -DtestEnv=dev"
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

