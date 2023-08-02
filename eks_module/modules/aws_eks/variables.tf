variable "eks_cluster_name" {
     default  =  "demo-eks"
}

variable "subnet_ids" {
     
}
variable "tags" {
  
}

variable "security_group_id" {
  description = "The ID of the security group to associate with the EKS cluster."
  type        = string
}
