
## Commande pour installer
```bash
 mvn clean install
```

## Comande pour lancer l'analyser sur Sonar

```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=bdi_5 \
  -Dsonar.host.url=http://im2ag-sonar.u-ga.fr:9000 \
  -Dsonar.login=9e61527f045e1722b328fb0b8fbc96eda8a04d54
```