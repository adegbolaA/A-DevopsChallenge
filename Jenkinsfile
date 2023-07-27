@Library('my-shared-library') _



pipeline{
    agent any

    stages{

        stage ('Git Checkout'){

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

            steps{

                script{
                     mvnTest()
              }

            }
           
        }

         stage ('Integration Test'){

            steps{

                script{
                     mvnIntegrationTest()
              }

            }
           
        }



















    }
}