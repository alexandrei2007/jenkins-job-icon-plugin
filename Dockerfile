FROM ubuntu

RUN apt-get update && apt-get install -y \
    openjdk-8-jdk \
    maven \
    vim 

RUN echo export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 >> ~/.bashrc

RUN ln -sf /usr/lib/jvm/java-8-openjdk-amd64/bin/java /usr/bin/java
RUN ln -sf /usr/lib/jvm/java-8-openjdk-amd64/bin/javac /usr/bin/javac
RUN ln -sf /usr/lib/jvm/java-8-openjdk-amd64/bin/javadoc /usr/bin/javadoc

WORKDIR /app
CMD ["/bin/bash"]