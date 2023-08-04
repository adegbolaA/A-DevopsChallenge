# Output EKS Cluster Configuration
output "eks_cluster_config" {
  value = aws_eks_cluster.eks_cluster
}