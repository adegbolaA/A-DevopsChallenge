# Create an IAM role for EKS cluster
resource "aws_iam_role" "eks_cluster_role" {
  name = "my-eks-cluster-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "eks.amazonaws.com"
        }
      }
    ]
  })
}

# Attach necessary policies to the EKS cluster IAM role
resource "aws_iam_role_policy_attachment" "eks_cluster_policy_attachment" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
  role       = aws_iam_role.eks_cluster_role.name
}

# Create an IAM role for EKS worker nodes
resource "aws_iam_role" "eks_worker_nodes_role" {
  name = "my-eks-worker-nodes-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "ec2.amazonaws.com"
        }
      }
    ]
  })
}

# Attach necessary policies to the EKS worker nodes IAM role
resource "aws_iam_role_policy_attachment" "eks_worker_nodes_policy_attachment" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
  role       = aws_iam_role.eks_worker_nodes_role.name
}

resource "aws_iam_role_policy_attachment" "eks_cni_policy_attachment" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
  role       = aws_iam_role.eks_worker_nodes_role.name
}

# Create the VPC for EKS
resource "aws_vpc" "eks_vpc" {
  cidr_block = "10.0.0.0/16"
}

# Create subnets for EKS worker nodes
resource "aws_subnet" "eks_worker_subnets" {
  count         = 2
  cidr_block    = "10.0.${count.index + 1}.0/24"
  vpc_id        = aws_vpc.eks_vpc.id
  availability_zone = "us-east-2"  
}

# Create security group for EKS worker nodes
resource "aws_security_group" "eks_worker_security_group" {
  name_prefix = "eks-worker-security-group"
  vpc_id      = aws_vpc.eks_vpc.id

  ingress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

 ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port = 0
    protocol  = "-1"
    to_port   = 0
    self = true
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create the EKS cluster
resource "aws_eks_cluster" "my_eks_cluster" {
  name     = "my-eks-cluster"
  role_arn = aws_iam_role.eks_cluster_role.arn

  vpc_config {
    subnet_ids = aws_subnet.eks_worker_subnets[*].id
    security_group_ids = [aws_security_group.eks_worker_security_group.id]
  }

  depends_on = [
    aws_iam_role_policy_attachment.eks_cluster_policy_attachment,
    aws_iam_role_policy_attachment.eks_worker_nodes_policy_attachment,
    aws_iam_role_policy_attachment.eks_cni_policy_attachment,
  ]
}

# Create the EKS worker nodes
module "eks_worker_nodes" {
  source = "terraform-aws-modules/eks/aws//modules/worker_nodes"

  cluster_name     = aws_eks_cluster.my_eks_cluster.name
  subnets          = aws_subnet.eks_worker_subnets[*].id
  instance_type    = "t3a.medium"  
  min_size         = 1
  max_size         = 3

  # Attach the IAM role created earlier
  instance_profile_name = aws_iam_role.eks_worker_nodes_role.name
}


