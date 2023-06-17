1) S3 Connection :
   AmazonS3ClientBuilder
   .standard()
   .withEndpointConfiguration(new EndpointConfiguration("http://localhost:4566/", "us-east-1")) // localstack endpoint configuration
   .withCredentials(new DefaultAWSCredentialsProviderChain())
   .withPathStyleAccessEnabled(true) // Disable virtualhost style connection and enable path style s3 bucket
   .build();

1) Create s3 bucket -
# aws --endpoint-url=http://localhost:4566 s3 mb s3://my-test-bucket
aws --endpoint-url=http://localhost:4566 s3api create-bucket --bucket my-test-bucket-2 --region sa-west-1 --create-bucket-configuration LocationConstraint=sa-west-1

2) List s3 buckets -
# aws --endpoint-url="http://localhost:4566" s3 ls

3) Upload file on s3 bucket -
# aws --endpoint-url="http://localhost:4566" s3 sync "myfiles" s3://my-test-bucket

4) List files from AWS CLI -
# aws --endpoint-url="http://localhost:4566" s3 ls s3://my-test-bucket

6) Access file via URL(File name was download.jpg) -
# http://localhost:4566/my-test-bucket/download.jpg

7) Delete object from bucket -
# aws --endpoint-url=http://localhost:4566 s3api delete-object --bucket <bucket-name> --key <file-name.format>

8) Create bucket notification configuration -
# aws --endpoint-url=http://localhost:4566 s3api put-bucket-notification-configuration --bucket <bucket-name> --notification-configuration file://<configuration-file-name>.json

9) Get bucket notification configuration -
# aws --endpoint-url=http://localhost:4566 s3api get-bucket-notification-configuration --bucket <bucket-name>

10) Set bucket policy -
# aws --endpoint-url=http://localhost:4566 s3api put-bucket-policy --bucket <bucket-name> --policy file://<policy-file>.json

10) Get bukcet policy -
# aws --endpoint-url=http://localhost:4566 s3api get-bucket-policy --bucket <bucket-name>