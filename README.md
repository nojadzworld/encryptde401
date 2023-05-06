# Fav Songs

The fav songs app is a demo for BCrypt for hashing passwords to store in a Postgres database.

## Instructions

- Build from the command line inside the `favsong` directory with `./gradlew bootRun`
  - Setup a Postgres server with a database named "favsong".
- The `src/main/resources/application.properties` file contains generic user/name password resources change these to match your Postgres server username and password.
  - While the app is running direct your web browser to [localhost:8080](http://localhost:8080). This will load the splash page.

### App Use

Follow the link to create an account and enter a username and password, you'll be redirected back to the login page, now use your credentials to login.

After logging in you can use the "Add a new Song " form to add a post. After making a post it will be displayed on the page. Keep making posts and they'll populate the page.
