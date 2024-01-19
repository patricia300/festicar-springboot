
## Commande pour installer
```bash
 mvn clean install
```

## Comande pour lancer l'analyse de la branch "develop" sur Sonar

```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=bdi_5 \
  -Dsonar.host.url=http://im2ag-sonar.u-ga.fr:9000 \
  -Dsonar.login=9e61527f045e1722b328fb0b8fbc96eda8a04d54
```

## Comande pour lancer l'analyse de la branch "main" sur Sonar
```bash
mvn clean verify sonar:sonar \
-Dsonar.projectKey=bdi_5_main \
-Dsonar.host.url=http://im2ag-sonar.u-ga.fr:9000 \
-Dsonar.login=c50179a369e1d7cc749e48eda152557c8bb5db35
```