@Library('my-shared-library') _



pipeline{
    agent any

    parameters{
        choice(name: 'action', choices: 'create\ndelete', description: 'Choose create/Destroy')
    }

    stages{
        

        stage ('Git Checkout'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                     gitCheckout(
                        branch: "main",
                        url: 'https://github.com/adegbolaA/A-DevopsChallenge.git'
                     )
                    
                 }

            }
           
        }

         stage ('Maven Unit Test'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                     mvnTest()
              }

            }
           
        }

         stage ('Maven Integration Test'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                     mvnIntegrationTest()
              }

            }
           
        }

        stage ('Static Code Analysis: Sonarqube'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                    def SonarQubeCedentialsId = 'sonarqube-api'
                     staticCodeAnalysis(SonarQubeCredentialsId)
              }

            }
           
        }



















    }
}