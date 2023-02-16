<img width="1515" alt="Screenshot 2023-02-17 at 1 55 27 AM" src="https://user-images.githubusercontent.com/43849911/219479036-059a8e3e-4c42-47c5-90ab-ce6b5a7703e0.png">

```
echo -n 'postgres' | base64
cG9zdGdyZXM=
```

```
./mvnw clean install -DskipTests=true
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------< com.example.k8s:springboot-postgres-k8s >---------------
[INFO] Building springboot-postgres-k8s 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ springboot-postgres-k8s ---
[INFO] Deleting /Users/saiashish/Desktop/sai/projects/springboot-postgres-k8s/target
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ springboot-postgres-k8s ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 2 resources
[INFO] Copying 4 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ springboot-postgres-k8s ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 7 source files to /Users/saiashish/Desktop/sai/projects/springboot-postgres-k8s/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ springboot-postgres-k8s ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/saiashish/Desktop/sai/projects/springboot-postgres-k8s/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ springboot-postgres-k8s ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/saiashish/Desktop/sai/projects/springboot-postgres-k8s/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ springboot-postgres-k8s ---
[INFO] Tests are skipped.
[INFO] 
[INFO] --- maven-jar-plugin:3.2.0:jar (default-jar) @ springboot-postgres-k8s ---
[INFO] Building jar: /Users/saiashish/Desktop/sai/projects/springboot-postgres-k8s/target/springboot-postgres-k8s-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.3.1.RELEASE:repackage (repackage) @ springboot-postgres-k8s ---
[INFO] Replacing main artifact with repackaged archive
[INFO] 
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ springboot-postgres-k8s ---
[INFO] Installing /Users/saiashish/Desktop/sai/projects/springboot-postgres-k8s/target/springboot-postgres-k8s-0.0.1-SNAPSHOT.jar to /Users/saiashish/.m2/repository/com/example/k8s/springboot-postgres-k8s/0.0.1-SNAPSHOT/springboot-postgres-k8s-0.0.1-SNAPSHOT.jar
[INFO] Installing /Users/saiashish/Desktop/sai/projects/springboot-postgres-k8s/pom.xml to /Users/saiashish/.m2/repository/com/example/k8s/springboot-postgres-k8s/0.0.1-SNAPSHOT/springboot-postgres-k8s-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.498 s
[INFO] Finished at: 2023-02-17T02:24:54+05:30
[INFO] ------------------------------------------------------------------------
```

```
minikube start
😄  minikube v1.29.0 on Darwin 12.6
✨  Using the docker driver based on existing profile
👍  Starting control plane node minikube in cluster minikube
🚜  Pulling base image ...
E0217 02:04:20.685139    8484 cache.go:188] Error downloading kic artifacts:  failed to download kic base image or any fallback image
🤷  docker "minikube" container is missing, will recreate.
🔥  Creating docker container (CPUs=2, Memory=4000MB) ...
🐳  Preparing Kubernetes v1.26.1 on Docker 20.10.23 ...
🔗  Configuring bridge CNI (Container Networking Interface) ...
🔎  Verifying Kubernetes components...
    ▪ Using image docker.io/kubernetesui/dashboard:v2.7.0
    ▪ Using image gcr.io/k8s-minikube/storage-provisioner:v5
    ▪ Using image docker.io/kubernetesui/metrics-scraper:v1.0.8
💡  Some dashboard features require the metrics-server addon. To enable all features please run:

	minikube addons enable metrics-server	


🌟  Enabled addons: storage-provisioner, default-storageclass, dashboard
🏄  Done! kubectl is now configured to use "minikube" cluster and "default" namespace by default
```

```
docker build -t springboot-postgres-k8s:1.0 .
[+] Building 3.4s (7/7) FINISHED                                                
 => [internal] load build definition from Dockerfile                       0.0s
 => => transferring dockerfile: 273B                                       0.0s
 => [internal] load .dockerignore                                          0.0s
 => => transferring context: 2B                                            0.0s
 => [internal] load metadata for docker.io/adoptopenjdk/openjdk11:alpine-  3.0s
 => [internal] load build context                                          0.2s
 => => transferring context: 4.37kB                                        0.2s
 => CACHED [1/2] FROM docker.io/adoptopenjdk/openjdk11:alpine-jre@sha256:  0.0s
 => [2/2] ADD target/springboot-postgres-k8s-0.0.1-SNAPSHOT.jar app.jar    0.0s
 => exporting to image                                                     0.0s
 => => exporting layers                                                    0.0s
 => => writing image sha256:28de45eab7b8c10be9747f275dc2dd8063ae8975aa680  0.0s
 => => naming to docker.io/library/springboot-postgres-k8s:1.0             0.0s

Use 'docker scan' to run Snyk tests against images to find vulnerabilities and learn how to fix them
```

```

kubectl apply -f postgres-credentials.yml
secret/postgres-credentials created

kubectl get secrets                     
NAME                   TYPE     DATA   AGE
db-credentials         Opaque   2      113m
db-root-credentials    Opaque   1      113m
db-user-pass           Opaque   2      13d
mongo-secret           Opaque   2      7d17h
postgres-credentials   Opaque   2      39s

kubectl apply -f postgres-configmap.yml 
configmap/postgres-conf created

kubectl get configmaps                 
NAME               DATA   AGE
db-conf            2      115m
kube-root-ca.crt   1      14d
mongo-conf         2      7d17h
postgres-conf      2      14s

kubectl apply -f postgres-deployment.yml
service/postgres created
persistentvolumeclaim/postgres-pv-claim created
deployment.apps/postgres created

kubectl get deployments                
NAME                   READY   UP-TO-DATE   AVAILABLE   AGE
mongo                  1/1     1            1           7d16h
mysql                  1/1     1            1           85m
postgres               1/1     1            1           28s
springboot-k8s-mysql   0/3     3            0           75m

kubectl get svc        
NAME                   TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes             ClusterIP   10.96.0.1       <none>        443/TCP          14d
mongodb-service        ClusterIP   None            <none>        27017/TCP        7d17h
mysql                  ClusterIP   None            <none>        3306/TCP         85m
postgres               ClusterIP   None            <none>        5432/TCP         34s
springboot-k8s-mysql   NodePort    10.103.234.86   <none>        8080:30163/TCP   75m

kubectl apply -f deployment.yml               
service/springboot-postgres-k8s created
deployment.apps/springboot-postgres-k8s created

kubectl exec -it postgres-86985886b5-j2mlb bash
kubectl exec [POD] [COMMAND] is DEPRECATED and will be removed in a future version. Use kubectl exec [POD] -- [COMMAND] instead.
root@postgres-86985886b5-j2mlb:/# 

psql -h postgres -U postgres
Password for user postgres: 
psql (15.2 (Debian 15.2-1.pgdg110+1))
Type "help" for help.

postgres=# 

postgres=# \l
                                                 List of databases
    Name    |  Owner   | Encoding |  Collate   |   Ctype    | ICU Locale | Locale Provider |   Access privi
leges   
------------+----------+----------+------------+------------+------------+-----------------+---------------
--------
 employeedb | postgres | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | 
 postgres   | postgres | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | 
 template0  | postgres | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | =c/postgres+
            |          |          |            |            |            |                 | postgres=CTc/postgres
 template1  | postgres | UTF8     | en_US.utf8 | en_US.utf8 |            | libc            | =c/postgres+
            |          |          |            |            |            |                 | postgres=CTc/
postgres
(4 rows)

/api/employees

{ 
   "id" : 1 ,
   "name" : "sai"
}

\c employeedb

\dt 
employee

select * from employee;
```
