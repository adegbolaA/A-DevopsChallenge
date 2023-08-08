 Unit Conversion Java Application and Jenkins Pipeline Readme Documentation

This is the README documentation for a Temperation Conversion Application deployed in Jenkins pipeline designed to automate the deployment of a Java application using Docker and Kubernetes on AWS EKS (Elastic Kubernetes Service). The pipeline follows a Continuous Integration/Continuous Deployment (CI/CD) approach to ensure the application is built, tested, and deployed in a controlled and automated manner.


The Application developed using Spring Boot, Maven and Unit Testing with Junit

Pipeline Overview
The pipeline is written in Jenkins Declarative Pipeline syntax. It is designed to be triggered manually with a set of user-defined parameters. These parameters allow users to choose the action (create or delete), specify the Docker image name, tag, AWS account ID, region, ECR (Elastic Container Registry) repository name, and EKS (Elastic Kubernetes Service) cluster name.



Pipeline Stages
The pipeline consists of the following stages:

1. Git Checkout
Stage Name: Git Checkout
Purpose: Fetches the source code from a specified Git repository for further processing.
Conditions: This stage is executed only when the action parameter is set to 'create'.

2. Maven Unit Test
Stage Name: Maven Unit Test
Purpose: Runs unit tests on the Java application using Maven.
Conditions: This stage is executed only when the action parameter is set to 'create'.

3. Maven Integration Test
Stage Name: Maven Integration Test
Purpose: Runs integration tests on the Java application using Maven.
Conditions: This stage is executed only when the action parameter is set to 'create'.

4. Static Code Analysis: Sonarqube
Stage Name: Static Code Analysis: Sonarqube
Purpose: Performs static code analysis on the Java application using SonarQube.
Conditions: This stage is executed only when the action parameter is set to 'create'.

5. Quality Gate Status Check: Sonarqube
Stage Name: Quality Gate Status Check: Sonarqube
Purpose: Checks the quality gate status of the SonarQube analysis results.
Conditions: This stage is executed only when the action parameter is set to 'create'.

6. Maven Build
Stage Name: Maven Build
Purpose: Builds the Java application using Maven.
Conditions: This stage is executed only when the action parameter is set to 'create'.

7. Docker Image Build: ECR
Stage Name: Docker Image Build: ECR
Purpose: Builds a Docker image for the Java application and pushes it to an Elastic Container Registry (ECR).
Conditions: This stage is executed only when the action parameter is set to 'create'.

8. Docker Image Scan ECR: Trivy
Stage Name: Docker Image Scan ECR: Trivy
Purpose: Scans the Docker image in ECR using Trivy for security vulnerabilities.
Conditions: This stage is executed only when the action parameter is set to 'create'.

9. Docker Image Push: ECR
Stage Name: Docker Image Push: ECR
Purpose: Pushes the Docker image to the Elastic Container Registry (ECR).
Conditions: This stage is executed only when the action parameter is set to 'create'.

10. EKS Cluster Creation: Terraform
Stage Name: EKS Cluster Creation: Terraform
Purpose: Creates an EKS cluster using Terraform.
Conditions: This stage is executed only when the action parameter is set to 'create'.

11. Connection to EKS
Stage Name: Connection to EKS
Purpose: Configures AWS CLI to connect to the EKS cluster.
Conditions: This stage is executed only when the action parameter is set to 'create'.

12. Deploy to EKS
Stage Name: Deploy to EKS
Purpose: Deploys the Java application to the EKS cluster.
Conditions: This stage is executed only when the action parameter is set to 'create'.
User Input: This stage requires manual confirmation before proceeding with the deployment to the production environment (EKS).


Pipeline Post-actions
The pipeline defines post-actions that will execute depending on the pipeline's result:

On Success: Sends an email notification indicating the successful execution of the pipeline.
On Failure: Sends an email notification indicating the failure of the pipeline.
On Unstable: Sends an email notification indicating an unstable state of the pipeline.


Prerequisites
Before running the pipeline, ensure the following:

Jenkins should have the shared library 'my-shared-library' configured.
The necessary credentials for AWS (AWS_ACCESS_KEY_ID, AWS_SECRET_KEY_ID) and SonarQube (sonarqube-api) are set in Jenkins.


Pipeline Execution
Open Jenkins and navigate to the desired pipeline job.
Click on "Build with Parameters."
Fill in the required parameters: action, ImageName, ImageTag, DockerHubUser, aws_account_id, Region, ECR_REPO_NAME, and Cluster.
Click on "Build" to trigger the pipeline.
Please note that the pipeline stages are designed to run conditionally based on the action parameter. If you set action to 'delete', the pipeline will not execute any of the stages.

Conclusion
This Jenkins pipeline provides a robust and automated approach to building, testing, and deploying a Java application using Docker and Kubernetes on AWS EKS. By leveraging the power of Jenkins and the shared library 'my-shared-library', developers can streamline their deployment process, improve code quality, and ensure smooth CI/CD workflows.