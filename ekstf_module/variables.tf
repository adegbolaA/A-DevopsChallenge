variable "aws_region" {
  description = "The AWS region where the EKS cluster will be created."
  default     = "us-east-2"
}

variable "eks_cluster_name" {
  description = "The name of the EKS cluster."
  default     = "conversionapp-cluster"
}

variable "access_key" {
  description = "The AWS access key used to authenticate Terraform."
  type        = string
}

variable "secret_key" {
  description = "The AWS secret key used to authenticate Terraform."
  type        = string
}