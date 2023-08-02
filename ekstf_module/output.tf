# Output the EKS cluster's kubeconfig
output "kubeconfig" {
  value = aws_eks_cluster.my_eks_cluster.kubeconfig
  key_name              = "NewKeyPair"  
}

# Output the EKS cluster's worker nodes security group
output "worker_nodes_security_group" {
  value = aws_security_group.eks_worker_security_group.id
}