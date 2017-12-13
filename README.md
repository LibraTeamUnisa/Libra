# libra

## Installation

### Requirements
  * git
  * Eclipse IDE for Java EE Developers
  * WildFly 11.0.0.Final
  * Mysql server 5.7.20
  * Default user and password for mysqldb: root root

Download pre-configured wildfly
```
https://mega.nz/#!yNxUlJiK!2FNNry7lkOUvCP569_urqtQAaKWspfe_da6yxUzaDm8
https://www.dropbox.com/s/s59yxaqjy5c3gqs/wildfly-11.0.0.Final.zip?dl=0
```

Download current repository 
```bash
git clone https://github.com/cciro94/libra.git
cd libra
```

Import downloaded folder into eclipse 
```
Import --> Maven/Existing Maven Project
```

Build libra project using run as Maven install or using bash command
```bash
mvn clean install
```
Copy builded file into wildfly's home folder
```bash
copy target/Libra.war {WILDFLY_HOME}/standalone/deployments
```

### Execution
Just run wildfly and check on http://localhost:8080/Libra/
```bash
{WIDLFLY_HOME}/bin/standalone.bat
```
### DB connection test
Creates a new table and persists an Azienda with id = 1 and ragioneSociale = "RagioneSociale"
```
http://localhost:8080/Libra/registrazione
```
