@Library('my-shared-library') _
buildJavaApp("/var/lib/jenkins/workspace/Java-App-Pipeline/ChallengePackage/demo")


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
    }
}