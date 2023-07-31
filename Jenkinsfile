@Library('my-shared-library') _



pipeline{
    environment {
        //KUBECONFIG = "/home/ec2-user/.kube/config"
        KUBECONFIG = credentials('kubeconfig-credential-id')
    }

    agent any {
        docker {
            image 'jenkins/inbound-agent'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    parameters{
        choice(name: 'action', choices: 'create\ndelete', description: 'Choose create/Destroy')
        string(name: 'ImageName', description: "Name of the docker build", defaultValue: 'javaapp')
        string(name: 'ImageTag', description: "Tag of the docker build", defaultValue: 'v1')
        string(name: 'DockerHubUser', description: "Name of the Application", defaultValue: 'kevinlearningaccount')
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
                    def SonarQubeCredentialsId = 'sonarqube-api'
                     staticCodeAnalysis(SonarQubeCredentialsId)
              }

            }
           
        }

        stage ('Quality Gate Status Check: Sonarqube'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                    def SonarQubeCredentialsId = 'sonarqube-api'
                     QualityGateStatus(SonarQubeCredentialsId)
              }

            }
           
        }

        stage ('Maven Build'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                    mvnBuild()
              }

            }
           
        }

        stage ('Docker Image Build'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                    dockerBuild("${params.ImageName}","${params.ImageTag}","${params.DockerHubUser}")
              }

            }
           
        }

        stage ('Docker Image Scan: Trivy'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                    dockerImageScan("${params.ImageName}","${params.ImageTag}","${params.DockerHubUser}")
              }

            }
           
        }

        stage ('Docker Image Push: DockerHub'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                    dockerImagePush("${params.ImageName}","${params.ImageTag}","${params.DockerHubUser}")
              }

            }
           
        }

        stage ('Docker Image Cleanup: DockerHub'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                    dockerImageCleanUp("${params.ImageName}","${params.ImageTag}","${params.DockerHubUser}")
              }

            }
           
        }

         stage ('Install kubectl'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                     // Linux-based installation of kubectl
                    sh 'curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"'
                    sh 'chmod +x kubectl'
                    sh 'mkdir -p ~/bin'
                    sh 'mv kubectl ~/bin/'
              }

            }
           
        } 


         stage ('Deploy'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                   
                   sh "kubectl --kubeconfig $KUBECONFIG apply -f deployment.yaml"

                    /* withEnv(["KUBECONFIG=$KUBECONFIG"]) {
                      
                           env.PATH = "$HOME/bin:${env.PATH}"
                           sh 'kubectl apply -f deployment.yaml'
                        
                    } */
                  
                   
              }
             

            }
           
        } 



       /*  stage ('Deploy'){
         when {expression { params.action == 'create' }}
            steps{

                script{
                  kubernetesDeploy (configs: 'deployment.yaml', kubeconfigId: 'kube')
              }

            }
           
        } 
 */




















    }
}