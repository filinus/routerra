$(aws ecr get-login --no-include-email --region us-west-1)

docker-compose -f ./docker-compose-local.yml build --compress --force-rm

REPO=610099245367.dkr.ecr.us-west-1.amazonaws.com
AGGR=routerra/aggregator:latest
REST=routerra/rest:latest

docker tag $AGGR $REPO/$AGGR
docker tag $REST $REPO/$REST

docker push $REPO/$AGGR
docker push $REPO/$REST