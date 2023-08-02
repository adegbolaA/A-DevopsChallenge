variable "eks_cluster_name" {
  
}
variable "node_group_name" {
  default = "nodes-general"
}

variable "subnet_ids" {
  
}

variable "tags" {
  
}
variable "nodes_iam_role" {
  default = "eks-node-group-general"
}

variable "security_group_id" {
  description = "The ID of the security group to associate with the EKS cluster."
  type        = string
}
