variable "aws_region" {
  description = "The AWS region where the EKS cluster will be created."
  default     = "us-east-2"
}

variable "eks_cluster_name" {
  description = "The name of the EKS cluster."
  default     = "conversionapp-cluster"
}
