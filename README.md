
# Custom rules plugin for SonarQube 10.3 and Java 11

Sonar Java plugin to detect, if any code constructs are being used to hide untested code from being reported in Code coverage reports eg jacoco.  
This plugin targets two scenarios :   
* It reports creation of new Annotation that has name with 'Generated' substring. 
* It reports any uses of Annotation of type @lombok.Generated or annotation of any name that includes 'Generated' substring when applied to clas or method. When any annotation with this name pattern is applied to method or class, then it is not reported in coverage reports.  

## Install

* `cd <project directory>`
* `mvn clean install`
* `cp ./target/sonar-java-detect-annotation-1.0.0.jar ./volumes/plugins/`
* `docker compose up`

## Debug

`mvnDebug clean install sonar:sonar -Dsonar.projectKey='<project_key>' -Dsonar.projectName='<project_name>' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=<token>`
