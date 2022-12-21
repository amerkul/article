resource "kubernetes_deployment_v1" "test_zookeeper" {
  metadata {
    name   = "zookeeper"
    labels = {
      app = "zookeeper"
    }
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        app = "zookeeper"
      }
    }

    template {
      metadata {
        labels = {
          app = "zookeeper"
        }
      }

      spec {
        container {
          name              = "zookeeper"
          image             = "wurstmeister/zookeeper"
          image_pull_policy = "IfNotPresent"

          port {
            container_port = 2181
          }
        }
      }
    }
  }
}

resource "kubernetes_service_v1" "test_zookeeper" {
  metadata {
    name   = "zookeeper-cluster"
    labels = {
      app = "zookeeper-service"
    }
  }

  spec {
    type     = "NodePort"
    selector = {
      app = "zookeeper"
    }

    port {
      port        = 2181
      target_port = 2181
      node_port   = 30181
    }
  }
}