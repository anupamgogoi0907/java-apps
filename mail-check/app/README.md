### Import public certificate of SMTP Server to Java cacerts
```
sudo keytool -import -file  /Users/agogoi/git/anupamgogoi0907/java-apps/mail-check/app/smtp.cert -alias smtp -keystore cacerts
```
Password: **changeit**

### Configure 
Fill proper values in the **config.properties** according to your server config.

### How to Run
```
$ java -jar app.jar ".config.properties"
```



