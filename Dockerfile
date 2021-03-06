FROM openjdk:13.0.1
VOLUME /tmp
COPY . /app
WORKDIR /app
ENV SPOTIFY_CLIENT_ID=$SPOTIFY_CLIENT_ID
ENV SPOTIFY_CLIENT_SECRET=$SPOTIFY_CLIENT_SECRET
ENV SPOTIFY_REFRESH_TOKEN=$SPOTIFY_REFRESH_TOKEN
ENV SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
ENV SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
ENV SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD
RUN /bin/sh -c "./mvnw clean package"
ADD target/spotify-fetcher-1.0.jar .
CMD java -jar spotify-fetcher-1.0.jar
