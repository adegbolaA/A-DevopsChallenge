# Importing the `aws_security_group` resource from the other file
data "terraform_remote_state" "security_group" {
  backend = "local"  # You can change this to match your backend configuration

  # Path to the state file of security_group.tf relative to the current working directory
  config = {
    path = "/home/ec2-user/A-DevopsChallenge/eks_module/securitygroup.tfstate"
  }
}



resource "aws_security_group_rule" "eks_cluster_ingress_rule" {
  type        = "ingress"
  from_port   = 22  # The source port of incoming traffic (SSH port)
  to_port     = 22  # The destination port of incoming traffic (SSH port)
  protocol    = "tcp"  # The protocol for the incoming traffic (TCP)
  security_group_id = data.terraform_remote_state.security_group.outputs.eks_cluster_sg_id

  # The source CIDR block for incoming traffic. 0.0.0.0/0 allows traffic from any IP address.
  cidr_blocks = ["0.0.0.0/0"]
}



resource "aws_eks_cluster" "eks" {
  # Name of the cluster.
  name =  var.eks_cluster_name

  # The Amazon Resource Name (ARN) of the IAM role that provides permissions for 
  # the Kubernetes control plane to make calls to AWS API operations 
  role_arn = aws_iam_role.eks_cluster.arn

  # Desired Kubernetes master version
  version = "1.24"

    vpc_config {
    endpoint_private_access = false
    endpoint_public_access  = true
    subnet_ids              = var.subnet_ids
    security_group_ids      = [aws_security_group.eks_cluster_sg.id]  
  }

  # Ensure that IAM Role permissions are created before and deleted after EKS Cluster handling.
  # Otherwise, EKS will not be able to properly delete EKS managed EC2 infrastructure such as Security Groups.
  depends_on = [
    aws_iam_role_policy_attachment.amazon_eks_cluster_policy,
    aws_security_group_rule.eks_cluster_ingress_rule, 
  ]
  tags = var.tags

  /*   # Associate the new key pair with the cluster
  remote_access {
    ec2_ssh_key            = aws_key_pair.eks_key_pair.key_name
    source_security_group_ids = [aws_security_group.eks_cluster_sg.id]  
  } */
}

resource "aws_iam_role" "eks_cluster" {
  # The name of the role
  name = "eks-cluster-role"

  # The policy that grants an entity permission to assume the role.
  # Used to access AWS resources that you might not normally have access to.
  # The role that Amazon EKS will use to create AWS resources for Kubernetes clusters
  assume_role_policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "eks.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
POLICY
}

resource "aws_iam_role_policy_attachment" "amazon_eks_cluster_policy" {
  # The ARN of the policy you want to apply
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"

  # The role the policy should be applied to
  role = aws_iam_role.eks_cluster.name
}

resource "aws_iam_role_policy_attachment" "AmazonEC2ContainerRegistryReadOnly-EKS" {
 policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
 role    = aws_iam_role.eks_cluster.name
}
