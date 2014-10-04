7klick
======
Build root module
Build common module
Build profile module

Set Tomcat VM options to -Xms512m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=512m

Deploy on Tomcat

Access
http://localhost:8080/common-web

The 7klick common-web is a help page to create authentication token to be used when running other 7klick modules. The token is posted as a query parameter.
localhost:8080/profile/?ticket=nFYYbTAjno7Isv0VIAq0ljyHdSpShdWuh0lv4p7K-qWeRd0d2495QKXYUYQP5YaIyyrBLQvfHqaNCrbPel9b-PTd3Ja1m5IK9oigWOd6v-_2om1JlOaL70d1dtn8gQiV

Select Login to module
Create ticket
Sign in- select profile

