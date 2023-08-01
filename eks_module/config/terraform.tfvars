aws_eks_cluster_config = {
  "demo-cluster" = {
    eks_cluster_name = "demo-cluster"
    eks_subnet_ids   = ["subnet-0f192aefd8ada8889", "subnet-04ad9b5edecb4b75a", "subnet-03bc881c9dfda6cab"]
    tags             = {
      "Name" = "demo-cluster"
    }
  }
}

eks_node_group_config = {
  "node1" = {
    eks_cluster_name  = "demo-cluster"  
    node_group_name   = "myeksnode"
    nodes_iam_role    = "eks-node-group-general1"
    node_subnet_ids   = ["subnet-0f192aefd8ada8889", "subnet-04ad9b5edecb4b75a", "subnet-03bc881c9dfda6cab"]
    tags              = {
      "Name" = "node1"
    }
  }
}
