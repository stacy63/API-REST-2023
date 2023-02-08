# Configuration

## Base de données MariaDB

### Trouver sa base de donnée adaptée
- https://www.ambient-it.net/meilleures-base-de-donnees/
- https://www.ionos.fr/digitalguide/hebergement/aspects-techniques/mariadb-vs-mysql/

### Installation
- https://mariadb.org/download/?t=mariadb&p=mariadb&r=10.11.1&os=windows&cpu=x86_64&pkg=msi&m=icam
- https://www.mariadbtutorial.com/getting-started/install-mariadb/
- https://openclassrooms.com/fr/courses/6982461-utilisez-spring-data-pour-interagir-avec-vos-bases-de-donnees/7201158-mettez-en-place-votre-environnement-de-developpement
- https://stackoverflow.com/questions/28036244/flyway-maven-plugin-how-to-read-the-settings-from-spring-boot-application-yml
- https://stackoverflow.com/questions/53172123/flyway-found-non-empty-schemas-public-without-schema-history-table-use-bas
- https://blog.webnersolutions.com/java-execute-sql-script-using-maven/
- https://stackoverflow.com/questions/71712444/cannot-connect-to-database-with-flyway-through-maven
- https://www.baeldung.com/liquibase-vs-flyway
- https://docs.spring.io/spring-boot/docs/1.1.0.M1/reference/html/howto-database-initialization.html

### application.properties
- https://springframework.guru/configuring-spring-boot-for-mariadb/
- https://stackoverflow.com/questions/37066024/what-is-the-mariadb-dialect-class-name-for-hibernate
- https://flywaydb.org/documentation/usage/maven/
- https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

### Modèle de données
https://www.unifr.ch/inf/softeng/en/assets/public/files/teaching/bachelor_seminars/4_Sem_complexe_2108.pdf

### Script sql
- https://stackoverflow.com/questions/6265891/mysql-syntax-what-is-this
- https://www.it-connect.fr/comment-creer-une-base-de-donnees-et-des-tables-avec-un-script-sql-%EF%BB%BF/
- https://www.quora.com/After-creating-table-in-mysql-bench-why-we-use-the-below-statement-and-what-does-all-keywords-mean-ENGINE-InnoDB-AUTO_INCREMENT-1-DEFAULT-CHARSET-latin1
    - ENGINE is used to specify the storage engine to be used for the table. When a database is created, one often overlooked but critical factor in performance is the storage engine
      (particularly as the database grows). In many instances, the temptation is to just accept the default and continue on developing your project. This can lead to unexpected negative
      impacts on performance, backups, and data integrity later in the application life cycle, such as when your team implements analytics and MySQL dashboards.
      By default, MySQL 5.7 supports ten storage engines (InnoDB, MyISAM, Memory, CSV, Archive, Blackhole, NDB, Merge, Federated, and Example)
      AUTO_INCREMENT defines the initial value for autoincrement column, By default it will start from 1.
      DEFAULT CHARSET defines the character encoding in which data will be stored in database.
- https://mariadb.com/kb/en/choosing-the-right-storage-engine/
- https://developpement-informatique.com/article/282/types-de-donnees-sql
- https://www.developpez.net/forums/d963011/bases-donnees/mysql/debuter/probleme-creation-d-association-table-sous-mysql/
- https://learnsql.com/cookbook/how-to-check-the-length-of-a-string-in-sql/
- https://developpement-informatique.com/article/284/les-contraintes-en-sql
- https://dba.stackexchange.com/questions/37014/in-what-data-type-should-i-store-an-email-address-in-database
- https://stackoverflow.com/questions/2240973/how-long-is-the-sha256-hash
- https://stackoverflow.com/questions/19154453/how-to-write-a-query-to-ensure-email-contains
- https://dzone.com/articles/flyway-error-found-non-empty-schemas-without-schem
- https://flywaydb.org/documentation/configuration/parameters/validateOnMigrate

## Spring, JPA, Hibernate

### Hierarchie et annotation
- https://blog.paumard.org/cours/jpa/chap05-heritage-entite.html
- https://www.baeldung.com/hibernate-identifiers
- https://koor.fr/Java/TutorialJEE/jee_jpa_many_to_many.wp
- https://www.baeldung.com/jpa-many-to-many
- https://koor.fr/Java/TutorialJEE/jee_jpa_mapping_cascade.wp
- https://www.baeldung.com/jpa-cascade-types
- (Case one): https://www.baeldung.com/jpa-many-to-many
- https://thorben-janssen.com/hibernate-tip-many-to-many-association-with-additional-attributes/
- hashcode : https://www.jmdoudoux.fr/java/dej/chap-techniques_java.htm#techniques_java-2
- https://www.baeldung.com/jpa-embedded-embeddable
- https://docs.jboss.org/hibernate/annotations/3.4/reference/fr/html_single/
- https://blog.paumard.org/cours/jpa/chap03-entite-objets-inclus.html

### Code
Regex :
- https://blog.paumard.org/cours/java-api/chap03-expression-regulieres-syntaxe.html
- https://fr.wikibooks.org/wiki/Programmation_Java/Regex
- https://www.developpez.net/forums/d1049030/java/general-java/pattern-autoriser-noms-traits-d-union/
- http://www.rexegg.com/regex-lookarounds.html
- String paramétrée : https://stackoverflow.com/questions/10019162/parameterized-strings-in-java
- https://www.baeldung.com/java-annotation-attribute-value-restrictions
- https://www.baeldung.com/java-annotation-attribute-value-restrictions
- https://www.w3schools.com/sql/sql_wildcards.asp
- case requêtes : https://sql.sh/cours/case
- https://learn.microsoft.com/fr-fr/sql/t-sql/functions/replace-transact-sql?view=sql-server-ver16
- https://www.baeldung.com/spring-data-exists-query
- https://en.wikibooks.org/wiki/Java_Persistence/JPQL
- https://docs.github.com/fr/get-started/getting-started-with-git/ignoring-files
- https://learning.postman.com/docs/sending-requests/troubleshooting-api-requests/
- https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
- https://help.returnpath.com/hc/en-us/articles/220560587-What-are-the-rules-for-email-address-syntax-  
Log4j:
- https://stackoverflow.com/questions/31235195/why-does-the-log4j-environment-variable-need-both-core-and-api-jars
- https://sematext.com/blog/log4j-tutorial/  
Log4j2:
- https://stackoverflow.com/questions/71522695/log4j-does-not-record-any-log-file-for-my-project
- https://dzone.com/articles/log4j-2-configuration-using-properties-file#:~:text=properties%20file%20is%20a%20set,the%20file%2C%20and%20appender%20declarations.
- https://howtodoinjava.com/log4j2/log4j2-properties-example/
- https://stackify.com/log4j2-java/#:~:text=Configuration%3A%20the%20root%20element%20of,the%20System%20console%20is%20defined
- https://rmdiscala.developpez.com/cours/LesChapitres.html/Java/Cours3/Chap3.1.htm  
ExceptionHandler:
- https://www.baeldung.com/exception-handling-for-rest-with-spring
- https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f
- https://davidgiard.com/handling-spring-boot-errors-with-controlleradvice

## Contraintes objets en prévision des contraintes colonnes SQL
- https://thorben-janssen.com/hibernate-tips-whats-the-difference-between-column-nullable-false-and-notnull/
- https://www.baeldung.com/java-bean-validation-not-null-empty-blank
- https://docs.jboss.org/hibernate/annotations/3.4/reference/fr/html/validator.html
- https://docs.jboss.org/hibernate/validator/4.1/reference/en-US/html/validator-customconstraints.html#validator-customconstraints-constraintannotation
- https://docs.oracle.com/javaee/7/api/javax/validation/ConstraintValidator.html
- https://www.geeksforgeeks.org/hibernate-validator-with-example/
- https://jmdoudoux.developpez.com/cours/developpons/java/chap-validation_donnees.php
- https://koor.fr/Java/Tutorial/java_annotations_definition.wp
- https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-declaring-bean-constraints
- https://stackoverflow.com/questions/18382330/turn-off-bean-validation-programmatically-javax-validation-constraints
- https://www.baeldung.com/javax-validation

## HTTPS
- https://www.thomasvitale.com/https-spring-boot-ssl-certificate/
- https://www.baeldung.com/spring-boot-https-self-signed-certificate
- https://stackoverflow.com/questions/5983946/tried-many-answers-to-get-my-keytool-exe-to-open-but-failed
- https://fr.wikipedia.org/wiki/PKCS12
- https://support.postman.com/hc/en-us/articles/4416016147991-I-am-having-trouble-adding-client-certificates#:~:text=Self-signed%20certificate%20in%20certificate,connecting%20to%20the%20API%20service.
- https://www.ibm.com/docs/en/tnpm/1.4.2?topic=security-import-certificate-jre-keystore
- removing ssl certificate from cacerts : https://docs.oracle.com/cd/E19683-01/817-2874/6migoia18/
- https://www.ibm.com/docs/en/urbancode-deploy/6.2.4?topic=configuration-changing-passwords-server-keystore
- https://doc.nuxeo.com/nxdoc/trust-store-and-key-store-configuration/

## HASH
- https://auth0.com/blog/adding-salt-to-hashing-a-better-way-to-store-passwords/
- https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html
- https://mkyong.com/java/java-password-hashing-with-argon2/
- https://www.lemagit.fr/definition/Mebioctet-Mio-miB
- https://www.baeldung.com/java-secure-random
- https://stackoverflow.com/questions/40862857/whats-the-best-way-to-create-a-good-securerandom 
(SecureRandom commonly depends on the host to provide the seed or even the random data.)
- https://howtodoinjava.com/java8/secure-random-number-generation/
- https://stackoverflow.com/questions/18142745/how-do-i-generate-a-salt-in-java-for-salted-hash
- https://security.stackexchange.com/questions/223805/storing-argon2id-parameters-in-a-database
- https://fr.wikipedia.org/wiki/Base64
- http://patatos.over-blog.com/article-antipattern-java-interface-de-constantes-114377630.html
- https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt

## SPRING SECURITY
- https://www.baeldung.com/spring-security-with-maven
- https://www.baeldung.com/spring-boot-security-autoconfiguration
- https://reflectoring.io/spring-csrf/
- https://blog.ouidou.fr/spring-security-pour-les-nuls-1930fce60089
- https://www.baeldung.com/spring-security-authentication-with-a-database
- https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
- https://www.codejava.net/frameworks/spring-boot/spring-boot-security-authentication-with-jpa-hibernate-and-mysql
- https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
- Override authprovider : https://www.invivoo.com/securiser-application-spring-boot-spring-security/  
tests : 
- https://www.invivoo.com/securiser-application-spring-boot-spring-security/
- https://howtodoinjava.com/spring-security/http-basic-authentication-example/
- https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter
- Customizing Entrypoint : https://www.baeldung.com/spring-security-basic-authentication
- requestMatchers : https://stackoverflow.com/questions/28907030/spring-security-authorize-request-for-certain-url-http-method-using-httpsecu
- https://howtodoinjava.com/spring-security/http-basic-authentication-example/

## MAPPER ET DTO
- https://mapstruct.org/
- https://auth0.com/blog/how-to-automatically-map-jpa-entities-into-dtos-in-spring-boot-using-mapstruct/
- https://www.baeldung.com/mapstruct

## JWT
- https://www.developpez.net/forums/d2112802/java/plateformes-java-ee-jakarta-ee-spring-serveurs/spring/spring-web/spring-security-login-ajax-api-rest-erreur-401-a/
- https://blog.ouidou.fr/spring-security-jwt-et-cycle-de-vie-de-lauthentification-42b8fbdeabba
- https://www.toptal.com/spring/spring-security-tutorial
- https://github.com/jwtk/jjwt/issues/617
- https://stackoverflow.com/questions/55102937/how-to-create-a-spring-security-key-for-signing-a-jwt-token
- https://www.javainuse.com/spring/boot-jwt
- https://www.tutorialspoint.com/spring_security/spring_security_with_jwt.htm
- https://www.toptal.com/spring/spring-security-tutorial
- https://stackoverflow.com/questions/73486900/how-to-fix-parser-is-deprecated-and-setsigningkeyjava-security-key-is-depr
- https://auth0.com/blog/forbidden-unauthorized-http-status-codes/

## A tester 
Mapping with SQLResultSetMapping : 
- https://www.aegissofttech.com/articles/hibernate-sql-query-result-set-mapping-in-java.html#link1
- https://www.baeldung.com/jpa-sql-resultset-mapping  
csrf :
- https://dev.to/shane/using-postman-with-java-spring-and-csrf-tokens-di0
- https://www.baeldung.com/csrf-stateless-rest-api
- https://reflectoring.io/spring-csrf/
- https://myshittycode.com/2015/03/30/spring-security-invalid-csrf-token-null-was-found-on-the-request-parameter-_csrf-or-header-x-csrf-token/  
cors : 
- https://www.baeldung.com/spring-cors
- https://www.tabnine.com/code/java/methods/org.springframework.web.reactive.config.CorsRegistry/addMapping  
logging failure and success spring security:
- https://www.codejava.net/frameworks/spring-boot/spring-security-limit-login-attempts-example
- https://www.baeldung.com/spring-security-login
- https://www.invivoo.com/securiser-application-spring-boot-spring-security/