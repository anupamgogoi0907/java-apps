### Download 
Download the app folder and place anywhere in your system. Note that the system must have JRE installed.

### Import public certificate of SMTP Server to Java cacerts
```
sudo keytool -import -file  ./smtp.cert -alias smtp -keystore /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre/lib/securitycacerts
```
Password: **changeit**

### Configure 
Fill proper values in the **config.properties** according to your server config.

### How to Run
```
$ java -jar app.jar ".config.properties"
```



