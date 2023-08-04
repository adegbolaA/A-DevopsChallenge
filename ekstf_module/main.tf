provider "aws" {
  region = var.aws_region
}

# Create VPC
resource "aws_vpc" "eks_vpc" {
  cidr_block = "10.0.0.0/16"
}

# Create Subnets (Update availability zones accordingly)
resource "aws_subnet" "private_subnet_a" {
  vpc_id                  = aws_vpc.eks_vpc.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "${var.aws_region}a"
}

resource "aws_subnet" "private_subnet_b" {
  vpc_id                  = aws_vpc.eks_vpc.id
  cidr_block              = "10.0.2.0/24"
  availability_zone       = "${var.aws_region}b"
}

resource "aws_subnet" "private_subnet_c" {
  vpc_id                  = aws_vpc.eks_vpc.id
  cidr_block              = "10.0.3.0/24"
  availability_zone       = "${var.aws_region}c"
}

# Create EKS Cluster
resource "aws_eks_cluster" "eks_cluster" {
  name     = var.eks_cluster_name
  role_arn = aws_iam_role.eks_worker_node.arn

  vpc_config {
    subnet_ids = [
      aws_subnet.private_subnet_a.id,
      aws_subnet.private_subnet_b.id,
      aws_subnet.private_subnet_c.id,
    ]
  }
}

# Create Worker Node IAM Role
# Create Worker Node IAM Role
# Create Worker Node IAM Role
resource "aws_iam_role" "eks_worker_node" {
  name = "eks-worker-node-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "ec2.amazonaws.com"
        }
      },
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "eks.amazonaws.com"
        }
      }
    ]
  })

  # Attach the AmazonEKSClusterPolicy, AmazonEKSWorkerNodePolicy, and AmazonEC2ContainerRegistryReadOnly policies to the IAM role
  managed_policy_arns = [
    "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy",
    "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy",
    "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
  ]
}



# Create and attach the inline policy to IAM role
resource "aws_iam_policy_attachment" "eks_worker_node_policy_attachment" {
  name       = "eks-worker-node-policy-attachment"
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"  # Attach more policies if needed
  roles      = [aws_iam_role.eks_worker_node.name]
}

# Create Node Group
resource "aws_eks_node_group" "node_group" {
  cluster_name    = aws_eks_cluster.eks_cluster.name
  node_group_name = "my-node-group"
  node_role_arn   = aws_iam_role.eks_worker_node.arn
  subnet_ids      = [
    aws_subnet.private_subnet_a.id,
    aws_subnet.private_subnet_b.id,
    aws_subnet.private_subnet_c.id,
  ]

  scaling_config {
    desired_size = 2
    max_size     = 2
    min_size     = 1
  }

  tags = {
    Terraform   = "true"
    Environment = "dev"
  }
}

# Security Group for EKS Workers
resource "aws_security_group" "eks_worker_sg" {
  name_prefix = "eks-worker-sg"
  vpc_id      = aws_vpc.eks_vpc.id

  # Allow all outbound traffic
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Security Group for Prometheus
resource "aws_security_group" "prometheus_sg" {
  name_prefix = "prometheus-sg"
  vpc_id      = aws_vpc.eks_vpc.id

  # Allow inbound traffic on port 9090 for Prometheus
  ingress {
    from_port   = 9090
    to_port     = 9090
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]  # Update this with the allowed CIDR blocks (or restrict it to specific IP ranges)
  }
}

# Attach the Prometheus security group to EKS Workers
resource "aws_security_group_rule" "prometheus_sg_attachment" {
  type              = "ingress"
  from_port         = 9090
  to_port           = 9090
  protocol          = "tcp"
  security_group_id = aws_security_group.prometheus_sg.id

  # Replace "0.0.0.0/0" with the allowed CIDR block
  cidr_blocks       = ["10.0.0.0/24"]
}
