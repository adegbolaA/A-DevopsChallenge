# Create a security group resource
resource "aws_security_group" "eks_cluster_sg" {
  name_prefix = "eks-cluster-sg-"
  vpc_id      = "vpc-0e6aa2cba5ad8fadd"

  # Inbound rule for allowing SSH access from your IP
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

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 30000
    to_port     = 32767
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    protocol    = "-1"
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}



module "aws_eks_cluster" {
  source = "./modules/aws_eks"
  
  for_each = var.aws_eks_cluster_config

  eks_cluster_name = each.value.eks_cluster_name 
  subnet_ids = each.value.eks_subnet_ids
  tags = each.value.tags

  # Pass the security group ID as an input variable to the module
  security_group_id = aws_security_group.eks_cluster_sg.id
}


module "aws_eks_node_group" {

   source = "./modules/aws_eks_nodegroup"
  
   for_each = var.eks_node_group_config

    node_group_name               = each.value.node_group_name
    eks_cluster_name              = module.aws_eks_cluster[each.value.eks_cluster_name].eks_cluster_name
    subnet_ids                    = each.value.node_subnet_ids
    nodes_iam_role                = each.value.nodes_iam_role
    tags                          = each.value.tags
}


