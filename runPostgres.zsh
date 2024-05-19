docker run -dt --name postgres -e POSTGRES_PASSWORD=admin -e PAGER=/usr/bin/less -e LESS='-X -R -i' -p 5432:5432 docker.io/library/postgres:16-alpine


