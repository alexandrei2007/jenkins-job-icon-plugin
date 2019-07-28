# jenkins-job-icon-plugin

Plugin that allows adding an icon to a job

## Building the image to compile the plugin

```bash
docker build -t jenkins-build .
docker-compose up -d
docker exec it ID bash
```

Inside the container

```bash
mvn hpi:run
```

